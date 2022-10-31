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



### A Gradual Introduction to a Computer Processor
There are four demos useful for a gradual introduction to the operation of a computer processor and the basics of fetch and execution cycle.
* ALU and Register Operation Demo (`faifai.aludemo.ALUDemo`)
* ALU and Two Registers Operation Demo (`faifai.aludemo.ALUTwoRegDemo`)
* ALU with Memory System Demo (`faifai.aludemo.ALUMemoryDemo`)
* ALU, Program Counter, and Memory System Demo (`faifai.aludemo.ALUPCMemoryDemo`)

#### Features of each component
* Every component widget offers one or more of the following features.
** Set a new value
** Change the open/closed of the input (read) and the output (write) ports
** Other features relevant to the component (for example, view, load and save of the Memory System)
* Pressing the Signal button will simulate a signal activating all the components once. 

#### Screenshots

### Fetch and Execution Cycle of the LMC Illustrated
Finally, these three demos have put together all the components of a processor. The last two enables the loading of a LMC program into the memory system and execute the instructions (and the steps in the fetch and execution cycle) one by one.
* The LMC Demo (`faifai.aludemo.LMCDemo`): LMC implemention with the full set of components
* LMC Program Execution Demo (`faifai.aludemo.LMCOperationDemo`): The fetch and execution cycle on the LMC illustrated
* Program Execution (Faster) Demo (`faifai.aludemo.LMCOperationDemoFast`): The cycle runs a bit faster by combining steps


#### Screenshots