package kr.co.board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.board.dao.BoardDAO;
import kr.co.board.board.dto.ArticleDTO;

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
}
