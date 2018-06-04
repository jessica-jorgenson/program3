package pro3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Part2Testing {

	@Test
	void test1() {
		int leVal = 42;
		Integer[] doms = { 25, 10, 5, 1 };
		Integer[] doesItWork;
		try {
			Integer[] answer = { 25, 10, 5, 1, 1 };
			doesItWork = Part2.getMinCount(doms, leVal);
			System.out.println(answer.length + " " + doesItWork.length);
			System.out.println(Arrays.equals(answer, doesItWork));
			boolean fuck = Arrays.equals(answer, doesItWork);
			System.out.println("FUCK: " + fuck);
			for (Integer leInt : answer) {
				System.out.print(leInt + "-");
			}
			System.out.println();
			for (Integer leInt : doesItWork) {
				System.out.print(leInt + "-");
			}
			System.out.println();
			assertTrue(fuck);
			fail("1 WRONG");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Coin array empty");
		}

	}

	@Test
	void test2() {
		int leVal = 46;
		Integer[] doms = { 1, 2, 4, 8, 16 };
		Integer[] doesItWork;
		try {
			doesItWork = Part2.getMinCount(doms, leVal);
			Integer[] answer = { 16, 16, 8, 4, 2 };
			System.out.println(answer.equals(doesItWork));
			assertTrue(answer.equals(doesItWork));
			System.out.println("MADE 2");
			for (Integer leInt : answer) {
				System.out.print(leInt + "-");
			}
			System.out.println();
			for (Integer leInt : doesItWork) {
				System.out.print(leInt + "-");
			}
			fail("2 WRONG");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Coin array empty");
		}

	}

	@Test
	void test3() {
		int leVal = 50;
		Integer[] doms = {};
		Integer[] doesItWork;
		try {
			doesItWork = Part2.getMinCount(doms, leVal);
			Integer[] answer = { 16, 16, 8, 4, 2 };
			assertArrayEquals(answer, doesItWork);
			System.out.println("MADE 3");
			for (Integer leInt : answer) {
				System.out.print(leInt + " ");
			}
			System.out.println();
			for (Integer leInt : doesItWork) {
				System.out.print(leInt + " ");
			}
			fail("3 WRONG");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Coin array empty");
		}

	}

	@Test
	void test4() {
		int leVal = 500;
		Integer[] doms = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 83 };
		Integer[] doesItWork;
		try {
			doesItWork = Part2.getMinCount(doms, leVal);
			Integer[] answer = { 83, 83, 83, 83, 83, 83, 19, 19, 7 };
			assertArrayEquals(answer, doesItWork);
			System.out.println("MADE 4");
			for (Integer leInt : answer) {
				System.out.print(leInt + " ");
			}
			System.out.println();
			for (Integer leInt : doesItWork) {
				System.out.print(leInt + " ");
			}
			fail("4 WRONG");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Coin array empty");
		}

	}

}
