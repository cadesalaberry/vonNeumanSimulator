vonNeumanSimulator
==================

Test suit designed around the Computer Simulator from Davide Brugali and Marco Torchiano.


# Roundtrip Path Tree

![alt text][pathtree]


# Conformance Test Suite

- Identifier
- Description of test setup (e.g., objects needed by the test case)
- Test sequence covering start state, event/condition, expected action, and expected new
state of the system.

===========


# Instruction Set

The computer executes a type of assembly language with the following instruction set.

Code 	| Argument	| Description
---		| ---		| ---
HALT 	| 			| Halts the CPU
JUMP 	| `address` | Jumps to the instruction at the given address
JUMPZ 	| `address`	| Jumps to the instruction at the given address if register A contains 0
LOADA 	| `address`	| Loads register A with the content of the cell at address
LOADB	| `address`	| Loads register B with the content of the cell at address
STOREA	| `address`	| Stores the content of register A in the cell at address
STOREB	| `address`	| Stores the content of register B in the cell at address
MOVEAB 	|			| Stores the content of register A in the memory cell whose
ADD 	|			| Adds the content of register A to the content of register B and put the result into register A
INPUT	| `address`	| Reads information from the I/O and store it in the cell at address 
OUTPUT	| `address`	| Reads  information from the cell at address and send it to I/O
FOPEN	| `address`	| Opens a file whose name is found in the cell at address
FREAD	| `address`	| Reads information from the currently open file into the cell at address
FCLOSE 	|			| Closes the previously opened file


# Components

- CPU: executes elementary operations. The CPU uses registers to store operands, results, data and instructions:
	- registers A and B are general­purpose registers
	- PC is the program counter
 	- IR is the instruction register, it holds the current instruction.
The CPU executes according to a fetch­decode­execute cycle.

- Bus – links the CPU with all the other components. The bus carries commands issued by the CPU, confirmation messages, data exchanged between the CPU and the other components and addresses of memory cells to be read or written.

- RAM – stores data and programs. The RAM copies data from specific memory locations to the data bus when a « read » request is received, and copies data from the bus to  specific memory locations when a « write » request is received. The address of the memory location is retrieved from the bus. When the RAM has completed a read or write operation it send an acknowledgment (ACK) value to the CPU through the bus.

- I/0 devices – the keyboard, display and hard disk behave like the RAM. They read or write data on the bus when corresponding commands are found on the bus.


# Instruction Execution

The CPU behavior can be modeled as the state machine below (reactions are embedded to states for readability).

![alt text][diagram]

Each CPU cycle involves fetching the next instruction from memory according to the program counter (PC), when the RAM reads the memory and issues and acknowledge command, the CPU enters a state where the instruction is decoded. The CPU copies the instruction into the IR, examines and executes it.

[pathtree]: assets/pathtree.png "State Machine Diagram"
[diagram]: assets/statemachine.png "State Machine Diagram"


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/cadesalaberry/vonneumansimulator/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

