package com.example.crawling.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crawling.dto.RestInfoDTO;

@Repository
public class RestInfoDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<RestInfoDTO> selectAllUrlList() {
		System.out.println("check!");
		List<RestInfoDTO> restInfoList = sqlSession.selectList("mapper.member.selectAllRestURL");

		return restInfoList;
	}
}
