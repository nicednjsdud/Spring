package kr.co.ezenac.di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
		// 실행시 person.xml을 읽어 들여 빈을 생성
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		// id가 personService인 빈 가져옴
		PersonService ac = (PersonService) factory.getBean("personService");
		
		// 자바 코드에서 객체 직접 생성하지 않아도 됨.
		//PersonService personService = new PersonServiceImpl();
		
		// 생성된 빈을 이용해 name값 출력
		ac.sayHello();
		
		

	}

}
