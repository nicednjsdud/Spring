package kr.co.board.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

public interface BoardDAO {
	public List<ArticleDTO> selectAllArticlesList() throws DataAccessException;

	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public void insertNewImage(Map articleMap) throws DataAccessException;

	public ArticleDTO selectArticle(int articleNO) throws DataAccessException;

	public List<ImageDTO> selectImageFileLIst(int articleNO) throws DataAccessException;
}
