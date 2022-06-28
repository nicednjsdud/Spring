package kr.co.ezenac.di02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
		// 실행시 person.xml을 읽어 들여 빈을 생성
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		
		PersonService ac = (PersonService) factory.getBean("personService1");
		PersonService ac1 = (PersonService) factory.getBean("personService2");
		// 자바 코드에서 객체 직접 생성하지 않아도 됨.
		//PersonService personService = new PersonServiceImpl();
		
		ac.sayHello();
		ac1.sayHello();
	}
}
