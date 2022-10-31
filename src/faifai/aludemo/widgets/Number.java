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

public class Number {

  public final static int MASK_BINARY = 0;
  public final static int MASK_LMC = 1;
  int upperBound = Integer.MAX_VALUE;
  int lowerBound = Integer.MIN_VALUE;
  private int value = 0;
  int maskType = MASK_BINARY;
  int mask = 0xffffffff;

  public void setUpperBound(int upperBound) {
    this.upperBound = upperBound;
  }

  public void setLowerBound(int lowerBound) {
    this.lowerBound = lowerBound;
  }

  public String getMaskStr() {
    if (maskType == MASK_BINARY) {
      return Integer.toHexString(mask);
    } else if (maskType == MASK_LMC) {
      return "LMC";
    }
    return "";
  }

  public void setMask(int mask) {
    this.mask = mask;
  }

  public void setMaskType(int maskType) {
    this.maskType = maskType;
  }

  void setValue(int value) {
    if (value > upperBound) {
      value = upperBound;
    } else if (value < lowerBound) {
      value = lowerBound;
    }
    this.value = value;
  }

  public int getValue() {
    if (maskType == MASK_BINARY) {
      return this.value & mask;
    } else if (maskType == MASK_LMC) {
      return this.value % 100;
    }
    return this.value;
  }

  public int getRawValue() {
    return this.value;
  }

  public String getDecimal() {
    return getDecimal(-1);
  }

  public String getDecimal(int width) {
    boolean isNegative = false;
    int thisValue = value;
    if (value < 0) {
      isNegative = true;
      thisValue = -thisValue;
    }

    String decString = thisValue + "";
    if (width <= 0) {
      if (isNegative) {
        return "-" + decString;
      }
      return decString;
    }
    int len = decString.length();
    StringBuffer sb = new StringBuffer();
    if (isNegative) {
      sb.append("-");
    }
    for (int i = 0; i < width - len; i++) {
      sb.append("0");
    }
    return sb.toString() + decString;
  }

  public String getHex() {
    return getHex(-1);
  }

  public String getHex(int width) {
    boolean isNegative = false;
    int thisValue = value;
    if (value < 0) {
      isNegative = true;
      thisValue = -thisValue;
    }

    String hexString = Integer.toHexString(thisValue).toUpperCase();
    if (width <= 0) {
      if (isNegative) {
        return "-" + hexString;
      }
      return hexString;
    }
    int len = hexString.length();
    StringBuffer sb = new StringBuffer();
    if (isNegative) {
      sb.append("-");
    }
    for (int i = 0; i < width - len; i++) {
      sb.append("0");
    }
    return sb.toString() + hexString;
  }

  public String getBin() {
    return getBin(-1);
  }

  public String getBin(int width) {
    boolean isNegative = false;
    int thisValue = value;
    if (value < 0) {
      isNegative = true;
      thisValue = -thisValue;
    }
    String binString = Integer.toBinaryString(value).toUpperCase();
    if (width <= 0) {
      if (isNegative) {
        return "-" + binString;
      }
      return binString;
    }
    int len = binString.length();
    StringBuffer sb = new StringBuffer();
    if (isNegative) {
      sb.append("-");
    }
    for (int i = 0; i < width - len; i++) {
      sb.append("0");
    }
    return sb.toString() + binString;
  }
}
