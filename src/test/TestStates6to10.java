package test;

import static org.junit.Assert.*;
import simulator.*;

import org.junit.Test;

public class TestStates6to10 {
	
//	private static final String test2[] = { "INPUT 10", "LOADA 10", "JUMPZ 1" };
	
//	//test for JUMPZ
//	@Test
//	public void test2() {
//		System.out.println("Executing Test 2\n");
//		Computer computer = new Computer(test2);
//		Bus b = computer.bus;
//		for(int i=0; i<test2.length*2; i++) {
//			computer.simulate(test2.length);
//			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
//		}
//		//computer.simulate(test1.length);
//		//System.out.println("command:" + b.command + "\t" + b.address + "\t" + b.data);
//		assertEquals(1, b.address);
//	}
//	
//	private static final String test3[] = { "INPUT 10", "LOADA 10", "STOREA 7", "JUMP 7" };
//	
//	//test for STOREA
//	@Test
//	public void test3() {
//		System.out.println("\nExecuting Test 3\n");
//		Computer computer = new Computer(test3);
//		Bus b = computer.bus;
//		for(int i=0; i<test3.length*2; i++) {
//			computer.simulate(test3.length);
//			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
//		}
//		//computer.simulate(test1.length);
//		//System.out.println("command:" + b.command + "\t" + b.address + "\t" + b.data);
//		assertEquals(new Integer(13), Integer.valueOf(b.data));
//	}
	
	private static final String test6[] = { "INPUT 0", "LOADA 0", "JUMPZ 7" };
	
	//test for INPUT
	@Test
	public void test6() {
		System.out.println("\nExecuting Test 6\n");
		
		Computer computer = new Computer(test6);
		Bus b = computer.bus;
		
		for(int i=0; i<test6.length*2; i++) {
			computer.simulate(test6.length);
			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
		}
		
		assertEquals(7, b.address);
	}
	
	private static final String test7[] = { "FREAD 0" };
	
	//test for OUTPUT
	@Test
	public void test7() {
		System.out.println("\nExecuting Test 7\n");
		
		Computer computer = new Computer(test7);
		Bus b = computer.bus;
		
		initRamWithValue(computer, 0, "~/Downloads/ubuntu.img");
		
		for(int i=0; i<test7.length*2; i++) {
			computer.simulate(test7.length);
			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
		}
		
		assertEquals("hi", b.data);
	}
	
	
	
	//helper method to initialze RAM with a value
	private void initRamWithValue(Computer computer, int index, String value) {
		String[] initValues = new String[256];
		initValues[index] = value;
		computer.ram.initialize(initValues);
	}

}
