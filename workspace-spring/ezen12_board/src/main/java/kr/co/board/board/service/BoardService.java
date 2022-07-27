package kr.co.board.board.service;

import java.util.List;
import java.util.Map;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

public interface BoardService {

	public List<ArticleDTO> listArticles() throws Exception;

	public int addNewArticle(Map articleMap) throws Exception;

	public Map<String, Object> viewArticle(int articleNO) throws Exception;
	public Map<String, Object> viewArticle(Map<String, Object> viewMap) throws Exception;

	public void modArticle(Map<String,Object> articleMap) throws Exception;

	public void removeArticle(int articleNO) throws Exception;

	public void removeModImage(ImageDTO imageDTO) throws Exception;

	public int addReplyArticle(Map articleMap) throws Exception;

	public Map<String, Integer> listArticles(Map<String, Integer> pagingMap) throws Exception;

	

}
