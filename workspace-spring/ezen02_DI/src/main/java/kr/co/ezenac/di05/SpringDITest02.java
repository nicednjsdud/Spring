package kr.co.ezenac.di05;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;



@Component
public class SpringDITest02 {

	public static void main(String[] args) {
		
		ApplicationContext ac = new GenericXmlApplicationContext("config3.xml");
		Car car = (Car) ac.getBean("car");			//byName
		Car car2 = ac.getBean("car",Car.class);		//byName
		Car car3 = ac.getBean(Car.class);			//byType
		
		System.out.println("car :"+car);
		System.out.println("car2 : "+car2);
		System.out.println("car3 : "+car3);
		
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean("door");
		
		System.out.println("Engine : "+engine );
		System.out.println("Door : "+door);
		
		System.out.println("======================================");
		
		car.setColor("blue");
		car.setOil(200);
		car.setEngine(engine);
		car.setDoors(new Door[] {ac.getBean("door",Door.class), (Door)ac.getBean("door")});
		
		System.out.println("car : "+car);
	}

}


















