/***************************************************************
 *
 * IEEE754 Floating-point Format Interactive Demo
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
package faifai.arch;

import javax.swing.JOptionPane;

public class IEEE754Bit32Demo extends javax.swing.JFrame {

  private float ieee754 = 0;
  private int ieee754bit = 0;
  private String ieee754Str = null;
  private String signPart = null;
  private String exponentPart = null;
  private String mantissaPart = null;
  private boolean isNegative = false;
  private boolean isDenormalized = false;
  private int exponentExcess = -1;
  private int exponent = -1;
  private String fractionBinary = null;

  public IEEE754Bit32Demo() {
    try {
        javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (Exception ex) {
    }
    initComponents();
  }

  private void updateDisplay() {
    try {
      String number = jTextField1.getText().trim();
      if (number.length() == 0)
        return;
      analyseNumber();
      updateIEEE754Display();
      getFractionalFormat();
      updateFractionalBinaryDisplay();
      updateNormalization();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error",
              JOptionPane.ERROR_MESSAGE);
      jTextField1.setText("");
    }
  }

  private void updateNormalization() throws Exception {
    try {
      String firstStepDisplay = "";
      String normalizedDisplay = "";
      if (ieee754 == 0) {
        jLabel4.setText("");
        jLabel5.setText("Special Value: Zero");
        jLabel7.setText("");
      } else if (this.isDenormalized) {
        jLabel5.setText("Special Value: Denormalized Number");
        firstStepDisplay = "0." + mantissaPart + " x 2<sup>-126</sup>";
      } else {
        jLabel5.setText("");
        firstStepDisplay = "1." + mantissaPart + " x 2<sup>" + exponent + "</sup>";
        normalizedDisplay = "1.<font color='blue'>" + mantissaPart + "</font>"
                + " x 2<sup>(" + "<font color='green'>" + exponentPart + "</font> - 01111111)</sup>";
      }
      if (isNegative) {
        firstStepDisplay = "-" + firstStepDisplay;
        normalizedDisplay = "<font color='red'>-</font>" + normalizedDisplay;
      }
      jLabel4.setText("<html>" + firstStepDisplay);

      jLabel7.setText("<html>" + normalizedDisplay);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  private void updateFractionalBinaryDisplay() {
    jTextField3.setText(fractionBinary);
  }

  private void updateIEEE754Display() {
    jTextField2.setText(ieee754Str);
    jLabel1.setText(signPart);
    jLabel2.setText(exponentPart);
    jLabel3.setText(mantissaPart);
    jLabel6.setText(Float.toString(ieee754));
  }

  private void getFractionalFormat() {
    if (ieee754 == 0) {
      fractionBinary = "0";
      return;
    }
    this.isDenormalized = false;
    if (exponentExcess == 0) {
      // denormalized number
      this.isDenormalized = true;
      fractionBinary = "0.";
      for (int i = 1; i < 126; i++) {
        fractionBinary = fractionBinary + "0";
      }
      fractionBinary = fractionBinary + mantissaPart;
    } else if (exponent > 0) {
      fractionBinary = "1";
      for (int i = 0; i < exponent; i++) {
        if (i < mantissaPart.length()) {
          fractionBinary = fractionBinary + mantissaPart.charAt(i);
        } else {
          fractionBinary = fractionBinary + '0';
        }
      }
      if (exponent < mantissaPart.length()) {
        fractionBinary = fractionBinary + ".";
        for (int i = exponent; i < mantissaPart.length(); i++) {
          fractionBinary = fractionBinary + mantissaPart.charAt(i);
        }
      }
    } else if (exponent < 0) {
      fractionBinary = "0.";
      for (int i = exponent + 1; i < 0; i++) {
        fractionBinary = fractionBinary + '0';
      }
      fractionBinary += "1" + mantissaPart;
    } else if (exponent == 0) {
      fractionBinary = "1." + mantissaPart;
    }
    if (ieee754 < 0) {
      fractionBinary = "-" + fractionBinary;
    }
  }

  private void analyseNumber() throws Exception {
    try {
      String decimalText = jTextField1.getText();
      this.ieee754 = Float.parseFloat(decimalText);
      this.ieee754bit = Float.floatToIntBits(ieee754);
      if (this.ieee754 < 0) {
        isNegative = true;
      } else {
        isNegative = false;
      }
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < 32; i++) {
        if ((ieee754bit & 0x01) > 0) {
          sb.insert(0, '1');
        } else {
          sb.insert(0, '0');
        }
        ieee754bit = ieee754bit >> 1;
      }
      ieee754Str = sb.toString();

      this.signPart = ieee754Str.charAt(0) + "";
      this.exponentPart = ieee754Str.substring(1, 9);
      this.mantissaPart = ieee754Str.substring(9, 32);
      this.exponentExcess = Integer.valueOf(exponentPart, 2);
      this.exponent = this.exponentExcess - 127;
    } catch (Exception ex) {
      throw new Exception("Invalid Number");
    }
  }

  private void closeApplication() {
    int result = JOptionPane.showConfirmDialog(this, "Are you sure to quit?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.CANCEL_OPTION) {
      return; /* The Cancel button is pressed */
    }
    System.exit(0);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jPanel1 = new javax.swing.JPanel();
    jTextField1 = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jTextField2 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    jTextField3 = new javax.swing.JTextField();
    jPanel4 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenu2 = new javax.swing.JMenu();
    jMenuItem2 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("IEEE 754 Converter");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.GridBagLayout());

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fractional Value (Decimal)"));
    jPanel1.setPreferredSize(new java.awt.Dimension(720, 60));
    jPanel1.setLayout(new java.awt.GridLayout(1, 0));

    jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18));
    jTextField1.setMinimumSize(new java.awt.Dimension(6, 30));
    jTextField1.setPreferredSize(new java.awt.Dimension(6, 30));
    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });
    jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        jTextField1FocusLost(evt);
      }
    });
    jPanel1.add(jTextField1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(jPanel1, gridBagConstraints);

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("IEEE 754 (32-bit) Representation"));
    jPanel2.setLayout(new java.awt.GridBagLayout());

    jTextField2.setEditable(false);
    jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jTextField2.setMinimumSize(new java.awt.Dimension(6, 30));
    jTextField2.setPreferredSize(new java.awt.Dimension(6, 30));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel2.add(jTextField2, gridBagConstraints);

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel1.setForeground(new java.awt.Color(255, 51, 0));
    jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sign", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
    jLabel1.setMaximumSize(new java.awt.Dimension(600, 40));
    jLabel1.setMinimumSize(new java.awt.Dimension(0, 40));
    jLabel1.setPreferredSize(new java.awt.Dimension(30, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.1;
    jPanel2.add(jLabel1, gridBagConstraints);

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel2.setForeground(new java.awt.Color(0, 153, 0));
    jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Exponent", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
    jLabel2.setMaximumSize(new java.awt.Dimension(600, 40));
    jLabel2.setMinimumSize(new java.awt.Dimension(0, 40));
    jLabel2.setPreferredSize(new java.awt.Dimension(30, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.3;
    jPanel2.add(jLabel2, gridBagConstraints);

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(0, 51, 204));
    jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mantissa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
    jLabel3.setMaximumSize(new java.awt.Dimension(600, 40));
    jLabel3.setMinimumSize(new java.awt.Dimension(0, 40));
    jLabel3.setPreferredSize(new java.awt.Dimension(30, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel2.add(jLabel3, gridBagConstraints);

    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Value of the Representation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
    jLabel6.setMaximumSize(new java.awt.Dimension(600, 40));
    jLabel6.setMinimumSize(new java.awt.Dimension(0, 40));
    jLabel6.setPreferredSize(new java.awt.Dimension(30, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.1;
    jPanel2.add(jLabel6, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(jPanel2, gridBagConstraints);

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fractional Binary Representation"));
    jPanel3.setLayout(new java.awt.GridLayout(1, 0));

    jTextField3.setEditable(false);
    jTextField3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jTextField3.setMinimumSize(new java.awt.Dimension(6, 30));
    jTextField3.setPreferredSize(new java.awt.Dimension(6, 30));
    jPanel3.add(jTextField3);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    getContentPane().add(jPanel3, gridBagConstraints);

    jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Normalization"));
    jPanel4.setLayout(new java.awt.GridLayout(3, 1));

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel4.setMaximumSize(new java.awt.Dimension(0, 30));
    jLabel4.setMinimumSize(new java.awt.Dimension(0, 30));
    jLabel4.setPreferredSize(new java.awt.Dimension(0, 30));
    jPanel4.add(jLabel4);

    jLabel5.setFont(new java.awt.Font("Tahoma", 2, 11));
    jLabel5.setForeground(new java.awt.Color(153, 0, 51));
    jLabel5.setMaximumSize(new java.awt.Dimension(0, 30));
    jLabel5.setMinimumSize(new java.awt.Dimension(0, 30));
    jLabel5.setPreferredSize(new java.awt.Dimension(0, 30));
    jPanel4.add(jLabel5);

    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18));
    jLabel7.setMaximumSize(new java.awt.Dimension(0, 30));
    jLabel7.setMinimumSize(new java.awt.Dimension(0, 30));
    jLabel7.setPreferredSize(new java.awt.Dimension(0, 30));
    jPanel4.add(jLabel7);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    getContentPane().add(jPanel4, gridBagConstraints);

    jMenu1.setText("File");

    jMenuItem1.setText("Quit");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem1ActionPerformed(evt);
      }
    });
    jMenu1.add(jMenuItem1);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("Help");

    jMenuItem2.setText("About IEEE754 Demo");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem2ActionPerformed(evt);
      }
    });
    jMenu2.add(jMenuItem2);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
      updateDisplay();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
      updateDisplay();
    }//GEN-LAST:event_jTextField1FocusLost

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      closeApplication();
}//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      String message = "<html>IEEE 754 Conversion Demo version 1.0<br>Developed by Dr. Andrew Kwok-Fai Lui<br> </html>";
      JOptionPane.showMessageDialog(this, message);
}//GEN-LAST:event_jMenuItem2ActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new IEEE754Bit32Demo().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JTextField jTextField3;
  // End of variables declaration//GEN-END:variables
}
