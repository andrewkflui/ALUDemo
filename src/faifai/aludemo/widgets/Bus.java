/***************************************************************
 *
 * Arithmetic Logic Unit (ALU) Interactive Demo
 * A teaching and learning tool for computer architecture
 * 
 * Copyright (c) 2007 Dr. Andrew Kwok-Fai LUI
 * The Open University of Hong Kong
 *
 * Enhance the learning effectiveness of students through greater interactions
 */
/*  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package faifai.aludemo.widgets;

import java.util.ArrayList;

public class Bus {

  private String name = "";
  private ArrayList<Port> portList = new ArrayList<Port>();

  public Bus(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void addPort(Port port) {
    portList.add(port);
  }

  public boolean isConnectedToPort(Port port) {
    for (Port p : portList) {
      if (p == port) {
        return true;
      }
    }
    return false;
  }

  public boolean checkIntegrity() {
    int writeCount = 0;
    for (Port port : portList) {
      if (port.getStatus() == Port.STATUS_WRITE) {
        writeCount++;
      }
      if (writeCount > 1) {
        return false;
      }
    }
    return true;
  }

  public boolean moveData() throws Exception {
    Port writePort = null;

    for (Port port : portList) {
      if (port.getStatus() == Port.STATUS_WRITE) {
        writePort = port;
        break;
      }
    }
    if (writePort == null) {
      return false;
    }
    int data = writePort.read();

    for (Port port : portList) {
      System.out.println("Checking port " + port.getName() + " " + port.getStatusString() + " " + port.getStatus());
      if (port.getStatus() == Port.STATUS_READ) {
        System.out.println("Bus " + name + " write data to " + port.getName() + " " + data);
        port.write(data);
      }
    }
    return true;
  }
}
