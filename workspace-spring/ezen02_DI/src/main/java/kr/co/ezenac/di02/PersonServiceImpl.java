package kr.co.ezenac.di02;

public class PersonServiceImpl implements PersonService {
	
	private String name;
	private int age;
	
	
	// person.xml에서 인자가 한개인 생성자 설정시 사용
	public PersonServiceImpl(String name) {this.name = name;}
	
	// person.xml에서 인자가 두개인 생성자 설정시 사용
	public PersonServiceImpl(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public void sayHello() {
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
	}

	
}
