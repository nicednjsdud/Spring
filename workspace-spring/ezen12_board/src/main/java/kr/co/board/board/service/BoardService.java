package kr.co.board.board.service;

import java.util.List;

import kr.co.board.board.dto.ArticleDTO;

public interface BoardService {

	public List<ArticleDTO> listArticles() throws Exception;

}
