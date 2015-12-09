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
 * Simulates the functioning of a computer keyboard.
 * It reads data from the keyboard and writes them on the data bus
 */
public class Keyboard {
  Bus bus;
  // creates an object BufferedReader to read data from the keyboard
  BufferedReader lineReader = new  BufferedReader( new InputStreamReader( System.in ) );
  // the constructor receives a reference to the bus component
  public Keyboard(Bus p_bus) {
    bus = p_bus;
  }

  void execute() {
    // Verifies that value of the command bus is "Keyboard"
    if(bus.command.equals(Bus.INPUT)) {
      try {
        System.out.print("> ");
	// reads the data from the keyboard
	bus.data = lineReader.readLine();
      }
      catch(IOException ioe) {
	ioe.printStackTrace();
      }
      // Acknowledge the CPU of the command execution
      bus.command = Bus.ACK;
    }
  }
}