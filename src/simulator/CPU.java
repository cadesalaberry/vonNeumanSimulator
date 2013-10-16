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

public class CPU {
  private Bus     bus;

  // Registers
  private int	  PC = 0;
  private String  IR;
  private String  RD;
  private String  RegA;
  private String  RegB;

  // Instructions
  public final static String HALT="HALT";
  public final static String JUMP="JUMP";
  public final static String JUMPZ="JUMPZ";
  public final static String LOADA="LOADA";
  public final static String LOADB="LOADB";
  public final static String STOREA="STOREA";
  public final static String STOREB="STOREB";
  public final static String MOVEAB="MOVEAB";
  public final static String ADD="ADD";
  public final static String INPUT="INPUT";
  public final static String OUTPUT="OUTPUT";
  public final static String FOPEN="FOPEN";
  public final static String FREAD="FREAD";
  public final static String FCLOSE="FCLOSE";


  // inspection method
  public void dump(){
    System.out.println("CPU status");
    if(halted()) System.out.println("Halted");
    System.out.println("PC= " + PC);
    System.out.println("IR= " + IR);
    System.out.println("RegA= " + RegA);
    System.out.println("RegB= " + RegB);
  }

  // methods intended to operate on the IR
  private String code(String instruction){
    int separator;
    separator = instruction.indexOf(' ');
    if( separator > 0){
      return instruction.substring(0,separator);
    }else{
      return instruction;
    }
  }

  private int arg(String instruction){
    int separator;
    separator = instruction.indexOf(' ');
    if( separator > 0){
      return Integer.parseInt(
                instruction.substring(separator+1,instruction.length()));
    }else{
      return 0;
    }
  }

  // FSA states
  private final static int FETCH_entry=1;
  private final static int FETCH=2;
  private final static int DECODE_entry=3;
  private final static int DECODE=4;
  private final static int EXEC_HALT_entry=5;
  private final static int EXEC_HALT=6;
  private final static int EXEC_JUMP_entry=7;
  private final static int EXEC_JUMP=8;
  private final static int EXEC_JUMPZ_entry=9;
  private final static int EXEC_JUMPZ=10;
  private final static int EXEC_LOADA_entry=13;
  private final static int EXEC_LOADA=14;
  private final static int EXEC_LOADB_entry=15;
  private final static int EXEC_LOADB=16;
  private final static int EXEC_STOREA_entry=17;
  private final static int EXEC_STOREA=18;
  private final static int EXEC_STOREB_entry=19;
  private final static int EXEC_STOREB=20;
  private final static int EXEC_MOVEAB_entry=21;
  private final static int EXEC_MOVEAB=22;
  private final static int EXEC_ADD_entry=23;
  private final static int EXEC_ADD=24;
  private final static int EXEC_LOADA2_entry=25;
  private final static int EXEC_LOADA2=26;
  private final static int EXEC_LOADB2_entry=27;
  private final static int EXEC_LOADB2=28;
  // I/O
  private final static int EXEC_INPUT_entry=29;
  private final static int EXEC_INPUT=30;
  private final static int EXEC_INPUT2_entry=31;
  private final static int EXEC_INPUT2=32;
  private final static int EXEC_OUTPUT_entry=33;
  private final static int EXEC_OUTPUT=34;
  private final static int EXEC_OUTPUT2_entry=35;
  private final static int EXEC_OUTPUT2=36;
  // Storage
  private final static int EXEC_FOPEN_entry=37;
  private final static int EXEC_FOPEN=38;
  private final static int EXEC_FOPEN2_entry=39;
  private final static int EXEC_FOPEN2=40;
  private final static int EXEC_FREAD_entry=41;
  private final static int EXEC_FREAD=42;
  private final static int EXEC_FREAD2_entry=43;
  private final static int EXEC_FREAD2=44;
  private final static int EXEC_FCLOSE_entry=45;
  private final static int EXEC_FCLOSE=46;


  private int state = FETCH_entry; // indicates the current state of the CPU

  public CPU(Bus p_bus) {
    bus = p_bus;
  }

  public boolean halted() { return state == EXEC_HALT; }

  public void execute() {
    switch(state){
      case FETCH_entry:
                  bus.command = Bus.RAM_READ;
                  bus.address = PC;
                  state++;
      case FETCH:
                  if(bus.command.equals(Bus.ACK)) state = DECODE_entry;
                  break;
      case DECODE_entry:
                  IR = bus.data;
                  PC++;
                  state++;
      case DECODE:
                  if(code(IR).equals(HALT)) state=EXEC_HALT_entry;
                  if(code(IR).equals(JUMP)) state=EXEC_JUMP_entry;
                  if(code(IR).equals(JUMPZ)) state=EXEC_JUMPZ_entry;
                  if(code(IR).equals(LOADA)) state=EXEC_LOADA_entry;
                  if(code(IR).equals(LOADB)) state=EXEC_LOADB_entry;
                  if(code(IR).equals(STOREA)) state=EXEC_STOREA_entry;
                  if(code(IR).equals(STOREB)) state=EXEC_STOREB_entry;
                  if(code(IR).equals(MOVEAB)) state=EXEC_MOVEAB_entry;
                  if(code(IR).equals(ADD)) state=EXEC_ADD_entry;
                  if(code(IR).equals(INPUT)) state=EXEC_INPUT_entry;
                  if(code(IR).equals(OUTPUT)) state=EXEC_OUTPUT_entry;
                  if(code(IR).equals(FOPEN)) state=EXEC_FOPEN_entry;
                  if(code(IR).equals(FREAD)) state=EXEC_FREAD_entry;
                  if(code(IR).equals(FCLOSE)) state=EXEC_FCLOSE_entry;
                  break;
      case EXEC_HALT_entry:
                  state++;
      case EXEC_HALT:
                  break;
      case EXEC_JUMP_entry:
                  PC = arg(IR);
                  state++;
      case EXEC_JUMP:
                  state = FETCH_entry;
                  break;
      case EXEC_JUMPZ_entry:
                  if(RegA.equals("0")) PC = arg(IR);
                  state++;
      case EXEC_JUMPZ:
                  state = FETCH_entry;
                  break;
      case EXEC_LOADA_entry:
                  bus.command = Bus.RAM_READ;
                  bus.address = arg(IR);
                  state++;
      case EXEC_LOADA:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_LOADA2_entry;
                  break;
      case EXEC_LOADA2_entry:
                  RegA = bus.data;
                  state++;
      case EXEC_LOADA2:
                  state = FETCH_entry;
                  break;
      case EXEC_LOADB_entry:
                  bus.command = Bus.RAM_READ;
                  bus.address = arg(IR);
                  state++;
      case EXEC_LOADB:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_LOADB2_entry;
                  break;
      case EXEC_LOADB2_entry:
                  RegB = bus.data;
                  state++;
      case EXEC_LOADB2:
                  state = FETCH_entry;
                  break;
      case EXEC_STOREA_entry:
                  bus.command = Bus.RAM_WRITE;
                  bus.data = RegA;
                  bus.address = arg(IR);
                  state++;
      case EXEC_STOREA:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_STOREB_entry:
                  bus.command = Bus.RAM_WRITE;
                  bus.data = RegB;
                  bus.address = arg(IR);
                  state++;
      case EXEC_STOREB:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_MOVEAB_entry:
                  bus.command = Bus.RAM_WRITE;
                  bus.data = RegA;
                  bus.address = Integer.parseInt(RegB);
                  state++;
      case EXEC_MOVEAB:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_ADD_entry:
                  RegA= Integer.toString(Integer.parseInt(RegA)
                                        + Integer.parseInt(RegB));
                  state++;
      case EXEC_ADD:
                  state = FETCH_entry;
                  break;
                  //////////////////////////////////////////////////
      case EXEC_INPUT_entry:
                  bus.command = Bus.INPUT;
                  state++;
      case EXEC_INPUT:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_INPUT2_entry;
                  break;
      case EXEC_INPUT2_entry:
                  bus.command = Bus.RAM_WRITE;
                  bus.address = arg(IR);
                  state++;
      case EXEC_INPUT2:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_OUTPUT_entry:
                  bus.command = Bus.RAM_READ;
                  bus.address = arg(IR);
                  state++;
      case EXEC_OUTPUT:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_OUTPUT2_entry;
                  break;
      case EXEC_OUTPUT2_entry:
                  bus.command = Bus.OUTPUT;
                  state++;
      case EXEC_OUTPUT2:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_FOPEN_entry:
                  bus.command = Bus.RAM_READ;
                  bus.address = arg(IR);
                  state++;
      case EXEC_FOPEN:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_FOPEN2_entry;
                  break;
      case EXEC_FOPEN2_entry:
                  bus.command = Bus.FOPEN;
                  state++;
      case EXEC_FOPEN2:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_FREAD_entry:
                  bus.command = Bus.FREAD;
                  state++;
      case EXEC_FREAD:
                  if(bus.command.equals(Bus.ACK)) state=EXEC_FREAD2_entry;
                  break;
      case EXEC_FREAD2_entry:
                  bus.command = Bus.RAM_WRITE;
                  bus.address = arg(IR);
                  state++;
      case EXEC_FREAD2:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;
      case EXEC_FCLOSE_entry:
                  bus.command = Bus.FCLOSE;
                  state++;
      case EXEC_FCLOSE:
                  if(bus.command.equals(Bus.ACK)) state=FETCH_entry;
                  break;

    }
  }
}