package com.example.crawling;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class crawling {

	public static void main(String[] args) {
		try {
		// 망고플레이트
		// 1. 수집 대상 URL
		String URL = "https://www.mangoplate.com/search/%EC%9D%B4%ED%83%9C%EC%9B%90";
		
		// 2. Connection 생성
		Connection conn = Jsoup.connect(URL);
		
		// 3. HTML 파싱.
		
			Document html = conn.get();
			
			System.out.println(html.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
