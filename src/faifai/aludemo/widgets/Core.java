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

public class Core {

  public final static int MODE_BINARY = 2;
  public final static int MODE_DEC = 10;
  public final static int MODE_HEX = 16;
  public final static int MODE_LMC = 30;
  protected Number theNumber = null;
  protected String name = "REG";
  protected ArrayList<Port> portList = new ArrayList<Port>();
  protected Port auxOutPort;
  protected int representationMode = MODE_DEC;
  protected int representationRange[] = {2, 10, 16, 30};

  public Core(Number theNumber) {
    this.theNumber = theNumber;
    auxOutPort = new Port(theNumber, "AUX OUT", Port.PORT_OUT);
    auxOutPort.setStatus(Port.STATUS_WRITE);
  }

  public void addPort(Port thePort) {
    portList.add(thePort);
  }

  public int countPort() {
    return portList.size();
  }

  public Port getPort(int index) {
    return portList.get(index);
  }

  public Port getAuxOutPort() {
    return this.auxOutPort;
  }

  public Number getNumber() {
    return this.theNumber;
  }

  public void reset() {
    theNumber.setValue(0);
  }

  public void increase() {
    theNumber.setValue(theNumber.getValue() + 1);
  }

  public void setLMCMode() {
    theNumber.setUpperBound(999);
    theNumber.setLowerBound(0);
    int range[] = {Core.MODE_LMC};
    setRepresentationRange(range);
    setRepresentationMode(Core.MODE_LMC);
  }

  public void setValueStr(String valueStr) {
    try {
      if (representationMode == MODE_DEC || representationMode == MODE_LMC) {
        theNumber.setValue(Integer.valueOf(valueStr));
      } else if (representationMode == MODE_HEX) {
        theNumber.setValue(Integer.valueOf(valueStr, 16));
      } else if (representationMode == MODE_BINARY) {
        theNumber.setValue(Integer.valueOf(valueStr, 2));
      }
    } catch (Exception ex) {
    }
  }

  public String getValueStr() {
    if (representationMode == MODE_DEC) {
      return theNumber.getDecimal();
    } else if (representationMode == MODE_HEX) {
      return theNumber.getHex(8);
    } else if (representationMode == MODE_BINARY) {
      return theNumber.getBin(32);
    } else if (representationMode == MODE_LMC) {
      return theNumber.getDecimal(3);
    }
    return "";
  }

  public void setRepresentationRange(int range[]) {
    this.representationRange = range;
  }

  public String getRepresentationStr() {
    switch (representationMode) {
      case MODE_DEC:
        return "Dec";
      case MODE_HEX:
        return "Hex";
      case MODE_BINARY:
        return "Bin";
      case MODE_LMC:
        return "Lmc";
    }
    return "";
  }

  public void setRepresentationMode(int representationMode) {
    this.representationMode = representationMode;
  }

  public void nextRepresentationMode() {
    for (int i = 0; i < representationRange.length; i++) {
      if (representationMode == representationRange[i]) {
        representationMode = representationRange[(i + 1) % representationRange.length];
        return;
      }
    }
  }

  public void clockSignalReceived() {
    for (Port port : portList) {
      if (port.getStatus() == Port.STATUS_READ) {
        Bus inPortBus = BusManager.getBusOfPort(port);
        try {
          inPortBus.moveData();
        } catch (Exception ex) {
        }
      }
    }
    for (Port port : portList) {
      if (port.getStatus() == Port.STATUS_WRITE) {
        Bus outPortBus = BusManager.getBusOfPort(port);
        try {
          outPortBus.moveData();
        } catch (Exception ex) {
        }
      }
    }
  }
}
