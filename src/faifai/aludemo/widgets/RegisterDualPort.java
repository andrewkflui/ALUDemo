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

import java.awt.Color;

public class RegisterDualPort extends javax.swing.JPanel implements Component {

  protected Core theCore;

  public RegisterDualPort() {
    initComponents();
    Number theNumber = new Number();
    theCore = new Core(theNumber);
    theCore.addPort(new Port(theNumber, "REG IN", Port.PORT_IN));
    theCore.addPort(new Port(theNumber, "REG OUT", Port.PORT_OUT));
    int range[] = {Core.MODE_DEC, Core.MODE_HEX};
    theCore.setRepresentationRange(range);
    theCore.setRepresentationMode(Core.MODE_DEC);
    updateDisplay();
  }

  public Core getCore() {
    return theCore;
  }

  public void clockSignalReceived() {
    theCore.clockSignalReceived();
    updateDisplay();
  }

  public void setRegisterName(String name) {
    jLabel1.setText(name);
  }

  public void updateDisplay() {
    jTextField1.setText(theCore.getValueStr());
    jButton1.setText(theCore.getRepresentationStr());
    Port inPort = theCore.getPort(0);
    jButton2.setText(inPort.getStatusString());
    if (inPort.getStatus() == Port.STATUS_READ) {
      jButton2.setBackground(new Color(204, 255, 102));
    } else if (inPort.getStatus() == Port.STATUS_WRITE) {
      jButton2.setBackground(Color.PINK);
    } else {
      jButton2.setBackground(Color.ORANGE);
    }

    Port outPort = theCore.getPort(1);
    jButton3.setText(outPort.getStatusString());
    if (outPort.getStatus() == Port.STATUS_READ) {
      jButton3.setBackground(new Color(204, 255, 102));
    } else if (outPort.getStatus() == Port.STATUS_WRITE) {
      jButton3.setBackground(Color.PINK);
    } else {
      jButton3.setBackground(Color.ORANGE);
    }
  }

  protected void textFieldUpdated() {
    theCore.setValueStr(jTextField1.getText());
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

    jLabel1 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jButton3 = new javax.swing.JButton();

    setBackground(new java.awt.Color(153, 51, 0));
    setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
    setMinimumSize(new java.awt.Dimension(110, 70));
    setPreferredSize(new java.awt.Dimension(110, 70));
    setLayout(new java.awt.GridBagLayout());

    jLabel1.setBackground(new java.awt.Color(204, 204, 255));
    jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jLabel1.setMaximumSize(new java.awt.Dimension(40, 20));
    jLabel1.setMinimumSize(new java.awt.Dimension(40, 18));
    jLabel1.setOpaque(true);
    jLabel1.setPreferredSize(new java.awt.Dimension(40, 18));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    add(jLabel1, gridBagConstraints);

    jTextField1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
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
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
    add(jTextField1, gridBagConstraints);

    jPanel1.setOpaque(false);
    jPanel1.setPreferredSize(new java.awt.Dimension(131, 30));
    jPanel1.setLayout(new java.awt.GridBagLayout());

    jButton1.setBackground(new java.awt.Color(255, 255, 153));
    jButton1.setFont(new java.awt.Font("Tahoma", 0, 9));
    jButton1.setText("Dec");
    jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton1.setMaximumSize(new java.awt.Dimension(40, 18));
    jButton1.setMinimumSize(new java.awt.Dimension(40, 18));
    jButton1.setPreferredSize(new java.awt.Dimension(40, 18));
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        numeralActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel1.add(jButton1, gridBagConstraints);

    jButton2.setBackground(new java.awt.Color(255, 204, 0));
    jButton2.setFont(new java.awt.Font("Tahoma", 0, 9));
    jButton2.setText("Closed");
    jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton2.setMaximumSize(new java.awt.Dimension(40, 18));
    jButton2.setMinimumSize(new java.awt.Dimension(40, 18));
    jButton2.setPreferredSize(new java.awt.Dimension(40, 18));
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        statusActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel1.add(jButton2, gridBagConstraints);

    jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("IN");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    jPanel1.add(jLabel2, gridBagConstraints);

    jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("OUT");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    jPanel1.add(jLabel3, gridBagConstraints);

    jButton3.setBackground(new java.awt.Color(255, 204, 0));
    jButton3.setFont(new java.awt.Font("Tahoma", 0, 9));
    jButton3.setText("Closed");
    jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton3.setMaximumSize(new java.awt.Dimension(40, 18));
    jButton3.setMinimumSize(new java.awt.Dimension(40, 18));
    jButton3.setPreferredSize(new java.awt.Dimension(40, 18));
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3statusActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel1.add(jButton3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(jPanel1, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

    private void numeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeralActionPerformed
      theCore.nextRepresentationMode();
      updateDisplay();
    }//GEN-LAST:event_numeralActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
      textFieldUpdated();
      updateDisplay();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
      Port thePort = theCore.getPort(0);
      thePort.nextStatus();
      updateDisplay();
}//GEN-LAST:event_statusActionPerformed

    private void jButton3statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3statusActionPerformed
      Port thePort = theCore.getPort(1);
      thePort.nextStatus();
      updateDisplay();
    }//GEN-LAST:event_jButton3statusActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
      textFieldUpdated();
      updateDisplay();
    }//GEN-LAST:event_jTextField1FocusLost

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField jTextField1;
  // End of variables declaration//GEN-END:variables
}
