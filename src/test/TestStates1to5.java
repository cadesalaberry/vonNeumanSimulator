package test;

import static org.junit.Assert.*;

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

}
