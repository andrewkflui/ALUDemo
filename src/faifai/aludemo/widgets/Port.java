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

public class Port {
  public static final int PORT_IN = 0;
  public static final int PORT_OUT = 1;
  public static final int PORT_INOUT = 2;
  public static final int STATUS_CLOSED = 0;
  public static final int STATUS_READ = 1;
  public static final int STATUS_WRITE = 2;

  private Number number = null;
  private PortListener listener = null;
  private String name = "";
  private int type;
  private int status = STATUS_CLOSED;
  private boolean disableChangeStatus = false;

  public Port(PortListener listener, String name, int type) {
    this.name = name;
    this.type = type;
    this.listener = listener;
  }

  public Port(Number number, String name, int type) {
    this.name = name;
    this.type = type;
    this.number = number;
  }

  public int getType() {
    return type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setDisableChangeStatus(boolean disableChangeStatus) {
    this.disableChangeStatus = disableChangeStatus;
  }

  public String getStatusString() {
    switch (status) {
      case STATUS_CLOSED: return "Closed";
      case STATUS_READ: return "Read";
      case STATUS_WRITE: return "Write";
    }
    return "";
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    if (this.type == PORT_IN) {
      if (status == STATUS_WRITE) {
        return;
      }
    } else if (this.type == PORT_OUT) {
      if (status == STATUS_READ) {
        return;
      }
    }
    this.status = status;
  }

  void nextStatus() {
    if (disableChangeStatus)
      return;
    if (this.type == PORT_IN) {
      if (status == STATUS_CLOSED) {
        status = STATUS_READ;
      } else {
        status = STATUS_CLOSED;
      }
    } else if (this.type == PORT_OUT) {
      if (status == STATUS_CLOSED) {
        status = STATUS_WRITE;
      } else {
        status = STATUS_CLOSED;
      }
    } else {
      status = (status + 1) % 3;
    }
  }

  public int read() throws Exception {
    if (status != STATUS_WRITE) {
      throw new Exception("[Port] Invalid Status for Write Operation");
    }
    if (listener != null) {
      return listener.read(this);
    } else {
      return number.getValue();
    }
  }

  public void write(int data) throws Exception {
    if (status != STATUS_READ) {
      throw new Exception("[Port] Invalid Status for Read Operation");
    }
    if (listener != null) {
      listener.write(this, data);
      return;
    } else {
      number.setValue(data);
    }
  }
}
