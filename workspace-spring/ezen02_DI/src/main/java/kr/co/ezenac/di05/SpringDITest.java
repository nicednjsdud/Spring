package kr.co.ezenac.di05;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

class Engine {
}

class Door {
}

class Car {
	String color;
	int oil;

	public Car() {
	}

	Engine engine;
	Door[] doors;

	public Car(String color, int oil, Engine engine, Door[] doors) {
		super();
		this.color = color;
		this.oil = oil;
		this.engine = engine;
		this.doors = doors;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setOil(int oil) {
		this.oil = oil;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setDoors(Door[] doors) {
		this.doors = doors;
	}

	@Override
	public String toString() {
		return "Car{" + "color : " + this.color + ", oil : " + this.oil + ", engine : " + this.engine + ", door : "
				+ Arrays.toString(doors) + "}";
	}
}

@Component
public class SpringDITest {

	public static void main(String[] args) {
		
		ApplicationContext ac = new GenericXmlApplicationContext("config2.xml");
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


















