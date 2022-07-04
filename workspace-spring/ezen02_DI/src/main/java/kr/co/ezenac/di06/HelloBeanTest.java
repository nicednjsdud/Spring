package kr.co.ezenac.di06;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new GenericXmlApplicationContext("beans.xml");
		Hello hello = (Hello) ac.getBean("hello");
		List<String> list = hello.getNames();
		for(String value : list) {
			System.out.println(value);
		}
	}

}
