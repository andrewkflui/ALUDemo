/***************************************************************
 *
 * Copyright (c) 2010 Dr. Andrew Kwok-Fai LUI
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
package helper.gui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TutorialFrame extends javax.swing.JFrame {

  public TutorialFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setAlwaysOnTop(true);
  }

  public void loadText(String resource) throws Exception {
    InputStream instream = this.getClass().getResourceAsStream(resource);
    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    jEditorPane1.setContentType("text/html");
    jEditorPane1.setText(sb.toString());
    jEditorPane1.setCaretPosition(0);
    jEditorPane1.repaint();
  }

  public void setTutorialTopic(String title) {
    jLabel1.setText(title);
  }

  public void hideFrame() {
    this.setVisible(false);
  }

  public void showFrame() {
    this.setVisible(true);
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
    jScrollPane1 = new javax.swing.JScrollPane();
    jEditorPane1 = new javax.swing.JEditorPane();
    jPanel1 = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Help");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.GridBagLayout());

    jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jLabel1.setMaximumSize(new java.awt.Dimension(34, 30));
    jLabel1.setMinimumSize(new java.awt.Dimension(34, 30));
    jLabel1.setPreferredSize(new java.awt.Dimension(34, 30));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    getContentPane().add(jLabel1, gridBagConstraints);

    jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 320));

    jEditorPane1.setContentType("text/html");
    jEditorPane1.setEditable(false);
    jEditorPane1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
    jEditorPane1.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \rabc\n    </p>\r\n  </body>\r\n</html>\r\n");
    jEditorPane1.setMinimumSize(new java.awt.Dimension(480, 600));
    jScrollPane1.setViewportView(jEditorPane1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
    getContentPane().add(jScrollPane1, gridBagConstraints);

    jPanel1.setMinimumSize(new java.awt.Dimension(0, 30));
    jPanel1.setPreferredSize(new java.awt.Dimension(0, 30));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 277, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 30, Short.MAX_VALUE)
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(jPanel1, gridBagConstraints);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    hideFrame();
  }//GEN-LAST:event_formWindowClosing

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new TutorialFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JEditorPane jEditorPane1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  // End of variables declaration//GEN-END:variables
}
