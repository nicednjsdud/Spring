package kr.co.ezenac.board.service;

import java.util.List;
import java.util.Map;

import kr.co.ezenac.board.dto.ArticleDTO;

public interface BoardService {

	public List<ArticleDTO> listArticles() throws Exception;

	public int addNewArticle(Map articleMap) throws Exception;

	public Map<String, Object> viewArticle(int articleNO) throws Exception;

	public void modArticle(Map<String,Object> articleMap) throws Exception;

}
