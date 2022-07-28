package kr.co.board.common.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	public static void main(String[] args) {
		
		try {
			String url = "https://www.mangoplate.com/restaurants/dDLnc_MZ1MwG";
			Connection conn = Jsoup.connect(url);
			
			Document html = conn.get();
			// 식당이름
			Element _restaurant_name = html.getElementsByClass("restaurant_name").get(0);
			String restaurant_name = _restaurant_name.text();
			System.out.println(restaurant_name);
			
			// 주소
			Element address_info = html.getElementsByClass("only-desktop").get(0);
			// 도로명 주소
			String address = address_info.getElementsByTag("td").get(0).text().split("지번")[0];
			System.out.println(address);
			// 지번
			String jibunAddress = address_info.getElementsByTag("span").get(1).text();
			System.out.println(jibunAddress);
			
			// 전화번호
			Element phone_info = html.getElementsByClass("only-desktop").get(1);
			String phone = phone_info.getElementsByTag("td").get(0).text();
			System.out.println(phone);
			
			// 음식 종류
			Element menu_info = html.getElementsByTag("tr").get(2);
			String menu = menu_info.getElementsByTag("span").get(0).text();
			System.out.println(menu);
			
			// 영업 시간
			Element open_info = html.getElementsByTag("tr").get(5);
			String open = open_info.getElementsByTag("td").get(0).text();
			System.out.println(open);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
