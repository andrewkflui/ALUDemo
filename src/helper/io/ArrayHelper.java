/***************************************************************
 *
 * Copyright (c) 2010 Dr. Andrew Kwok-Fai LUI
 * The Open University of Hong Kong
 *
 * Utilities
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
package helper.io;
public class ArrayHelper {

  public static Object resizeArray(Object oldArray, int newSize) {
    int oldSize = java.lang.reflect.Array.getLength(oldArray);
    final Class arrayClass = oldArray.getClass().getComponentType();
    Object newArray = java.lang.reflect.Array.newInstance(arrayClass, newSize);
    int keepSize = Math.min(oldSize, newSize);
    if (keepSize > 0) { 
      System.arraycopy(oldArray, 0, newArray, 0, keepSize);
    }
    return newArray;
  }
}
