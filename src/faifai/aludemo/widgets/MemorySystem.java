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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.*;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MemorySystem extends javax.swing.JPanel implements Component {

    protected Core marCore;
    protected Core mdrCore;
    protected int lineRW = Port.STATUS_CLOSED;
    protected static final int MEMORY_SIZE = 256;
    private MemoryTableModel model;
    private MemoryTablePanel editorTable;

    /** Creates new form MemorySystem */
    public MemorySystem() {
        initComponents();
        Number marNumber = new Number();
        Number mdrNumber = new Number();
        marCore = new Core(marNumber);
        mdrCore = new Core(mdrNumber);

        marCore.addPort(new Port(marNumber, "MAR IN", Port.PORT_IN));
        int range[] = { Core.MODE_DEC, Core.MODE_HEX };
        marCore.setRepresentationRange(range);
        marCore.setRepresentationMode(Core.MODE_DEC);

        mdrCore.addPort(new Port(mdrNumber, "MDR INOUT", Port.PORT_INOUT));
        mdrCore.setRepresentationRange(range);
        mdrCore.setRepresentationMode(Core.MODE_DEC);

        model = new MemoryTableModel(MEMORY_SIZE);
        editorTable = new MemoryTablePanel();
        editorTable.setTableModel(model);
        editorTable.setColumnWidth("Address", 100);
        editorTable.refresh();

        updateDisplay();
    }

    public void updateDisplay() {
        jTextField1.setText(marCore.getValueStr());
        jTextField2.setText(mdrCore.getValueStr());
        jButton1.setText(marCore.getRepresentationStr());
        Port marPort = marCore.getPort(0);
        jButton6.setText(marPort.getStatusString());
        if (marPort.getStatus() == Port.STATUS_READ) {
            jButton6.setBackground(new Color(204, 255, 102));
        } else if (marPort.getStatus() == Port.STATUS_WRITE) {
            jButton6.setBackground(Color.PINK);
        } else {
            jButton6.setBackground(Color.ORANGE);
        }

        jButton4.setText(mdrCore.getRepresentationStr());
        Port mdrPort = mdrCore.getPort(0);
        jButton7.setText(mdrPort.getStatusString());
        if (mdrPort.getStatus() == Port.STATUS_READ) {
            jButton7.setBackground(new Color(204, 255, 102));
        } else if (mdrPort.getStatus() == Port.STATUS_WRITE) {
            jButton7.setBackground(Color.PINK);
        } else {
            jButton7.setBackground(Color.ORANGE);
        }

        if (lineRW == Port.STATUS_CLOSED) {
            jButton2.setText("No Op");
        } else if (lineRW == Port.STATUS_READ) {
            jButton2.setText("Read");
        } else if (lineRW == Port.STATUS_WRITE) {
            jButton2.setText("Write");
        }
    }

    public Core getMARCore() {
        return this.marCore;
    }

    public Core getMDRCore() {
        return this.mdrCore;
    }

    public void clockSignalReceived() {
        if (lineRW == Port.STATUS_CLOSED) {
        } else if (lineRW == Port.STATUS_READ) {
            mdrCore.getNumber().setValue((Integer) model.getData(marCore.getNumber().getValue()));
        } else if (lineRW == Port.STATUS_WRITE) {
            model.setValueAt(mdrCore.getNumber().getValue(), marCore.getNumber().getValue(), 1);
        }
        updateDisplay();
    }

    public void setLineRW(int lineRW) {
        this.lineRW = lineRW;
    }

    protected void textFieldUpdatedMAR() {
        marCore.setValueStr(jTextField1.getText());
    }

    protected void textFieldUpdatedMDR() {
        mdrCore.setValueStr(jTextField2.getText());
    }

    public void loadDefaultMemory(String resource) {
        InputStream instream = this.getClass().getResourceAsStream(resource);
        int data[] = readMemoryFile(instream);
        model.clear();
        model.setData(data);
        editorTable.refresh();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        ComponentLocation loc1 = new ComponentLocation(jPanel1);
        ComponentLocation loc2 = new ComponentLocation(jPanel2);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(loc1.right, loc1.centery, loc2.left, loc1.centery);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MAR");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setMaximumSize(new java.awt.Dimension(40, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(40, 18));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jLabel1, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MDR");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setMaximumSize(new java.awt.Dimension(40, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(40, 18));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jTextField2, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton2.setText("No Op");
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton2.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton2.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton2, gridBagConstraints);

        jLabel3.setBackground(new java.awt.Color(153, 51, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("R/W");
        jLabel3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel3, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton1.setText("Dec");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton1.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton1.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1numeralActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton4.setBackground(new java.awt.Color(255, 255, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton4.setText("Dec");
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton4.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton4.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4numeralActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jButton4, gridBagConstraints);

        jButton6.setBackground(new java.awt.Color(255, 204, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton6.setText("Closed");
        jButton6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton6.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton6.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton6.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton6, gridBagConstraints);

        jButton7.setBackground(new java.awt.Color(255, 204, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton7.setText("Closed");
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton7.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton7.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton7, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setBackground(new java.awt.Color(204, 255, 204));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html>Memory<br />\nSystem");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setMaximumSize(new java.awt.Dimension(40, 20));
        jLabel4.setMinimumSize(new java.awt.Dimension(40, 18));
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel4, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(153, 204, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 9));
        jButton3.setText("View Data");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton3.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton3.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton3, gridBagConstraints);

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 9));
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton5.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton5.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton5, gridBagConstraints);

        jButton8.setBackground(new java.awt.Color(153, 204, 0));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 9));
        jButton8.setText("Load");
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton8.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton8.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton8, gridBagConstraints);

        jButton9.setBackground(new java.awt.Color(153, 204, 0));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 9));
        jButton9.setText("Save");
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.setMaximumSize(new java.awt.Dimension(40, 18));
        jButton9.setMinimumSize(new java.awt.Dimension(40, 18));
        jButton9.setPreferredSize(new java.awt.Dimension(40, 18));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9statusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton9, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33,
                                        Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        textFieldUpdatedMAR();
        updateDisplay();
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextField1FocusLost
        textFieldUpdatedMAR();
        updateDisplay();
    }// GEN-LAST:event_jTextField1FocusLost

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
        textFieldUpdatedMDR();
        updateDisplay();
    }// GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextField2FocusLost
        textFieldUpdatedMDR();
        updateDisplay();
    }// GEN-LAST:event_jTextField2FocusLost

    private void jButton2statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2statusActionPerformed
        this.lineRW = (this.lineRW + 1) % 3;
        updateDisplay();
    }// GEN-LAST:event_jButton2statusActionPerformed

    private void jButton1numeralActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1numeralActionPerformed
        marCore.nextRepresentationMode();
        updateDisplay();
    }// GEN-LAST:event_jButton1numeralActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_viewActionPerformed
        JOptionPane.showMessageDialog(this, editorTable, "Memory Data", JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_viewActionPerformed

    private void jButton4numeralActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4numeralActionPerformed
        mdrCore.nextRepresentationMode();
        updateDisplay();
    }// GEN-LAST:event_jButton4numeralActionPerformed

    private void jButton5statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5statusActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton5statusActionPerformed

    private void jButton6statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6statusActionPerformed
        Port thePort = marCore.getPort(0);
        thePort.nextStatus();
        updateDisplay();
    }// GEN-LAST:event_jButton6statusActionPerformed

    private void jButton7statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7statusActionPerformed
        Port thePort = mdrCore.getPort(0);
        thePort.nextStatus();
        updateDisplay();
    }// GEN-LAST:event_jButton7statusActionPerformed

    private void jButton8statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton8statusActionPerformed
        JFileChooser loadfileChooser;
        loadfileChooser = new JFileChooser();
        loadfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        loadfileChooser.setFileFilter(new MemoryDataFileFilter());
        loadfileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        loadfileChooser.setDialogTitle("Select Memory Data File");
        int result = loadfileChooser.showOpenDialog(null);
        File selectedFile;
        if (result == JFileChooser.CANCEL_OPTION) {
            selectedFile = null;
            return;
        }
        selectedFile = loadfileChooser.getSelectedFile();
        // Vector data = (Vector) SerializableHelper.load(selectedFile);
        int data[] = readMemoryFile(selectedFile);
        model.clear();
        model.setData(data);
        editorTable.refresh();
    }// GEN-LAST:event_jButton8statusActionPerformed

    private void jButton9statusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton9statusActionPerformed
        JFileChooser savefileChooser;
        savefileChooser = new JFileChooser();
        savefileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        savefileChooser.setFileFilter(new MemoryDataFileFilter());
        savefileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        savefileChooser.setDialogTitle("Select Memory Data File");
        int result = savefileChooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File selectedFile = savefileChooser.getSelectedFile();
        if (!selectedFile.getName().endsWith(".mem")) {
            selectedFile = new File(selectedFile.getParent(), selectedFile.getName() + ".mem");
        }
        // SerializableHelper.save(selectedFile, model.dumpData());
        writeMemoryFile(selectedFile, model.dumpData());
    }// GEN-LAST:event_jButton9statusActionPerformed

    private static int[] readMemoryFile(File theFile) {
        try {
            return readMemoryFile(new FileInputStream(theFile));
        } catch (Exception ex) {
        }
        return null;
    }

    private static int[] readMemoryFile(InputStream instream) {
        int data[] = new int[MEMORY_SIZE];
        try {
            InputStreamReader reader = new InputStreamReader(instream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String s[] = line.split(",");
                if (s.length != 2)
                    continue;
                int addr, value;
                try {
                    addr = Integer.parseInt(s[0]);
                    value = Integer.parseInt(s[1]);
                } catch (Exception ex) {
                    continue;
                }
                data[addr] = value;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void writeMemoryFile(File theFile, int data[]) {
        if (data == null || data.length == 0)
            return;
        try {
            FileWriter writer = new FileWriter(theFile, false);
            for (int i = 0; i < data.length; i++) {
                writer.write(String.format("%d,%d", i, data[i]));
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}

class MemoryDataFileFilter extends FileFilter {

    public boolean accept(File file) {
        if (file == null) {
            return false;
        }
        if (file.getName().endsWith(".mem")) {
            return true;
        } else if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public String getDescription() {
        return "Memory Data File (mem)";
    }
}
