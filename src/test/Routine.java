package test;

import simulator.Bus;
import simulator.Computer;
import simulator.RAM;

/**
 * Helper method to compare the computer state after each CPU clock cycle.
 * 
 * @author cdesal
 * 
 */
public class Routine {

	/**
	 * Matches a all the values taken by the bus variables to the reference
	 * xpected routine.
	 * 
	 * @param testStr
	 * @param xpected
	 * @return
	 */
	public static boolean areMatching(String[] testStr, String[][] xpected) {

		Computer computer = new Computer(testStr);
		Bus b = computer.bus;
		boolean passed = true;

		for (int i = 0; i < xpected.length && passed; i++) {
			computer.simulate(i);
			passed = busStateIsMatching(b, xpected[i]);
		}

		return passed;
	}

	/**
	 * Compares the bus state to a reference state in xpected, of the form:
	 * 
	 * String xpct[][] = {{ "0", null, null }, { "0", "ACK", "JUMP 1" }}
	 * 
	 * @param b
	 * @param xpected
	 * @return
	 */
	public static boolean busStateIsMatching(Bus b, String[] xpected) {

		String addressStr = Integer.toString(b.address);

		String[] output = { addressStr, b.command, b.data };

		for (int i = 0; i < output.length; i++) {
			if (xpected[i] == null && output[i] == null) {

			} else if (!xpected[i].equals(output[i])) {
				System.out
						.println("Error: bus state is not matching expected value."
								+ "\nHas Found: "
								+ output[i]
								+ "\nExpecting: "
								+ xpected[i]);
				return false;
			}
		}
		return true;
	}

	public static boolean ramStateIsMatching(RAM r, String[] xpected) {

		boolean passed = true;

		for (int i = 0; i < xpected.length && passed; i++) {
			passed = r.cell(i).equals(xpected[i]);
		}
		return passed;
	}

	/**
	 * Print the routine followed by the computer. By routine, we mean the bus
	 * state at every CPU clock cycle.
	 * 
	 * @param testStr
	 * @return
	 */
	public static boolean print(String[] testStr) {

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
