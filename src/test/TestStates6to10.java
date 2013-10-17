package test;

import static org.junit.Assert.*;
import simulator.*;

import org.junit.Test;

public class TestStates6to10 {
	
	private static final String test2[] = { "INPUT 10", "LOADA 10", "JUMPZ 1" };
	
	//test for JUMPZ
	@Test
	public void test2() {
		System.out.println("Executing Test 2\n");
		Computer computer = new Computer(test2);
		Bus b = computer.bus;
		for(int i=0; i<test2.length*2; i++) {
			computer.simulate(test2.length);
			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
		}
		//computer.simulate(test1.length);
		//System.out.println("command:" + b.command + "\t" + b.address + "\t" + b.data);
		assertEquals(1, b.address);
	}
	
	private static final String test3[] = { "INPUT 10", "LOADA 10", "STOREA 7", "JUMP 7" };
	
	//test for STOREA
	@Test
	public void test3() {
		System.out.println("\nExecuting Test 3\n");
		Computer computer = new Computer(test3);
		Bus b = computer.bus;
		for(int i=0; i<test3.length*2; i++) {
			computer.simulate(test3.length);
			System.out.println(i + ": command:" + b.command + "\taddress:" + b.address + "\tdata:" + b.data);
		}
		//computer.simulate(test1.length);
		//System.out.println("command:" + b.command + "\t" + b.address + "\t" + b.data);
		assertEquals(new Integer(13), Integer.valueOf(b.data));
	}

}
