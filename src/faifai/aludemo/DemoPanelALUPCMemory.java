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

import faifai.aludemo.widgets.*;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class DemoPanelALUPCMemory extends javax.swing.JPanel {

  /** Creates new form ALUTest */
  public DemoPanelALUPCMemory() {
    initComponents();

    Bus busR0toALU = new Bus("R0 to ALU");
    Bus busSystem = new Bus("System Bus");

    BusManager.addBus(busR0toALU);
    BusManager.addBus(busSystem);

    registerDualPort1.setRegisterName("R0");
    registerDualPort2.setRegisterName("ACC");
    instructionRegister1.setRegisterName("IR");
    programCounter1.setRegisterName("PC");

    busR0toALU.addPort(registerDualPort1.getCore().getPort(1));
    busR0toALU.addPort(aLU1.getInPort2());

    busSystem.addPort(registerDualPort1.getCore().getPort(0));
    busSystem.addPort(registerDualPort2.getCore().getPort(1));
    busSystem.addPort(aLU1.getInPort1());
    busSystem.addPort(memorySystem1.getMARCore().getPort(0));
    busSystem.addPort(memorySystem1.getMDRCore().getPort(0));

    busSystem.addPort(programCounter1.getCore().getPort(0));
    busSystem.addPort(instructionRegister1.getCore().getPort(0));

    aLU1.setAccumulatorNumber(registerDualPort2.getCore().getNumber());
  }

  public void clockSignalReceived() {
    ArrayList<Bus> busList = BusManager.getBusList();
    for (Bus bus : busList) {
      if (!bus.checkIntegrity()) {
        JOptionPane.showMessageDialog(this, "Error in bus " + bus.getName(),
                "Bus Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }
    memorySystem1.clockSignalReceived();
    aLU1.clockSignalReceived();
    registerDualPort1.clockSignalReceived();
    registerDualPort2.clockSignalReceived();
    instructionRegister1.clockSignalReceived();
    programCounter1.clockSignalReceived();

    memorySystem1.updateDisplay();
    registerDualPort1.updateDisplay();
    registerDualPort2.updateDisplay();
    programCounter1.updateDisplay();
    instructionRegister1.updateDisplay();
  }

  private Point getCenter(JComponent comp) {
    Point loc = comp.getLocation();
    Dimension dim = comp.getSize();
    Point center = new Point(loc.x + dim.width / 2, loc.y + dim.height / 2);
    return center;
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    ComponentLocation r0Loc = new ComponentLocation(registerDualPort1);
    ComponentLocation accLoc = new ComponentLocation(registerDualPort2);
    ComponentLocation aluLoc = new ComponentLocation(aLU1);
    ComponentLocation memLoc = new ComponentLocation(memorySystem1);
    ComponentLocation pcLoc = new ComponentLocation(programCounter1);
    ComponentLocation irLoc = new ComponentLocation(instructionRegister1);

    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(4));

    g2d.drawLine(aluLoc.centerx, aluLoc.bottom, aluLoc.centerx, accLoc.top);

    g2d.drawLine(accLoc.centerx + 120, memLoc.centery - 20, memLoc.left, memLoc.centery - 20);
    g2d.drawLine(accLoc.centerx + 120, memLoc.centery + 20, memLoc.left, memLoc.centery + 20);
    g2d.drawLine(accLoc.centerx + 120, r0Loc.centery, r0Loc.left, r0Loc.centery);
    g2d.drawLine(accLoc.centerx + 120, pcLoc.centery, pcLoc.left, pcLoc.centery);

    g2d.drawLine(irLoc.right, accLoc.bottom + 40, accLoc.centerx, accLoc.bottom + 40);

    int xpoint[] = {accLoc.centerx, accLoc.centerx, accLoc.centerx + 120, accLoc.centerx + 120, aluLoc.centerx + 40, aluLoc.centerx + 40};
    int ypoint[] = {accLoc.bottom, accLoc.bottom + 40, accLoc.bottom + 40, r0Loc.centery, r0Loc.centery, aluLoc.top};
    g2d.drawPolyline(xpoint, ypoint, 6);

    int xpoint2[] = {r0Loc.right, r0Loc.right+40, r0Loc.right+40, aluLoc.centerx-40, aluLoc.centerx-40};
    int ypoint2[] = {r0Loc.centery, r0Loc.centery, r0Loc.centery-80, r0Loc.centery-80, aluLoc.top};
    g2d.drawPolyline(xpoint2, ypoint2, 5);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    aLU1 = new faifai.aludemo.widgets.ALU();
    registerDualPort1 = new faifai.aludemo.widgets.RegisterDualPort();
    registerDualPort2 = new faifai.aludemo.widgets.RegisterDualPort();
    memorySystem1 = new faifai.aludemo.widgets.MemorySystem();
    programCounter1 = new faifai.aludemo.widgets.ProgramCounter();
    instructionRegister1 = new faifai.aludemo.widgets.InstructionRegister();

    setMinimumSize(new java.awt.Dimension(640, 480));
    setPreferredSize(new java.awt.Dimension(640, 480));

    aLU1.setMinimumSize(new java.awt.Dimension(110, 140));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(124, 124, 124)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(aLU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(registerDualPort2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(instructionRegister1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(124, 124, 124)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(registerDualPort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(memorySystem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(programCounter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(37, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(69, 69, 69)
        .addComponent(registerDualPort1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(47, 47, 47)
        .addComponent(memorySystem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(30, 30, 30)
        .addComponent(programCounter1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(106, 106, 106))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(150, Short.MAX_VALUE)
        .addComponent(aLU1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(32, 32, 32)
        .addComponent(registerDualPort2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(instructionRegister1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(33, 33, 33))
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private faifai.aludemo.widgets.ALU aLU1;
  private faifai.aludemo.widgets.InstructionRegister instructionRegister1;
  private faifai.aludemo.widgets.MemorySystem memorySystem1;
  private faifai.aludemo.widgets.ProgramCounter programCounter1;
  private faifai.aludemo.widgets.RegisterDualPort registerDualPort1;
  private faifai.aludemo.widgets.RegisterDualPort registerDualPort2;
  // End of variables declaration//GEN-END:variables
}
