package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import simulator.Bus;
import simulator.Computer;

public class TestStates1to5 {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testJUMPRoutine() {

		String testStr[] = { "JUMP 2", "FAILED", "PASSED"};
		String xpctBUS[] ={ "2", "ACK", "PASSED" };
		
		Computer computer = new Computer(testStr);

		computer.simulate(10);
				
		assertTrue(Routine.busStateIsMatching(computer.bus, xpctBUS));
	}
	
	public void testJUMPZRoutine() {

		String test[] = { "JUMPZ 1", "JUMPZ 5", "", "", "", "JUMP 1" };
		String xpct[][] = { { "0", null, null }, // S0
				{ "0", "ACK", "JUMP 1" }, // S1
				{ "0", "ACK", "JUMP 1" }, // S16
				{ "1", "ACK", "JUMP 5" }, // S0
				{ "5", "ACK", "JUMP 1" }, // S1
				{ "1", "ACK", "JUMP 5" }, // S16
				{ "1", "ACK", "JUMP 5" }, // S0
				{ "5", "ACK", "JUMP 1" }, // S1
				{ "5", "ACK", "JUMP 1" }, // S16
		};

		assertTrue(Routine.areMatching(test, xpct));
	}

}
