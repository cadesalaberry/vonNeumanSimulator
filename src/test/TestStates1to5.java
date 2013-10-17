package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import simulator.Computer;

public class TestStates1to5 {

	@Test
	public void testJUMPRoutine() {

		String testStr[] = { "JUMP 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };

		Computer computer = new Computer(testStr);

		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
	}

	/**
	 * Assumes that LOADA is working.
	 */
	@Test
	public void testJUMPZRoutine() {

		String testStr[] = { "LOADA 4", "JUMPZ 3", "FAILED", "PASSED", "0" };
		String xpctBUS[] = { "3", "ACK", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));

		String testStr2[] = { "LOADA 4", "JUMPZ 3", "PASSED", "FAILED", "1" };
		String xpctBUS2[] = { "2", "ACK", "PASSED" };

		Computer computer2 = new Computer(testStr2);
		computer2.simulate(3 * testStr2.length);

		assertTrue(Routine.busStateIsMatching(computer2.bus, xpctBUS2));
	}

	/**
	 * Assumes that LOADA is working.
	 */
	@Test
	public void testSTOREARoutine() {

		String testStr[] = { "LOADA 3", "STOREA 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADA 3", "STOREA 2", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Assumes that LOADA is working.
	 */
	@Test
	public void testMOVEABRoutine() {

		String testStr[] = { "LOADA 3", "LOADB 4", "MOVEAB", "PASSED", "4" };
		String xpctBUS[] = { "3", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADA 3", "LOADB 4", "MOVEAB", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(4 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Assumes that STOREA is working.
	 */
	@Test
	public void testLOADABRoutine() {

		String testStr[] = { "LOADA 3", "STOREA 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADA 3", "STOREA 2", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	@Test
	public void testINPUTRoutine() {

		String testStr[] = { "INPUT 1", "FAILED" };
		String xpctBUS[] = { "1", "ACK", "PASSED" };
		String xpctRAM[] = { "INPUT 1", "PASSED" };

		InputStream in_orig = System.in;
		PrintStream out_orig = System.out;
		InputStream in = new ByteArrayInputStream("PASSED".getBytes());
		PrintStream out = new PrintStream(new ByteArrayOutputStream());
		System.setIn(in);
		System.setOut(out);

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);
		System.setIn(in_orig);
		System.setOut(out_orig);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	@Test
	public void testOUTPUTRoutine() {

		String testStr[] = { "OUTPUT 1", "PASSED" };
		String xpctBUS[] = { "1", "ACK", "PASSED" };
		String xpctRAM[] = { "OUTPUT 1", "PASSED" };
		String xpctOut   = "\n> PASSED\n";

		InputStream in_orig = System.in;
		PrintStream out_orig = System.out;
		InputStream in = new ByteArrayInputStream("PASSED".getBytes());
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOut);
		System.setIn(in);
		System.setOut(out);

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);
		System.setIn(in_orig);
		System.setOut(out_orig);

		System.out.println(byteArrayOut);
		assertEquals(byteArrayOut.toString(), xpctOut);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}
}
