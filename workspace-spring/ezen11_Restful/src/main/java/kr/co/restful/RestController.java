package kr.co.restful;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {

	@RequestMapping(value = "/res1")
	@ResponseBody			// 데이터 전송하도록 설정
	public Map<String, Object> res1() {	//Map 데이터를 브라우저로 전송함
		Map<String, Object> map = new HashMap<>();
		map.put("id", "bob");
		map.put("name", "이젠");

		return map;
	}
	@RequestMapping(value="/res2")
	public ModelAndView res2() {
		return new ModelAndView("home");
	}
}
