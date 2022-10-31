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

import faifai.aludemo.widgets.*;
import faifai.aludemo.widgets.Number;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DemoPanelLMC extends javax.swing.JPanel {

  public DemoPanelLMC() {
    initComponents();

    Bus busSystem = new Bus("System Bus");
    Bus busACCtoALU = new Bus("ACC to ALU");

    BusManager.addBus(busACCtoALU);
    BusManager.addBus(busSystem);

    registerDualPort1.setRegisterName("ACC");
    instructionRegister1.setRegisterName("IR");
    programCounter1.setRegisterName("PC");

    registerDualPort1.getCore().getPort(0).setStatus(Port.STATUS_READ);
    registerDualPort1.getCore().getPort(0).setDisableChangeStatus(true);
    registerDualPort1.updateDisplay();

    BusManager.addBus(busSystem);
    BusManager.addBus(busACCtoALU);

    registerDualPort1.getCore().setLMCMode();
    programCounter1.getCore().setLMCMode();
    instructionRegister1.getCore().setLMCMode();
    instructionRegister1.getCore().getNumber().setMaskType(Number.MASK_LMC);
    registerDualPort1.getCore().getNumber().setLowerBound(-999);
    memorySystem1.getMARCore().setLMCMode();
    memorySystem1.getMDRCore().setLMCMode();

    busACCtoALU.addPort(registerDualPort1.getCore().getAuxOutPort());
    busACCtoALU.addPort(aLU1.getInPort2());

    busSystem.addPort(registerDualPort1.getCore().getPort(1));
    busSystem.addPort(aLU1.getInPort1());
    busSystem.addPort(memorySystem1.getMARCore().getPort(0));
    busSystem.addPort(memorySystem1.getMDRCore().getPort(0));
    busSystem.addPort(programCounter1.getCore().getPort(0));
    busSystem.addPort(instructionRegister1.getCore().getPort(0));
    busSystem.addPort(lMCInputOutput1.getInputPort());
    busSystem.addPort(lMCInputOutput1.getOutputPort());

    aLU1.setAccumulatorNumber(registerDualPort1.getCore().getNumber());

    memorySystem1.updateDisplay();
    registerDualPort1.updateDisplay();
    programCounter1.updateDisplay();
    instructionRegister1.updateDisplay();
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
    programCounter1.clockSignalReceived();
    instructionRegister1.clockSignalReceived();
    lMCInputOutput1.clockSignalReceived();

    updateDisplay();
  }

  public void updateDisplay() {
    memorySystem1.updateDisplay();
    registerDualPort1.updateDisplay();
    programCounter1.updateDisplay();
    instructionRegister1.updateDisplay();
    lMCInputOutput1.updateDisplay();
  }

  public void resetDataMovement() {
    memorySystem1.getMARCore().getPort(0).setStatus(Port.STATUS_CLOSED);
    memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_CLOSED);
    registerDualPort1.getCore().getPort(1).setStatus(Port.STATUS_CLOSED);
    programCounter1.getCore().getPort(0).setStatus(Port.STATUS_CLOSED);
    instructionRegister1.getCore().getPort(0).setStatus(Port.STATUS_CLOSED);
    lMCInputOutput1.getInputPort().setStatus(Port.STATUS_CLOSED);
    lMCInputOutput1.getOutputPort().setStatus(Port.STATUS_CLOSED);

    memorySystem1.setLineRW(Port.STATUS_CLOSED);
    aLU1.setFunction(0);
  }

  public Number getInstructionRegister() {
    return this.instructionRegister1.getCore().getNumber();
  }

  public Number getAccumulator() {
    return this.registerDualPort1.getCore().getNumber();
  }

  public void loadMemory(String resource) {
    memorySystem1.loadDefaultMemory(resource);
  }

  public void resetPC() {
    programCounter1.getCore().reset();
  }

  public void setDataMovement(String rtl) {
    if (rtl.equals("PC > MAR")) {
      programCounter1.getCore().getPort(0).setStatus(Port.STATUS_WRITE);
      memorySystem1.getMARCore().getPort(0).setStatus(Port.STATUS_READ);
    } else if (rtl.equals("MEM[MAR] > MDR")) {
      memorySystem1.setLineRW(Port.STATUS_READ);
    } else if (rtl.equals("MDR > IR")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_WRITE);
      instructionRegister1.getCore().getPort(0).setStatus(Port.STATUS_READ);
    } else if (rtl.equals("IR[ADDR] > MAR")) {
      instructionRegister1.getCore().getPort(0).setStatus(Port.STATUS_WRITE);
      memorySystem1.getMARCore().getPort(0).setStatus(Port.STATUS_READ);
    } else if (rtl.equals("MDR > ACC")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_WRITE);
      aLU1.setFunction(3);
    } else if (rtl.equals("ACC + MDR > ACC")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_WRITE);
      aLU1.setFunction(1);
    } else if (rtl.equals("ACC - MDR > ACC")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_WRITE);
      aLU1.setFunction(2);
    } else if (rtl.equals("IR[ADDR] > PC")) {
      instructionRegister1.getCore().getPort(0).setStatus(Port.STATUS_WRITE);
      programCounter1.getCore().getPort(0).setStatus(Port.STATUS_READ);
    } else if (rtl.equals("ACC > MDR")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_READ);
      registerDualPort1.getCore().getPort(1).setStatus(Port.STATUS_WRITE);
    } else if (rtl.equals("MDR > ACC")) {
      memorySystem1.getMDRCore().getPort(0).setStatus(Port.STATUS_WRITE);
      memorySystem1.getMARCore().getPort(0).setStatus(Port.STATUS_READ);
    } else if (rtl.equals("MDR > MEM[MAR]")) {
      memorySystem1.setLineRW(Port.STATUS_WRITE);
    } else if (rtl.equals("PC + 1 > PC")) {
      programCounter1.setToAdd();
    } else if (rtl.equals("IN > ACC")) {
      lMCInputOutput1.getInputPort().setStatus(Port.STATUS_WRITE);
      aLU1.setFunction(3);
    } else if (rtl.equals("ACC > OUT")) {
      lMCInputOutput1.getOutputPort().setStatus(Port.STATUS_READ);
      registerDualPort1.getCore().getPort(1).setStatus(Port.STATUS_WRITE);
    }
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    ComponentLocation memLoc = new ComponentLocation(memorySystem1);
    ComponentLocation accLoc = new ComponentLocation(registerDualPort1);
    ComponentLocation aluLoc = new ComponentLocation(aLU1);
    ComponentLocation pcLoc = new ComponentLocation(programCounter1);
    ComponentLocation irLoc = new ComponentLocation(instructionRegister1);
    ComponentLocation ioLoc = new ComponentLocation(lMCInputOutput1);

    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(4));

    g2d.drawLine(irLoc.centerx + 20, memLoc.centery - 20, memLoc.left, memLoc.centery - 20);
    g2d.drawLine(irLoc.centerx + 20, memLoc.centery + 20, memLoc.left, memLoc.centery + 20);
    g2d.drawLine(irLoc.centerx, irLoc.top, irLoc.centerx, pcLoc.top - 20);
    g2d.drawLine(pcLoc.centerx, pcLoc.top, pcLoc.centerx, pcLoc.top - 20);
    g2d.drawLine(accLoc.centerx, accLoc.bottom, accLoc.centerx, pcLoc.top - 20);

    g2d.drawLine(irLoc.centerx + 20, ioLoc.centery - 20, ioLoc.left, ioLoc.centery - 20);
    g2d.drawLine(irLoc.centerx + 20, ioLoc.centery + 20, ioLoc.left, ioLoc.centery + 20);

    g2d.drawLine(aluLoc.centerx, aluLoc.bottom, aluLoc.centerx, accLoc.top);

    int xpoint[] = {pcLoc.centerx, irLoc.centerx + 20, irLoc.centerx + 20, aluLoc.centerx + 40, aluLoc.centerx + 40};
    int ypoint[] = {pcLoc.top - 20, pcLoc.top - 20, aluLoc.top - 40, aluLoc.top - 40, aluLoc.top};
    g2d.drawPolyline(xpoint, ypoint, 5);

    int xpoint2[] = {accLoc.left, accLoc.left - 40, accLoc.left - 40, aluLoc.centerx - 40, aluLoc.centerx - 40};
    int ypoint2[] = {accLoc.centery, accLoc.centery, aluLoc.top - 40, aluLoc.top - 40, aluLoc.top};
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
    programCounter1 = new faifai.aludemo.widgets.ProgramCounter();
    instructionRegister1 = new faifai.aludemo.widgets.InstructionRegister();
    memorySystem1 = new faifai.aludemo.widgets.MemorySystem();
    lMCInputOutput1 = new faifai.aludemo.widgets.LMCInputOutput();

    setMinimumSize(new java.awt.Dimension(540, 420));
    setOpaque(false);
    setPreferredSize(new java.awt.Dimension(540, 420));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(103, 103, 103)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(registerDualPort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(aLU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(96, 96, 96)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lMCInputOutput1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(memorySystem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addGap(34, 34, 34)
            .addComponent(programCounter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(instructionRegister1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap(45, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(aLU1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(registerDualPort1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(lMCInputOutput1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(memorySystem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(40, 40, 40)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(instructionRegister1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(programCounter1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(55, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private faifai.aludemo.widgets.ALU aLU1;
  private faifai.aludemo.widgets.InstructionRegister instructionRegister1;
  private faifai.aludemo.widgets.LMCInputOutput lMCInputOutput1;
  private faifai.aludemo.widgets.MemorySystem memorySystem1;
  private faifai.aludemo.widgets.ProgramCounter programCounter1;
  private faifai.aludemo.widgets.RegisterDualPort registerDualPort1;
  // End of variables declaration//GEN-END:variables
}
