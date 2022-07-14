package kr.co.board.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.service.BoardService;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {

	// 이미지 저장 위
	private static String ARTICLE_IMAGE_REPO = "/Users/jeong-won-yeong/Documents/Spring/workspace-spring/imageRepo/";

	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleDTO articleDTO;

	@Override
	@RequestMapping(value = "/board/listArticles.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 인터셉터에서 전달된 뷰 이름 가져옴
		String viewName = (String)request.getAttribute("viewName");
		List<ArticleDTO> articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		// 조회한 글 정보를 바인딩 후 jsp로 전달함
		mav.addObject("articlesList",articlesList);
		
		return mav;
	}
}









