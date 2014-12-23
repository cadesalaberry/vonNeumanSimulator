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

|  ID  | Start State |      Event      | Condition |                        Reaction                       | New State |
|:----:|:-----------:|:---------------:|:---------:|:-----------------------------------------------------:|:---------:|
|  1.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  1.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  1.3 |      S1     |       JUMP      |     -     |                       PC=arg(IR)                      |    S16    |
|  1.4 |     S16     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  2.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  2.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  2.3 |      S1     |      JUMPZ      |  RegA==0  |                       PC=arg(IR)                      |     S2    |
|  2.4 |      S1     |      JUMPZ      |  RegA!=0  |                           -                           |     S2    |
|  2.4 |      S2     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  3.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  3.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  3.3 |      S1     |      STOREA     |     -     | bus.command=RAM_WRITEbus.data=RegAbus.address=arg(IR) |     S3    |
|  3.4 |      S3     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  4.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  4.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  4.3 |      S1     |      MOVEAB     |     -     |   bus.command=RAM_WRITEbus.data=RegAbus.address=RegB  |     S4    |
|  4.4 |      S4     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  5.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  5.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  5.3 |      S1     |      LOADA      |     -     |        bus.command=RAM_READbus.address=arg(IR)        |     S5    |
|  5.4 |      S5     | bus.command=ACK |     -     |                     RegA=bus.data                     |     S6    |
|  5.5 |      S6     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  6.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  6.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  6.3 |      S1     |      INPUT      |     -     |                   bus.command=INPUT                   |     S7    |
|  6.4 |      S7     | bus.command=ACK |     -     |        bus.command=RAM_WRITEbus.address=arg(IR)       |     S8    |
|  6.5 |      S8     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  7.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  7.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  7.3 |      S1     |      OUTPUT     |     -     |        bus.command=RAM_READbus.address=arg(IR)        |     S9    |
|  7.4 |      S9     | bus.command=ACK |     -     |                   bus.command=OUTPUT                  |    S10    |
|  7.5 |     S10     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  8.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  8.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  8.3 |      S1     |      FREAD      |     -     |                  bus.command=HD_READ                  |    S11    |
|  8.4 |     S11     | bus.command=ACK |     -     |        bus.command=RAM_WRITEbus.address=arg(IR)       |    S12    |
|  8.5 |     S12     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
|  9.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|  9.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
|  9.3 |      S1     |      FOPEN      |     -     |        bus.command=RAM_READbus.address=arg(IR)        |    S13    |
|  9.4 |     S13     | bus.command=ACK |     -     |                  bus.command=HD_OPEN                  |    S14    |
|  9.5 |     S14     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
| 10.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
| 10.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
| 10.3 |      S1     |      FCLOSE     |     -     |                  bus.command=HD_CLOSE                 |    S15    |
| 10.4 |     S15     | bus.command=ACK |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
|      |             |                 |           |                                                       |           |
| 11.1 |      -      |   constructor   |     -     |           bus.address=PCbus.command=RAM_READ          |     S0    |
| 11.2 |      S0     | bus.command=ACK |     -     |                    IR=bus.dataPC++                    |     S1    |
| 11.3 |      S1     |       HALT      |     -     |                           -                           |    END    |

# State Based Testing Report

We used eclEmma to analyse the coverage of our testing code, leading to the following output statistics.

![alt text][codecoverage]


# Extended Conformance Suite

Because the entire code was not covered with the previous test cases, we added the complementary ones to make it complete.

**TABLE TO ADD FROM PDF**


# Extended State Based Testing Report

Again, using eclEmma, here is the coverage report.

![alt text][codecoverage2]


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
[codecoverage]: assets/codecoverage.png "Non Optimal Code Coverage"
[codecoverage2]: assets/codecoverage2.png "Optimal Code Coverage"


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/cadesalaberry/vonneumansimulator/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

