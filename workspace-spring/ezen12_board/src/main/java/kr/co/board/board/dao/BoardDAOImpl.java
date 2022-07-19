package kr.co.board.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.co.board.board.dto.ArticleDTO;
import kr.co.board.board.dto.ImageDTO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ArticleDTO> selectAllArticlesList() throws DataAccessException {
		List<ArticleDTO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle", articleMap);
		return articleNO;
	}

	// 가게시글 번호의 MAX값 + 1 구함

	private int selectNewArticleNO() {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}

	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		
		List<ImageDTO> imageFileList =(List<ImageDTO>) articleMap.get("imageFileList");
		int articleNO = (Integer)articleMap.get("articleNO");
		
		int imageFileNO = selectNewImageFileNO();
		
		if(imageFileList !=null && imageFileList.size() !=0) {
			for(ImageDTO imageDTO : imageFileList) {
				imageDTO.setImageFileNO(++imageFileNO);
				imageDTO.setArticleNO(articleNO);
			}
			// T_IMAGEFILE 테이블에 INSERT
			sqlSession.insert("mapper.board.insertNewImage",imageFileList);
		}
		
	}

	private int selectNewImageFileNO() {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}

	@Override
	public ArticleDTO selectArticle(int articleNO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.board.selectArticle",articleNO);
	}
}







