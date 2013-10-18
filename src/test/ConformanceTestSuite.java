package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import simulator.Computer;

public class ConformanceTestSuite {

	/**
	 * Covers test case #1 - JUMP.
	 */
	@Test
	public void testJUMPRoutine() {

		String testStr[] = { "JUMP 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };

		Computer computer = new Computer(testStr);

		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
	}

	/**
	 * Covers test case #2 - LOADA. Assumes that LOADA is working.
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
	 * Covers test case #3 - STOREA. Assumes that LOADA is working.
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
	 * Covers test case #4 - MOVEAB. Assumes that LOADA is working.
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
	 * Covers test case #5 - LOADA. Assumes that STOREA is working.
	 */
	@Test
	public void testLOADARoutine() {

		String testStr[] = { "LOADA 3", "STOREA 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADA 3", "STOREA 2", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Covers test case #6 - INPUT.
	 */
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

	/**
	 * Covers test case #7 - OUTPUT.
	 */
	@Test
	public void testOUTPUTRoutine() {

		String testStr[] = { "OUTPUT 1", "PASSED" };
		String xpctBUS[] = { "1", "ACK", "PASSED" };

		String xpctOut = "\n> PASSED\n";

		InputStream in_orig = System.in;
		PrintStream out_orig = System.out;
		InputStream in = new ByteArrayInputStream("".getBytes());
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOut);
		System.setIn(in);
		System.setOut(out);

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);
		System.setIn(in_orig);
		System.setOut(out_orig);

		assertEquals(byteArrayOut.toString(), xpctOut);
		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
	}

	/**
	 * Covers test case #8 and #9 - FOPEN and FREAD.
	 */
	@Test
	public void testFOPENandFREADRoutine() {

		String testStr[] = { "FOPEN 2", "FREAD 2", "assets/TestFile" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "FOPEN 2", "FREAD 2", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(5 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Covers test case #10 - FCLOSE. Assumes FOPEN and FREAD are working. It
	 * will print an error because BufferedReader has been closed, but the test
	 * will still pass.
	 * 
	 */
	@Test
	public void testFCLOSERoutine() {

		String testStr[] = { "FOPEN 3", "FCLOSE", "FREAD 3", "assets/TestFile" };
		String xpctBUS[] = { "2", "FREAD", "FREAD 3" };
		String xpctRAM[] = { "FOPEN 3", "FCLOSE", "FREAD 3", "assets/TestFile" };

		Computer computer = new Computer(testStr);
		computer.simulate(10 * testStr.length);
		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));

	}
/*
	@Test
	public void ultraCodeCoverer() {

		String testStr[] = { "HALT"};

		Computer computer = new Computer(testStr);
		computer.simulate(10 * testStr.length);
		computer.cpu.dump();
	}*/

	@Test
	public void carlos4Lyfe() {

		String testStr[] = { "LOADA 1", "INPUT", "OUTPUT", "HALT"};
		
		InputStream in_orig = System.in;
		PrintStream out_orig = System.out;
		InputStream in = new ByteArrayInputStream("PASSED".getBytes());
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOut);
		System.setIn(in);
		System.setOut(out);

		Computer computer = new Computer(testStr);
		computer.simulate(10 * testStr.length);
		System.setIn(in_orig);
		System.setOut(out_orig);
		
		computer.cpu.dump();
	}

	

	/**
	 * Assumes that STOREB is working.
	 */
	@Test
	public void testLOADBRoutine() {

		String testStr[] = { "LOADB 3", "STOREB 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADB 3", "STOREB 2", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Assumes that LOADB is working.
	 */
	@Test
	public void testSTOREBRoutine() {

		String testStr[] = { "LOADB 3", "STOREB 2", "FAILED", "PASSED" };
		String xpctBUS[] = { "2", "ACK", "PASSED" };
		String xpctRAM[] = { "LOADB 3", "STOREB 2", "PASSED", "PASSED" };

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Assumes that ADD is working.
	 */
	@Test
	public void testADDRoutine() {

		String testStr[] = { "LOADA 5", "LOADB 5", "ADD", "STOREA 4", "FAILED",
				"4" };
		String xpctBUS[] = { "4", "ACK", "8" };
		String xpctRAM[] = { "LOADA 5", "LOADB 5", "ADD", "STOREA 4", "8", "4" };

		Computer computer = new Computer(testStr);
		computer.simulate(4 * testStr.length);

		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
		assertTrue(Routine.ramStateIsMatching(computer.ram, xpctRAM));
	}

	/**
	 * Covers test case #7 - OUTPUT.
	 */
	@Test
	public void testOUTPUT2Routine() {

		String testStr[] = { "OUTPUT 1", "PASSED" };
		String xpctBUS[] = { "1", "ACK", "PASSED" };

		String xpctOut = "\n> PASSED\n";

		InputStream in_orig = System.in;
		PrintStream out_orig = System.out;
		InputStream in = new ByteArrayInputStream("".getBytes());
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(byteArrayOut);
		System.setIn(in);
		System.setOut(out);

		Computer computer = new Computer(testStr);
		computer.simulate(3 * testStr.length);
		System.setIn(in_orig);
		System.setOut(out_orig);

		assertEquals(byteArrayOut.toString(), xpctOut);
		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
	}
}
