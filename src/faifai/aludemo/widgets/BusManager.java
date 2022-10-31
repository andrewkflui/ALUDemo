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

public class BusManager {

  private static ArrayList<Bus> busList = new ArrayList<Bus>();

  public static void addBus(Bus bus) {
    busList.add(bus);
  }

  public static ArrayList<Bus> getBusList() {
    return busList;
  }

  public static Bus getBusOfPort(Port port) {
    for (Bus bus : busList) {
      if (bus.isConnectedToPort(port)) {
        return bus;
      }
    }
    return null;
  }
}
