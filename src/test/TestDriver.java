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
import simulator.*;

public class TestDriver {
  public static void main(String[] args) {

    System.out.println("Performing test 1...");
    Computer computer = new Computer(test1);
    computer.simulate(1000);
    computer.cpu.dump();
    computer.ram.dump();
    System.out.println("Test done.");

    System.out.println("Performing test 2...");
    computer = new Computer(test2);
    computer.simulate(1000);
    computer.cpu.dump();
    computer.ram.dump();
    System.out.println("Test done.");

    System.out.println("Performing test 3...");
    computer = new Computer(test3);
    computer.simulate(1000);
    //computer.cpu.dump();
    //computer.ram.dump();
    System.out.println("Test done.");

    System.out.println("Performing boot test...");
    computer = new Computer(bootTest);
    computer.simulate(10000);
    //computer.cpu.dump();
    //computer.ram.dump();
    System.out.println("Test done.");
    System.out.println("All tests performed.");
  }

  private static final String test1[]={
    /*  0 */ "LOADA 5",
    /*  1 */ "LOADB 6",
    /*  2 */ "ADD",
    /*  3 */ "STOREA 7",
    /*  4 */ "HALT",
    /*  5 */ "123",
    /*  6 */ "432",
    /*  7 */ "0",
  };

  private static final String test2[]={
    /*  0 */ "LOADA 13",
    /*  1 */ "JUMPZ 12",
    /*  2 */ "LOADB 14",
    /*  3 */ "ADD",
    /*  4 */ "STOREA 13",
    /*  5 */ "LOADB 15",
    /*  6 */ "ADD",
    /*  7 */ "STOREA 16",
    /*  8 */ "LOADA 17",
    /*  9 */ "LOADB 16",
    /* 10 */ "MOVEAB 16",
    /* 11 */ "JUMP 0",
    /* 12 */ "HALT",
    /* 13 */ "12",
    /* 14 */ "-1",
    /* 15 */ "20",
    /* 16 */ "0",
    /* 17 */ "99",
    /* 18 */ "0",
    /* 19 */ "0",
    /* 20 */ "0",
    /* 21 */ "0",
    /* 22 */ "0",
    /* 23 */ "0",
    /* 24 */ "0",
    /* 25 */ "0",
    /* 26 */ "0",
    /* 27 */ "0",
    /* 28 */ "0",
    /* 29 */ "0",
    /* 30 */ "0",
    /* 31 */ "0",
   } ;

   private static final String test3[]={
    /*  0 */ "OUTPUT 11",
    /*  1 */ "INPUT 14",
    /*  2 */ "OUTPUT 12",
    /*  3 */ "INPUT 15",
    /*  4 */ "LOADA 14",
    /*  5 */ "LOADB 15",
    /*  6 */ "ADD",
    /*  7 */ "STOREA 16",
    /*  8 */ "OUTPUT 13",
    /*  9 */ "OUTPUT 16",
    /* 10 */ "HALT",
    /* 11 */ "Insert 1st number:",
    /* 12 */ "Insert 2nd number:",
    /* 13 */ "Sum is:",
    /* 14 */ "",
    /* 15 */ "",
    /* 16 */ "",
  };


  private static final String bootTest[]={
    /*  0 */ "FOPEN 22",
    /*  1 */ "FREAD 26",
    /*  2 */ "FCLOSE",
    /*  3 */ "FOPEN 26",
    /*  4 */ "FREAD 27",
    /*  5 */ "LOADA 27",
    /*  6 */ "JUMPZ 20",
    /*  7 */ "LOADB 25",
    /*  8 */ "FREAD 28",
    /*  9 */ "LOADA 28",
    /* 10 */ "MOVEAB",
    /* 11 */ "LOADA 25",
    /* 12 */ "LOADB 23",
    /* 13 */ "ADD",
    /* 14 */ "STOREA 25",
    /* 15 */ "LOADA 27",
    /* 16 */ "LOADB 24",
    /* 17 */ "ADD",
    /* 18 */ "STOREA 27",
    /* 19 */ "JUMP 6",
    /* 20 */ "FCLOSE", // L0
    /* 21 */ "JUMP 100",
    /* 22 */ "boot.ini", //  x1: boot descr
    /* 23 */ "1", // piu1
    /* 24 */ "-1", // meno1
    /* 25 */ "100", // x4 progbase
    /* 26 */ "0", // x2: exec file name
    /* 27 */ "0", // x3: exec instr number
    /* 28 */ "0", // x5: instr buffer
   } ;
}