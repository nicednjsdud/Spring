package kr.co.board.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// section값과 pageNum값을 구함
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");

		// 최초 요청시 section값과 pageNum값이 없으면 각각 1로 초기화함
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

		Map<String, Integer> pagingMap = new HashMap<>();
		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);

		// 모든 글 정보 조회
		
		String viewName = (String) request.getAttribute("viewName");
		Map<String,Integer> articlesMap = boardService.listArticles(pagingMap);
		ModelAndView mav = new ModelAndView(viewName);
		// 조회한 글 정보를 바인딩 후 jsp로 전달함
//		mav.addObject("articlesList", articlesList);

		return mav;
	}

	@RequestMapping(value = "/board/*Form.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/board/replyForm.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView replyForm(@RequestParam(value = "parentNO", required = false) String parentNO,
			@RequestParam(value = "groupNO", required = false) String groupNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		// 부모글을 세션에 바인딩함

		if (parentNO != null) {
			session.setAttribute("parentNO", parentNO);
		}
		if (groupNO != null) {
			session.setAttribute("groupNO", groupNO);
		}

		mav.setViewName(viewName);
		return mav;
	}

	// (이미지 포함한) 글추가
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

		// 로그인 시 세션에 저장된 회원정보에서 아이디(글쓴이)를 Map에 저장
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		String id = memberDTO.getId();
		articleMap.put("id", id);

		// 세션에 저장한 부모글을 가지고 와서 articleMap에 저장함
		String parentNO = (String) session.getAttribute("parentNO");
		articleMap.put("parentNO", (parentNO == null ? 0 : parentNO));
//		session.removeAttribute("parentNO");

		// 업로드한 이미지 파일 이름을 가져옴
		List<String> fileList = upload(multipartRequest);

		List<ImageDTO> imageFileList = new ArrayList<>();
		if (fileList != null && fileList.size() != 0) {
			// 전송되는 이미지 정보를 ImageDTO 객체의 속성에 차례대로 저장한 후 imageFileList에 다시 저장함
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

			int articleNO = boardService.addNewArticle(articleMap); // articleMap을 서비스 클래스로 전달함

			if (imageFileList != null && imageFileList.size() != 0) {
				// 첨부한 이미지들을 for문을 이용해 업로드함
				for (ImageDTO imageDTO : imageFileList) {
					// temp => articleNO 이미지 이동.
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + imageFileName);
					File destFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO);
					FileUtils.moveFileToDirectory(srcFile, destFile, true);
				}
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do';";
			message += "</script>";

			// 새 글을 추가한 후 메시지를 전달함
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				// 오류 발생시 temp폴더의 이미지들 모두 삭제
				for (ImageDTO imageDTO : imageFileList) {
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + imageFileName);
					srcFile.delete();
				}
			}

			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}

		return resEnt;
	}

	@Override
	@RequestMapping(value = "/board/addReplyArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addReplyArticle(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
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

		// 로그인 시 세션에 저장된 회원정보에서 아이디(글쓴이)를 Map에 저장
		HttpSession session = multipartRequest.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		String id = memberDTO.getId();
		articleMap.put("id", id);

		// 세션에 저장한 부모글을 가지고 와서 articleMap에 저장함
		String parentNO = (String) session.getAttribute("parentNO");
		articleMap.put("parentNO", (parentNO == null ? 0 : parentNO));
		session.removeAttribute("parentNO");

		String groupNO = (String) session.getAttribute("groupNO");
		articleMap.put("groupNO", groupNO);
		session.removeAttribute("groupNO");

		// 업로드한 이미지 파일 이름을 가져옴
		List<String> fileList = upload(multipartRequest);

		List<ImageDTO> imageFileList = new ArrayList<>();
		if (fileList != null && fileList.size() != 0) {
			// 전송되는 이미지 정보를 ImageDTO 객체의 속성에 차례대로 저장한 후 imageFileList에 다시 저장함
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

			int articleNO = boardService.addReplyArticle(articleMap); // articleMap을 서비스 클래스로 전달함

			if (imageFileList != null && imageFileList.size() != 0) {
				// 첨부한 이미지들을 for문을 이용해 업로드함
				for (ImageDTO imageDTO : imageFileList) {
					// temp => articleNO 이미지 이동.
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + imageFileName);
					File destFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO);
					FileUtils.moveFileToDirectory(srcFile, destFile, true);
				}
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?articleNO="
					+ articleMap.get("articleNO") + "';";
			message += "</script>";

			// 새 글을 추가한 후 메시지를 전달함
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				// 오류 발생시 temp폴더의 이미지들 모두 삭제
				for (ImageDTO imageDTO : imageFileList) {
					imageFileName = imageDTO.getImageFileName();
					File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + imageFileName);
					srcFile.delete();
				}
			}

			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/articleForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}

		return resEnt;
	}

	// 새글쓰기시 (다중) 이미지 업로드 기능 (업로드한 파일 이름들 얻은 후 반환함)
	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws ServletException, IOException {
		multipartRequest.setCharacterEncoding("utf-8");

		List<String> fileList = new ArrayList<>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFilename = mFile.getOriginalFilename();

			if (originalFilename != "" && originalFilename != null) {
				fileList.add(originalFilename); // 첨부한 이미지 파일의 이름들을 차례대로 저장함
				File file = new File(ARTICLE_IMAGE_REPO + "/" + fileName);
				if (mFile.getSize() != 0) {
					if (!file.exists()) {
						file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들 생성
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + originalFilename)); // 임시로
						// 저장된 MultipartFile을 실제 파일로 전송
					}
				}
			}
		}

		return fileList;
	}

	// 글 (다중 이미지) 상세보기
	@Override
	@RequestMapping(value = "/board/viewArticle.do", method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, // 조회할 글 번호를 가져옴
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");

		Map<String, Object> articleMap = boardService.viewArticle(articleNO); // 조회할 글 정보,이미지파일 정보를 articleMap에 설정

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("articleMap", articleMap);

		return mav;
	}

	// (다중 이미지)수정 기능
	@Override
	@RequestMapping(value = "/board/modArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {

		multipartRequest.setCharacterEncoding("utf-8");

		Map<String, Object> articleMap = new HashMap<>();

		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();

			if (name.equals("imageFileNO")) {
				String[] values = multipartRequest.getParameterValues(name);
				articleMap.put(name, values);
			} else if (name.equals("oldFileName")) {
				String[] values = multipartRequest.getParameterValues(name);
				articleMap.put(name, values);
			} else {
				String value = multipartRequest.getParameter(name);
				articleMap.put(name, value);
			}
		}

		// 수정한 이미지 파일을 업로드함
		List<String> fileList = uploadModImageFile(multipartRequest);

		// 수정시 새로 추가된 이미지 수
		int added_img_num = Integer.parseInt((String) articleMap.get("added_img_num"));

		// 기존 이미지 수
		int pre_img_num = Integer.parseInt((String) articleMap.get("pre_img_num"));

		List<ImageDTO> imageFileList = new ArrayList<>();
		List<ImageDTO> modAddImageFileList = new ArrayList<>();

		if (fileList != null && fileList.size() != 0) {
			String[] imageFileNO = (String[]) articleMap.get("imageFileNO");

			for (int i = 0; i < added_img_num; i++) {
				String fileName = fileList.get(i);
				ImageDTO imageDTO = new ImageDTO();
				if (i < pre_img_num) { // 기존의 이미지를 수정해서 첨부한 이미지들
					imageDTO.setImageFileName(fileName);
					imageDTO.setImageFileNO(Integer.parseInt(imageFileNO[i]));
					imageFileList.add(imageDTO);
					articleMap.put("imageFileList", imageFileList);
				} else { // 새로 추가한 이미지들
					imageDTO.setImageFileName(fileName);
					modAddImageFileList.add(imageDTO); // ??
					articleMap.put("modAddImageFileList", modAddImageFileList);
				}
			}

		}

		String articleNO = (String) articleMap.get("articleNO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			boardService.modArticle(articleMap);

			if (fileList != null && fileList.size() != 0) {
				for (int i = 0; i < fileList.size(); i++) {
					String fileName = fileList.get(i);

					if (i < pre_img_num) {
						if (fileName != null) {
							File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + fileName);
							File destFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO);
							FileUtils.moveFileToDirectory(srcFile, destFile, true);

							String[] oldName = (String[]) articleMap.get("oldFileName");
							String oldFileName = oldName[i];

							File oldFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO + "/" + oldFileName);
							oldFile.delete();
						}
					} else {
						if (fileName != null) {
							File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + fileName);
							File destFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO);
							FileUtils.moveFileToDirectory(srcFile, destFile, true);
						}
					}

				}
			}

			message = "<script>";
			message += " alert('글을 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?articleNO="
					+ articleNO + "';";
			message += "</script>";

			// 새 글을 추가한 후 메시지를 전달함
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (fileList != null && fileList.size() != 0) {
				// 오류 발생시 temp폴더의 이미지들 모두 삭제
				for (int i = 0; i < fileList.size(); i++) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + fileList.get(i));
					srcFile.delete();
				}
			}

			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?articleNO="
					+ articleNO + "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}

		return resEnt;
	}

	// 수정시 다중 이미지 업로드하기
	private List<String> uploadModImageFile(MultipartHttpServletRequest multipartRequest)
			throws Exception, IOException {

		List<String> fileList = new ArrayList<>();
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();

			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			if (originalFileName != "" && originalFileName != null) {
				fileList.add(originalFileName);

				File file = new File(ARTICLE_IMAGE_REPO + "/" + fileName);
				if (mFile.getSize() != 0) {
					if (!file.exists()) {
						file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들 생성
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "/" + "temp" + "/" + originalFileName)); // 임시로
						// 저장된 MultipartFile을 실제 파일로 전송
					}
				}

			} else { // 첨부한 이미지가 없었을 경우
				fileList.add(null);
			}
		}

		return fileList;
	}

	@Override
	@RequestMapping(value = "/board/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=utf-8");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String message;
		ResponseEntity resEnt = null;

		try {
			boardService.removeArticle(articleNO); // 글 번호 전달해서 글 삭제함

			File destDir = new File(ARTICLE_IMAGE_REPO + "/" + articleNO);
			FileUtils.deleteDirectory(destDir); // 첨부된 이미지 파일이 저장된 폴더도 삭제함

			message = "<script>";
			message += "alert('글을 삭제했습니다.');";
			message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += "</script>";
			// 글을 삭제 후 메세지를 전달함
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
			message += "</script>";

			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}

		return resEnt;
	}

	// 게시글 수정하기에서 각 이미지 삭제 기능
	@Override
	@RequestMapping(value = "/board/removeModImage.do", method = RequestMethod.POST)
	public void removeModImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String imageFileNO = request.getParameter("imageFileNO");
		String imageFileName = request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");

		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setArticleNO(Integer.parseInt(articleNO));
		imageDTO.setArticleNO(Integer.parseInt(imageFileNO));

		boardService.removeModImage(imageDTO);

		File oldFile = new File(ARTICLE_IMAGE_REPO + "/" + articleNO + "/" + imageFileName);
		oldFile.delete();

		out.print("success");
	}

}
