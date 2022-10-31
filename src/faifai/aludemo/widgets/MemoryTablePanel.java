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
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MemoryTablePanel extends javax.swing.JPanel {

  MemoryTableModel model = null;
  int mode = 10;

  class EditableCellRenderer extends JLabel implements TableCellRenderer {

    private Color lockedColor = new Color(255, 200, 200);

    public EditableCellRenderer() {
      this.setOpaque(true);
      this.setForeground(Color.black);
      this.setFont(new Font("Dialog", 0, 12));
    }

    public void setLockedColor(Color lockedColor) {
      this.lockedColor = lockedColor;
    }

    public java.awt.Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
      int bits = 32;
      if (column == 0) {
        bits = 8;
      }

      if (value == null) {
        this.setText(null);
      } else {
        int number = (Integer) value;
        if (mode == 10) {
          this.setText(value + "");
        } else if (mode == 2) {
          String binString = Integer.toBinaryString(number);
          int len = binString.length();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < bits; i++) {
            if (i % 4 == 0) {
              sb.append(' ');
            }
            if (i < bits - len) {
              sb.append('0');
            } else {
              sb.append(binString.charAt(i - (bits - len)));
            }
          }
          this.setText(sb.toString());
        } else if (mode == 16) {
          String hexString = Integer.toHexString(number).toUpperCase();
          int len = hexString.length();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < (bits / 4) - len; i++) {
            sb.append("0");
          }
          this.setText(sb.toString() + hexString);
        } else if (mode == 30) {
          String decString = value + "";
          int len = decString.length();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < 3 - len; i++) {
            sb.append("0");
          }
          this.setText(sb.toString() + decString);
        }
      }

      if (isSelected) {
        this.setBackground(table.getSelectionBackground());
      } else {
        if (table.getModel().isCellEditable(row, column)) {
          this.setBackground(Color.white);
        } else {
          this.setBackground(lockedColor);
        }
      }
      return this;
    }
  }

  public MemoryTablePanel() {
    initComponents();
    EditableCellRenderer renderer = new EditableCellRenderer();
    jTable1.setDefaultRenderer(Integer.class, renderer);

    jTable1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    jTable1.getTableHeader().setBackground(Color.ORANGE);
    jTable1.setDefaultEditor(Integer.class, new CellEditor());
  }

  public void setTableModel(MemoryTableModel model) {
    this.model = model;
    jTable1.setModel(model);
  }

  public void refresh() {
    jTable1.revalidate();
    jTable1.repaint();
  }

  public JTable getTable() {
    return jTable1;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  protected void disableMode() {
    jButton1.setEnabled(false);
  }

  public void setColumnWidth(String colname, int width) {
    TableColumn col = jTable1.getColumn(colname);
    if (col == null) {
      return;
    }
    col.setPreferredWidth(width);
    col.setMaxWidth(width);
  }

  void scrollToTop() {
    JScrollBar vbar = jScrollPane1.getVerticalScrollBar();
    vbar.setValue(vbar.getMinimum());
  }

  void scrollToBottom() {
    JScrollBar vbar = jScrollPane1.getVerticalScrollBar();
    vbar.setValue(vbar.getMaximum());
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

    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jPanel1 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();

    setLayout(new java.awt.GridBagLayout());

    jScrollPane1.setViewportView(jTable1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(jScrollPane1, gridBagConstraints);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    jButton1.setBackground(new java.awt.Color(255, 153, 0));
    jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
    jButton1.setText("Dec");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    jPanel1.add(jButton1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(jPanel1, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (mode == 10) {
      mode = 16;
      jButton1.setText("Hex");
    } else if (mode == 16) {
      mode = 2;
      jButton1.setText("Bin");
    } else if (mode == 2) {
      mode = 10;
      jButton1.setText("Dec");
    }
    refresh();
  }//GEN-LAST:event_jButton1ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;
  // End of variables declaration//GEN-END:variables

  public class CellEditor extends DefaultCellEditor {

    JTextField textfield;

    public CellEditor() {
      super(new CellTextField());
      textfield = (JTextField) getComponent();
    }

    //Override to ensure that the value remains an Integer.
    public Object getCellEditorValue() {
      int value = 0;
      try {
        String text = textfield.getText().replaceAll(" ", "");
        if (mode == 10 || mode == 30) {
          value = Integer.valueOf(text);
        } else if (mode == 16) {
          value = Integer.valueOf(text, 16);
        } else if (mode == 2) {
          value = Integer.valueOf(text, 2);
        }
        return new Integer(value);
      } catch (Exception ex) {
        return null;
      }
    }

    public boolean stopCellEditing() {
      if (getCellEditorValue() == null) {
        JOptionPane.showMessageDialog(null, "Invalid value", "Input Error", JOptionPane.ERROR_MESSAGE);
        super.cancelCellEditing();
        return false;
      }
      boolean result = super.stopCellEditing();
      return result;
    }
  }

  public class CellTextField extends JTextField {

    public CellTextField() {
      super();
    }

    public void setText(String text) {
      try {
        int value = Integer.valueOf(text);
        if (mode == 10) {
        } else if (mode == 16) {
          text = Integer.toHexString(value);
        } else if (mode == 2) {
          text = Integer.toBinaryString(value);
        }
      } catch (Exception ex) {
      }
      super.setText(text);
    }
  }
}
