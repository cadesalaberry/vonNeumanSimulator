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

public class Display {
  Bus bus;
  // the constructor receives a reference to the bus component
  public Display(Bus p_bus) {
    bus = p_bus;
  }

  void execute() {
    // Verifies that data on the command bus is "WRITE"
    if(bus.command.equals(Bus.OUTPUT)) {
      System.out.println("\n> " + bus.data);
      // Acknowledge the CPU of the command execution
      bus.command = Bus.ACK;
    }
  }
}