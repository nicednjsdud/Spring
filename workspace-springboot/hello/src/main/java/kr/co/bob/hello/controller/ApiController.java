package kr.co.bob.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@RequestMapping("/bob")
	public String hello() {
		return "Hello BOB!";
	}
}
