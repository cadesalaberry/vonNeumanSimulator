/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package simulator;

import java.io.*;

/**
 * Simulates the functioning of the 
 * computer HardDisk.
 * It reads streams of data from a given 
 * file and copy them on the data bus
 */
public class HardDisk {
	private Bus bus;
	private BufferedReader lineReader; // the line reader of the file
	private String line; // one line in the file

	// the constructor receives a reference to the bus component
	public HardDisk(Bus p_bus) {
		bus = p_bus;
	}

	void execute() {
		if (bus.command.equals(Bus.FOPEN)) {
			// It opens the file indicated in the data bus
			try {
				FileInputStream inStream = new FileInputStream(bus.data);
				lineReader =
					new BufferedReader(new InputStreamReader(inStream));
				// Acknowledge the CPU of the command execution
				bus.command = Bus.ACK;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (bus.command.equals(Bus.FCLOSE)) {
			// It closes the line reader and the input stream
			try {
				lineReader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			bus.command = Bus.ACK;
		}
		if (bus.command.equals(Bus.FREAD)) {
			// It reads the next line in the file and writes the string on the data bus
			// If it has reached the end of the file, it writes the label "DONE"
			try {
				if ((line = lineReader.readLine()) != null) {
					bus.data = line;
				} else {
					bus.data = "";
				}
				// Acknowledge the CPU of the command execution
				bus.command = Bus.ACK;
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}