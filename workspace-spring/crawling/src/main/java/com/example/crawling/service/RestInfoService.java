package com.example.crawling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crawling.dao.RestInfoDAO;
import com.example.crawling.dto.RestInfoDTO;

@Service
public class RestInfoService {
	
	@Autowired
	RestInfoDAO restInfoDAO;
	
	
	public List<RestInfoDTO> listURL(){
		System.out.println("check! Service!");
		List<RestInfoDTO> URLList = restInfoDAO.selectAllUrlList();
		
		return URLList;
	}
}
