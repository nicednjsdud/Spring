package com.example.crawling.dto;

import org.springframework.stereotype.Component;

@Component("restInfoDTO")
public class RestInfoDTO {
	private int urlNO;
	private String name;
	private String url;
	public RestInfoDTO() {
		
	}
	public RestInfoDTO(int urlNO, String name, String url) {
		this.urlNO = urlNO;
		this.name = name;
		this.url = url;
	}
	public int getUrlNO() {
		return urlNO;
	}
	public void setUrlNO(int urlNO) {
		this.urlNO = urlNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}