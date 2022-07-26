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
		articleMap.put("articleNO",articleNo);						// 글번호를 articleMap에 저장한 후
		boardDAO.insertNewImage(articleMap);						// 이미지정보를 저장함
		return articleNo;
	}


	@Override
	public Map<String, Object> viewArticle(int articleNO) throws Exception {
		
		Map<String,Object> articleMap = new HashMap<>();
		ArticleDTO articleDTO = boardDAO.selectArticle(articleNO);
		
		// 이미지 부분 정보 요청
		List<ImageDTO> imageFileList = boardDAO.selectImageFileLIst(articleNO);
		
		articleMap.put("article", articleDTO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}


	@Override
	public void modArticle(Map<String,Object> articleMap) throws Exception {
		
		boardDAO.updateArticle(articleMap);
		
		List<ImageDTO> imageFileList = (List<ImageDTO>)articleMap.get("imageFileList");
		List<ImageDTO> modAddImageFileList =(List<ImageDTO>)articleMap.get("modAddImageFileList");
		
		if(imageFileList !=null && imageFileList.size() !=0) {
			int added_img_num = Integer.parseInt((String)articleMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String)articleMap.get("pre_img_num"));
			
			if(pre_img_num < added_img_num) {		// 기존 이미지도 수정하고 새 이미지도 추가한 경우
				boardDAO.updateImageFile(articleMap);	// 기존 이미지 수정
				boardDAO.insertModNewImage(articleMap);	// 새 이미지 추가
			}
			else{										// 기존 이미지를 수정만 한 경우
				boardDAO.updateImageFile(articleMap);
			}
			
		}
		// 새 이미지를 추가한 경우
		else if(modAddImageFileList !=null && modAddImageFileList.size() !=0){										
			boardDAO.insertModNewImage(articleMap);
		}
	}


	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}


	@Override
	public void removeModImage(ImageDTO imageDTO) throws Exception {
		boardDAO.deleteModImage(imageDTO);
		
	}
}
