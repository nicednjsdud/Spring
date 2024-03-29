package com.example.crawling;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.crawling.dto.RestInfoDTO;
import com.example.crawling.service.RestInfoService;

public class RestInfoCrawler {
	
	@Autowired
	private static RestInfoService restInfoService;
	
	@Autowired
	private static SqlSession sqlsession;

	public static void main(String[] args) throws IOException {
		
		try (InputStream in = Resources.getResourceAsStream("mybatis/model/modelConfig.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

            try (SqlSession session = factory.openSession()) {
                List<RestInfoDTO> result = session.selectList("mapper.crawler.selectAllRest");
                System.out.println(result);
            }
        }
		
//		List<RestInfoDTO> lists = restInfoService.listURL();
//		System.out.println(lists);
		

		try {
			String url = "https://www.mangoplate.com/restaurants/iXobcHXQf6";
			Connection conn = Jsoup.connect(url);

			Document html = conn.get();
			// 식당이름
			Element _restaurant_name = html.getElementsByClass("restaurant_name").get(0);
			String restaurant_name = _restaurant_name.text();

			// 주소
			Element address_info = html.getElementsByClass("only-desktop").get(0);
			// 도로명 주소
			String address = address_info.getElementsByTag("td").get(0).text().split("지번")[0];

			// 지번
			String jibunAddress = address_info.getElementsByTag("span").get(1).text();

			// 전화번호
			Element phone_info = html.getElementsByClass("only-desktop").get(1);
			String phone = phone_info.getElementsByTag("td").get(0).text();

			// 음식 종류
			Element menu_info = html.getElementsByTag("tr").get(2);
			String menu = menu_info.getElementsByTag("span").get(0).text();

			// 가격대
			Element price_info = html.getElementsByTag("tr").get(3);
			String price = price_info.getElementsByTag("td").get(0).text();

			// 가격대
			Element park_ok = html.getElementsByTag("tr").get(4);
			String park = park_ok.getElementsByTag("td").get(0).text();

			// 영업 시간
			Element open_info = html.getElementsByTag("tr").get(5);
			String open = open_info.getElementsByTag("td").get(0).text();

			// 음식 리스트 / 가격
			Elements _menu_list = html.getElementsByClass("Restaurant_MenuItem");
			String menu_list = "";
			if (!_menu_list.isEmpty()) {
				for (int i = 0; i < _menu_list.size(); i++) {
					menu += _menu_list.get(i).text() + "|";
				}
			}
			// 태그
			Elements tag_info = html.getElementsByClass("tag-item");
			String tag = "";
			if (!tag_info.isEmpty()) {
				for (int i = 0; i < tag_info.size(); i++) {
					tag += tag_info.get(i).text() + " ";
				}
			}

			// 각 URL당 하나씩 db에 들어감
			// 이미지 파일은 따로 크롤러 생성 (복잡)
			System.out.print(restaurant_name + " | " + address + " | " + jibunAddress + " | " + phone + " | " + menu
					+ " | " + price + " | " + park + " | " + open + " | " + menu_list + " | " + tag);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
