package test;

import simulator.Bus;
import simulator.Computer;

public class Routine {

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

	public static boolean busStateIsMatching(Bus b, String[] xpected) {

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
