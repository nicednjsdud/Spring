package kr.co.ezenac;

public class Calculator {

	public void add(int x, int y) {
		int result = x + y;
		System.out.println("결과 : " + result);
	}

	public void minus(int x, int y) {
		int result = x - y;
		System.out.println("결과 : " + result);
	}

	public void multi(int x, int y) {
		int result = x * y;
		System.out.println("결과 : " + result);
	}

	public void divide(int x, int y) {
		int result = x / y;
		System.out.println("결과 : " + result);
	}
}
