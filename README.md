# Basic Processor Illustration: The Fetch and Execution Cycle

A set of demonstration programs to illustrate the fetch and execution cycles and the operations regarding the ALU, Registers, Memory, and Program Counters.

First developed in 2007

Copyright (C) 2007 - Andrew Kwok-Fai Lui

The Open University of Hong Kong

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, see http://www.gnu.org/licenses/.
 
## Introduction

The fetch and execution cycle is the foundation of the operation of processors. It involves the collaboration of several essential components including the Arithmetic and Logic Unit (ALU), registers, the program counter (PC), instruction register (IR), and the memory system. 

As a teacher of a course about computer architecture, discussion on the fetch and execution cycle is ineffective without visualization of the how and the why of data movement between the components. Therefore this set of demo applications was developed to enable visualization of the fetch and execution cycle in a classroom context. Students with weaker visualization ability can find it useful.

Principally this set is defined as teaching tools. However, with suitable tuition design, it can be part of a exploratory laboratory session.  Some of the tools are designed for a course using the Little Man Computer (LMC).

The set of interactive demos include the following.
* IEEE754 Format Demo (`faifai.arch.IEEE754Bit32Demo`)
* ALU and Register Operation Demo (`faifai.aludemo.ALUDemo`)
* ALU and Two Registers Operation Demo (`faifai.aludemo.ALUTwoRegDemo`)
* ALU with Memory System Demo (`faifai.aludemo.ALUMemoryDemo`)
* ALU, Program Counter, and Memory System Demo (`faifai.aludemo.ALUPCMemoryDemo`)
* The LMC Demo (`faifai.aludemo.LMCDemo`): LMC implemention with the full set of components
* LMC Program Execution Demo (`faifai.aludemo.LMCOperationDemo`): The fetch and execution cycle on the LMC illustrated
* Program Execution (Faster) Demo (`faifai.aludemo.LMCOperationDemoFast`): The cycle runs a bit faster by combining steps

## Running the Demos
### Pre-requisites
* Java JDK or JRE 1.8 or above

### Instruction
A launcher application ApplicationLauncher.class is provided for running each of the 8 applications.

1. Download the repository to a folder, assuming that it is `/app/ALUDemo`. The Java sources are in the `src` folder and the classes are found in the `bin` folder.
2. Execute `ApplicationLauncher.class` insider the folder

> `cd /app/ALUDemo`
> 
> `java -cp "./bin" ApplicationLauncher`

### Running Individual Application

Alternatively, each of the 5 applications can be executed from their main classes (as in the above list).

> `cd /app/ALUDemo`
> 
> `java -cp "./bin" faifai.arch.IEEE754Bit32Demo`

## Overview of the Applications

### IEEE754 Format Demo 
Class: `faifai.arch.IEEE754Bit32Demo`

This demo is actually not part of the fetch and execution cycle demo, but included in this repository because this seems a useful teaching and learning tool.
<img width="832" alt="IEEE754" src="https://user-images.githubusercontent.com/8808539/199018530-6aae2bc0-52f8-404b-bfdb-a2d1516740c1.png">

### A Gradual Introduction to a Computer Processor
There are four demos useful for a gradual introduction to the operation of a computer processor and the basics of fetch and execution cycle.
* ALU and Register Operation Demo (`faifai.aludemo.ALUDemo`)
* ALU and Two Registers Operation Demo (`faifai.aludemo.ALUTwoRegDemo`)
* ALU with Memory System Demo (`faifai.aludemo.ALUMemoryDemo`)
* ALU, Program Counter, and Memory System Demo (`faifai.aludemo.ALUPCMemoryDemo`)

#### Features of each component
* Every component widget offers one or more of the following features.
** Set a new value
** Toggle between decimal and hexadecimal view
** Change the open/closed of the input (read) and the output (write) ports
** The Memory Sytem enables set value of MAR and MDR, and also view, load and save all the data in the memory (size 256)
** The ALU enables selection of 3 instructions: add, subtract, and set
* Pressing the Signal button will simulate a signal activating all the components once. 

#### Screenshots
<img width="592" alt="ALUDemo" src="https://user-images.githubusercontent.com/8808539/199018564-5c604c27-2d9a-4d66-8c2c-602727be392a.png">
<img width="592" alt="ALUTwoReg" src="https://user-images.githubusercontent.com/8808539/199018574-1b42bef1-85d3-47d1-a9fa-c079becacd83.png">
<img width="752" alt="ALUMemory" src="https://user-images.githubusercontent.com/8808539/199018583-cf0bc501-5594-4b60-a01a-a1606807378b.png">
<img width="752" alt="ALUPCMemory" src="https://user-images.githubusercontent.com/8808539/199018599-fc9ba496-1d8d-4469-baaf-de3ae1598ffe.png">

#### Suggestion on How to Use the Processor Demo
1. Start from the ALU Demo with one register. Emphasis the following (1) the input port and the output port can be in a state of Open (connecting to the bus) or CLosed (not reading nor writing from the bus), (2) the buses connect the output port of one component to the input port of another component. The Closed state means that the component is not prepared to read or write data at the port. 
2. Emphasis that the components only do work at the signal, which means when the "Send Clock Signal" button is pressed. 
3. Discuss the ALU operationd (add, subtract, and set) and the data used in these operations. For example, the add function has two operands, one from the register's output and another from the ALU output. Set a value on the ALU and the register, select "add", and press the signal button to show that the updated value is seen in the ALU.
I will stop here and hopefully this is enough to give you an idea.

### Fetch and Execution Cycle of the LMC Illustrated
Finally, these three demos have put together all the components of a processor. The last two enables the loading of a LMC program into the memory system and execute the instructions (and the steps in the fetch and execution cycle) one by one.
* The LMC Demo (`faifai.aludemo.LMCDemo`): LMC implemention with the full set of components
* LMC Program Execution Demo (`faifai.aludemo.LMCOperationDemo`): The fetch and execution cycle on the LMC illustrated
* Program Execution (Faster) Demo (`faifai.aludemo.LMCOperationDemoFast`): The cycle runs a bit faster by combining steps

#### Screenshot of the LMC Program Execution Demo

<img width="912" alt="LMCOperation" src="https://user-images.githubusercontent.com/8808539/199019379-8137ada5-5317-47f5-acf4-dab281690515.png">

#### Suggestion on How to Use the LMC Program Execution Demo.
1. A LMC program (Add 2 numbers) should have been pre-loaded into the memory system. Alternatively, load another memory file or enter your own program.  Press the View Data button on the Memory System to show that to the students.
2. Emphaize the PC is now 000, meaning that the next address to execute is 0.  
3. Describe the purpose of pressing the "Send Clock Signal" button, which runs a step in the fetch and execution cycle. 
4. The first step is PC > MAR, which is shown on the left. Press a button once to execute this step. Show that MAR is now 000.
5. The next step MEM[MAR] > MDR is shown. Press the signal button to execute, show MDR is changed from 000 to 901.
6. The next step is MDR > IR. Press the signal button to execute and show that 901 is loaded into IR.
I also stop here now and this should be enough to give you an idea.

