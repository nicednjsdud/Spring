package kr.co.board.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;
import kr.co.board.board.service.BoardService;
import kr.co.board.member.dto.MemberDTO;

@Controller
public class BoardControllerImpl implements BoardController {

	// 이미지 저장 위
	private static String ARTICLE_IMAGE_REPO = "/Users/jeong-won-yeong/Documents/Spring/workspace-spring/imageRepo";

	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleDTO articleDTO;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 인터셉터에서 전달된 뷰 이름 가져옴
		String viewName = (String) request.getAttribute("viewName");
		List<ArticleDTO> articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		// 조회한 글 정보를 바인딩 후 jsp로 전달함
		mav.addObject("articlesList", articlesList);

		return mav;
	}

	@RequestMapping(value = "/board/*Form.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("here boardForm.do");

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// (이미지 포함한 )글추가
	@Override
	@RequestMapping(value = "/board/addNewArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {

		multipartRequest.setCharacterEncoding("utf-8");
		String imageFileName = null;

		// 글정보 저장하기 위한 Map 생성
		Map articleMap = new HashMap();
		Enumeration enun = multipartRequest.getParameterNames();
		// 새글쓰기창에서 전송된 글 정보를 Map에 key/value로 저장함
		while (enun.hasMoreElements()) {
			String name = (String) enun.nextElement();
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}

		// 로그인시 세션에 저장된 회원정보에서 아이디(글쓴이)를 map에 저장
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		String id = memberDTO.getId();
		articleMap.put("id", id);

		// 업로드한 이미지 파일 이름을 가져옴
		List<String> fileList = upload(multipartRequest);

		List<ImageDTO> imageFileList = new ArrayList<>();
		if (fileList != null && fileList.size() != 0) {
			// 전송되는 이미지 정보를 ImageDAO 객체의 속성에 차례대로 저장한 후 imageFilelist에 다시 저장함
			for (String fileName : fileList) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImageFileName(fileName);
				imageFileList.add(imageDTO);
			}
			// imageFileList를 다시 articleMap에 저장함
			articleMap.put("imageFileList", imageFileList);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String message;
		ResponseEntity resEnt = null;
		
		try {
		
			int articleNO = boardService.addNewArticle(articleMap);	// articleMap을 서비스 클래스를 전달
	
			if (imageFileList != null && imageFileList.size() != 0) {
				// 첨부한 이미지들을 for문을 이용해 업로드함
				for(ImageDTO imageDTO : imageFileList) {
					// temp => articleNO 이미지 이동.
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"/"+"temp"+"/"+imageFileName);
					File destFile = new File(ARTICLE_IMAGE_REPO+"/"+articleNO);
					FileUtils.moveFileToDirectory(srcFile, destFile, true);
				}
			}
			message = "<script>";
			message += "alert('새글을 추가했습니다.');";
			message += "location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do';";
			message += "</script>";
			
			// 새 글을 추가한 후 메세지를 전달함
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			
		}catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				// 오류 발생시 temp폴더의 이미지들 모두 삭제
				for(ImageDTO imageDTO : imageFileList) {
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO+"/"+"temp"+"/"+imageFileName);
					srcFile.delete();
				}
			}
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do';";
			message += "</script>";
			
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
		}

		return resEnt;
	}

	// 새글쓰기시 (다중) 이미지 업로드 기능 (업로드한 파일 이름들 얻은 후 반환함)
	private List<String> upload(MultipartHttpServletRequest multipartRequest)
			throws IllegalStateException, IOException {

		List<String> fileList = new ArrayList<>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();

			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);
				// 첨부한 파일이름을 차례대로 지정함
				File file = new File(ARTICLE_IMAGE_REPO + "/" + fileName);
				if (mFile.getSize() != 0) {
					if (!file.exists()) {
						file.getParentFile().mkdir(); // 경로에 해당하는 디렉토리들 생성
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + originalFileName));
						// 저장된 MultipartFile을 실제 파일로 전송
					}
				}
			}
		}
		return fileList;
	}
	
	// 글 (다중 이미지) 상세보기
	@Override
	@RequestMapping(value="/board/viewArticle.do",method=RequestMethod.GET)
	public ModelAndView viewArticle(int articleNO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		
		Map<String,Object>  articleMap = boardService.viewArticle(articleNO);	//조회할 글 정보,이미지파일 정보를 articleMap에 설정
		ModelAndView mav = new ModelAndView();
		mav.addObject("articleMap",articleMap);
		mav.setViewName(viewName);
		
		return mav;
	}
}
