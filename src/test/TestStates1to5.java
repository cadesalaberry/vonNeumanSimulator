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
	public void test() {

		String test[] = { "JUMP 1", "JUMP 5", "", "", "", "JUMP 1" };
		String xpct[][] = { { "0", null, null }, { "0", "ACK", "JUMP 1" },
				{ "0", "ACK", "JUMP 1" }, { "1", "ACK", "JUMP 5" },
				{ "5", "ACK", "JUMP 1" }, { "1", "ACK", "JUMP 5" },
				{ "1", "ACK", "JUMP 5" }, { "5", "ACK", "JUMP 1" },
				{ "5", "ACK", "JUMP 1" }, { "1", "ACK", "JUMP 5" },
				{ "1", "ACK", "JUMP 5" }, { "5", "ACK", "JUMP 1" } };

		Computer computer = new Computer(test);
		Bus b = computer.bus;

		assertTrue(Routine.areMatching(test, xpct));
	}

}
