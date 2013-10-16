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

public class RAM {
  final static int DEFAULT_SIZE = 256;

  private String [] cells;  // the sequence of memory cells

  private Bus bus;

  RAM(Bus p_bus) {
    bus = p_bus;
    cells = new String[DEFAULT_SIZE];
  }

  RAM(Bus p_bus, int size) {
    bus = p_bus;
    cells = new String[size];
  }

  public void initialize(String [] initValues){
    // check precondition
    if(initValues.length > cells.length){
      System.err.println("Error initializing RAM: " +
                         "initial values array bigger that RAM.");
      System.exit(0);
    }
    // initialized memory cells
    int i;
    for(i=0; i<initValues.length; ++i){
      cells[i]=initValues[i];
    }
  }

  public void set(String [] newCells){
    cells = newCells;
  }

  boolean execute() {
    // Reads the command and the data from the bus
    if(bus.command.equals(Bus.RAM_READ)) {
      // Writes the content of the selected cell in the data bus
      bus.data = cells[bus.address];
      // Acknowledge the CPU of the command execution
      bus.command = Bus.ACK;
      return true;
    }
    else if(bus.command.equals(Bus.RAM_WRITE)) {
      // Reads the address of the cell where it writes the content of the data bus
      cells[bus.address] = bus.data;
      // Acknowledge the CPU of the command execution
      bus.command = Bus.ACK;
      return true;
    }
    return false;
  }

  public String cell(int i) {
	  return cells[i];
  }

  public void dump() {
    System.out.println("RAM - size:" + cells.length);
    for(int i = 0; i < cells.length; i++){
      if(cells[i]==null) return;
      System.out.println(i + " : " + cells[i]);
    }
  }
}
