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
package faifai.aludemo;

import javax.swing.JOptionPane;

public class LMCRTLDemo extends javax.swing.JFrame {

  /** Creates new form ALUDemo */
  public LMCRTLDemo() {
    try {
        javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (Exception ex) {
    }
    initComponents();
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

    demoPanelLMC1 = new faifai.aludemo.DemoPanelLMC();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();
    jLabel1 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenu2 = new javax.swing.JMenu();
    jMenuItem2 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("LMC Data Movement Demo");
    setBackground(new java.awt.Color(255, 255, 102));
    setMinimumSize(new java.awt.Dimension(800, 420));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.GridBagLayout());

    demoPanelLMC1.setBackground(new java.awt.Color(255, 255, 153));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(demoPanelLMC1, gridBagConstraints);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("RTL Commands"));
    jPanel1.setMinimumSize(new java.awt.Dimension(200, 40));
    jPanel1.setPreferredSize(new java.awt.Dimension(200, 40));
    jPanel1.setLayout(new java.awt.GridBagLayout());

    jScrollPane1.setPreferredSize(new java.awt.Dimension(113, 280));

    jList1.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "PC > MAR", "MEM[MAR] > MDR", "MDR > IR", "IR[ADDR] > MAR", "MDR > ACC", "ACC + MDR > ACC", "ACC - MDR > ACC", "IR[ADDR] > PC", "ACC > MDR", "MDR > MAR", "MDR > MEM[MAR]", "PC + 1 > PC", "IN > ACC", "ACC > OUT" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        jList1ValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(jList1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel1.add(jScrollPane1, gridBagConstraints);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel1.add(jLabel1, gridBagConstraints);

    jButton2.setBackground(new java.awt.Color(255, 204, 204));
    jButton2.setFont(new java.awt.Font("Tahoma", 1, 10));
    jButton2.setText("Send Clock Signal");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        clockSignalActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    jPanel1.add(jButton2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(jPanel1, gridBagConstraints);

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

    jMenuItem2.setText("About LMCDemo");
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      closeApplication();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      closeApplication();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      String message = "<html>LMC RTL Data Movement Demo version 1.0<br>Developed by Dr. Andrew Kwok-Fai Lui<br> </html>";
      JOptionPane.showMessageDialog(this, message);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void clockSignalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockSignalActionPerformed
      demoPanelLMC1.clockSignalReceived();
      demoPanelLMC1.resetDataMovement();
      jList1.clearSelection();
      demoPanelLMC1.updateDisplay();
    }//GEN-LAST:event_clockSignalActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
      demoPanelLMC1.resetDataMovement();
      Object selected[] = jList1.getSelectedValues();
      for (int i=0; i<selected.length; i++) {
        demoPanelLMC1.setDataMovement((String)selected[i]);
      }
      demoPanelLMC1.updateDisplay();
    }//GEN-LAST:event_jList1ValueChanged

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new LMCRTLDemo().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private faifai.aludemo.DemoPanelLMC demoPanelLMC1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JList jList1;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  // End of variables declaration//GEN-END:variables
}
