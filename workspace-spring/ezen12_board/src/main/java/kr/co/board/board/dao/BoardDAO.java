package kr.co.board.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import kr.co.board.board.dto.ArticleDTO;

public interface BoardDAO {
	public List<ArticleDTO> selectAllArticlesList() throws DataAccessException;
}
