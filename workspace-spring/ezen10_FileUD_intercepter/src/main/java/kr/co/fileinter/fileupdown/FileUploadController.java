package kr.co.fileinter.fileupdown;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	//파일 저장 위치 지정
	private static final String CURR_IMAGE_PEPO_PATH = "/Users/jeong-won-yeong/Documents/Spring/workspace-spring/imageRepo";
	
	@RequestMapping(value = "/form")
	public String form() {
		return "uploadForm";		//업로드창인 uploadForm.jsp를 반환함
	}
	
	@RequestMapping(value = "/upload" , method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) 
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		
		//매개변수 정보와 파일 정보를 저장할 Map을 생성함
		Map map = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		
		//전송된 매개변수 값을 key/value로 map에 저장함
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
		}
		
		
		//파일을 업로드한 후 반환된 파일이름이 저장된 fileList를 다시 map 저장함
		List<String> fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);
		
		//map을 결과창으로 포워딩함
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("result");
		
		return mav;
	}

	//첨부한 파일 이름이 저장된 fileList 리턴함
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList = new ArrayList<>();
		
		//첨부된 파일 이름 가져옴
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			//파일 이름에 대한 MultipartFile 객체를 가져옴
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFilename =  mFile.getOriginalFilename();			//실제 파일 이름 가져옴
			
			fileList.add(originalFilename);									//파일 이름을 하나씩 fileList에 저장
			
			File file = new File(CURR_IMAGE_PEPO_PATH +"\\"+ fileName);
			if(mFile.getSize() != 0) {										//첨부된 파일이 있는지 체크
				if(!file.exists()) {										//경로에 파일이 없으면 그 경로에 해당하는 
					if(file.getParentFile().mkdirs()) {						//디렉토리를 만든 후 파일을 생성함					
						file.createNewFile();
					}
				}
				//임시로 저장된 MultipartFile을  실제 파일로 전송
				mFile.transferTo(new File(CURR_IMAGE_PEPO_PATH +"\\"+ originalFilename));
			}		
		}		
		return fileList;			//첨부한 파일 이름이 저장된 fileList 반환함 	
	}
	
}





















