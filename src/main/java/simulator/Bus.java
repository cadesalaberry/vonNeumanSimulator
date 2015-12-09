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

public class Bus{
  // the three parts of a system bus are implemented by means of
  // public variables which can be access by everyone in the system
  public String  data;     // the data bus
  public String  command;  // the command bus
  public int     address;  // the address bus

  // Bus commands
  public final static String RAM_READ="RAM_READ";
  public final static String RAM_WRITE="RAM_WRITE";
  public final static String ACK="ACK";
  public final static String INPUT="INPUT";
  public final static String OUTPUT="OUTPUT";
  public final static String FOPEN="FOPEN";
  public final static String FREAD="FREAD";
  public final static String FCLOSE="FCLOSE";

}
