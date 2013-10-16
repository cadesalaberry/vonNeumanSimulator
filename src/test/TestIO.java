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
package test;
import static org.junit.Assert.*;

import org.junit.Test;

import simulator.*;

public class TestIO {

	public TestIO() {

	}
	
	@Test
	public void testKdbd(){
		Computer computer = new Computer(ioProgram);
		computer.simulate(1000);
		
		String first = computer.ram.cell(14);
		String second = computer.ram.cell(15);
		int result = Integer.parseInt(first) +
					Integer.parseInt(second);
		assertEquals(Integer.toString(result),computer.ram.cell(16));
	}
	
	@Test
	public void testBootstrap(){
		Computer computer = new Computer(bootProgram);
		computer.simulate(2000);
		
		String first = computer.ram.cell(111);
		String second = computer.ram.cell(112);
		int result = Integer.parseInt(first) +
					Integer.parseInt(second);
		assertEquals(Integer.toString(result),computer.ram.cell(113));
	}
	
	private static final String ioProgram[]={
	 /*  0 */ CPU.OUTPUT + " 11",
	 /*  1 */ CPU.INPUT + " 14",
	 /*  2 */ CPU.OUTPUT + " 12",
	 /*  3 */ CPU.INPUT + " 15",
	 /*  4 */ CPU.LOADA + " 14",
	 /*  5 */ CPU.LOADB + " 15",
	 /*  6 */ CPU.ADD,
	 /*  7 */ CPU.STOREA + " 16",
	 /*  8 */ CPU.OUTPUT + " 13",
	 /*  9 */ CPU.OUTPUT + " 16",
	 /* 10 */ CPU.HALT,
	 /* 11 */ "Insert 1st number:",
	 /* 12 */ "Insert 2nd number:",
	 /* 13 */ "Sum is:",
	 /* 14 */ "",
	 /* 15 */ "",
	 /* 16 */ "",
   };

   private static final String bootProgram[]={
	 /*  0 */ CPU.FOPEN + " 22",
	 /*  1 */ CPU.FREAD + " 26",
	 /*  2 */ CPU.FCLOSE,
	 /*  3 */ CPU.FOPEN + " 26",
	 /*  4 */ CPU.FREAD + " 27",
	 /*  5 */ CPU.LOADA + " 27",
	 /*  6 */ CPU.JUMPZ + " 20",
	 /*  7 */ CPU.LOADB + " 25",
	 /*  8 */ CPU.FREAD + " 28",
	 /*  9 */ CPU.LOADA + " 28",
	 /* 10 */ CPU.MOVEAB,
	 /* 11 */ CPU.LOADA + " 25",
	 /* 12 */ CPU.LOADB + " 23",
	 /* 13 */ CPU.ADD,
	 /* 14 */ CPU.STOREA + " 25",
	 /* 15 */ CPU.LOADA + " 27",
	 /* 16 */ CPU.LOADB + " 24",
	 /* 17 */ CPU.ADD,
	 /* 18 */ CPU.STOREA + " 27",
	 /* 19 */ CPU.JUMP + " 6",
	 /* 20 */ CPU.FCLOSE,
	 /* 21 */ CPU.JUMP + " 100",
	 /* 22 */ "boot.ini", // boot descr
	 /* 23 */ "1",  // constant 1
	 /* 24 */ "-1", // constant -1
	 /* 25 */ "100", // progbase
	 /* 26 */ "0", // exec file name
	 /* 27 */ "0", // exec instr number
	 /* 28 */ "0", // instr buffer
	} ;

}
