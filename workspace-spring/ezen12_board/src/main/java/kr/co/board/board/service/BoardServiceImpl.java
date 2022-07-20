package kr.co.board.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.board.dao.BoardDAO;
import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	@Override
	public List<ArticleDTO> listArticles() throws Exception {
		List<ArticleDTO> articleList = boardDAO.selectAllArticlesList();
		return articleList;
	}


	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		// dao 호출
		int articleNo = boardDAO.insertNewArticle(articleMap);		// 글 정보를 저장한 후 글번호를 가져옴.
		articleMap.put("articleNo",articleNo);						// 글번호를 articleMap에 저장한 후
		boardDAO.insertNewImage(articleMap);						// 이미지정보를 저장함
		return articleNo;
	}


	@Override
	public Map<String, Object> viewArticle(int articleNO) throws Exception {
		
		Map<String,Object> articleMap = new HashMap<>();
		ArticleDTO article = boardDAO.selectArticle(articleNO);
		
		// 이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileLIst(articleNO);
		
		articleMap.put("article", article);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
}
