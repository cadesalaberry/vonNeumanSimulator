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

		routinesAreMatching(test, xpct);
	}

	public boolean routinesAreMatching(String[] testStr, String[][] xpected) {

		Computer computer = new Computer(testStr);
		Bus b = computer.bus;
		boolean passed = true;

		for (int i = 0; i < xpected.length && passed; i++) {
			computer.simulate(i);
			passed = busStateIsMatching(b, xpected[i]);
		}

		return passed;
	}

	public boolean busStateIsMatching(Bus b, String[] xpected) {

		String addressStr = Integer.toString(b.address);

		String[] output = { addressStr, b.command, b.data };

		for (int i = 0; i < output.length; i++) {
			if (xpected[i] == null && output[i] == null) {

			} else if (!xpected[i].equals(output[i])) {
				System.out.println("Error@" + i
						+ ": bus state is not matching expected value."
						+ "\nHas Found: " + output[i] + "\nExpecting: "
						+ xpected[i]);
				return false;
			}
		}
		return true;
	}

	public boolean printRoutine(String[] testStr) {

		Computer computer = new Computer(testStr);
		Bus b = computer.bus;
		boolean passed = true;

		System.out.print("{");
		for (int i = 0; i < 100 && passed; i++) {
			computer.simulate(i);
			System.out.print("{ \"" + b.address);
			System.out.print("\", \"" + b.command);
			System.out.print("\", \"" + b.data + "\" }\n");
		}
		System.out.print("}");

		return passed;
	}
}
