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

import java.util.*;
import javax.swing.table.DefaultTableModel;

public class MemoryTableModel extends DefaultTableModel {

    protected boolean islocked = false;
    protected BitSet lockedRowSet = new BitSet();
    protected BitSet lockedColumnSet = new BitSet();

    private static final String columnNames[] = new String[] { "Address", "Value" };
    private int memorySize = 256;
    private int data[];

    public MemoryTableModel(int memorySize) {
        super(columnNames, memorySize);
        this.memorySize = memorySize;
        this.data = new int[memorySize];
        for (int i = 0; i < memorySize; i++) {
            data[i] = 0;
            this.setValueAt(new Integer(i), i, 0);
            this.setValueAt(0, i, 1);
        }
        this.setEditableColumn(0, false);
        this.setEditableColumn(1, true);
    }

    public synchronized void setValueAt(Object idata, int row, int column) {
        if (row >= super.getRowCount()) {
            super.setRowCount(row + 1);
        }
        super.setValueAt(idata, row, column);
    }

    public Class getColumnClass(int c) {
        if (super.getRowCount() > 0) {
            Object obj = getValueAt(0, c);
            if (obj != null)
                return getValueAt(0, c).getClass();
        }
        return String.class;
    }

    /*
     * public synchronized void sortColumn(String columnName) {
     * int column = super.columnIdentifiers.indexOf(columnName);
     * if (column < 0 || column >= super.getColumnCount())
     * return;
     * sortColumn(column);
     * }
     * 
     * public synchronized void sortColumn(int column) {
     * if (column > super.getColumnCount())
     * return;
     * final TreeMap treeMap = new TreeMap();
     * for (int i = 0; i < super.dataVector.size(); i++) {
     * Vector resultElement = (Vector) super.dataVector.elementAt(i);
     * Object data = (resultElement.elementAt(column));
     * if (data == null)
     * treeMap.put("" + i, resultElement);
     * else
     * treeMap.put((resultElement.elementAt(column)).toString() + i, resultElement);
     * }
     * 
     * super.dataVector.clear();
     * Iterator iterator = treeMap.keySet().iterator();
     * int i = 0;
     * while (iterator.hasNext()) {
     * super.dataVector.add(i++, (Vector) treeMap.get(iterator.next()));
     * }
     * }
     */
    public synchronized void clear() {
        super.dataVector.clear();
    }

    public synchronized void lock() {
        this.islocked = true;
    }

    public synchronized void unlock() {
        this.islocked = false;
    }

    public synchronized void setEditableRowRange(int start, int end, boolean editable) {

        if (editable) {
            for (int i = start; i <= end; i++)
                this.lockedRowSet.clear(i);
        } else {
            for (int i = start; i <= end; i++)
                this.lockedRowSet.set(i);
        }
    }

    public synchronized void setEditableColumn(int column, boolean editable) {
        if (editable)
            this.lockedColumnSet.clear(column);
        else
            this.lockedColumnSet.set(column);
    }

    public synchronized void setEditableRows(int[] rows, boolean editable) {
        if (rows == null)
            return;

        if (editable) {
            for (int i = 0; i < rows.length; i++)
                this.lockedRowSet.clear(rows[i]);
        } else {
            for (int i = 0; i < rows.length; i++) {
                this.lockedRowSet.set(rows[i]);
            }
        }
    }

    public boolean isCellEditable(int row, int column) {
        if (islocked)
            return false;
        if (lockedRowSet == null || lockedColumnSet == null)
            return true;

        boolean locked = (row < this.lockedRowSet.size()) && this.lockedRowSet.get(row);
        locked = locked || (column < this.lockedColumnSet.size()) && this.lockedColumnSet.get(column);
        return !locked;
    }

    /*
     * public synchronized void addDataWithColumn(Vector data) {
     * if (data == null || data.size() == 0)
     * return;
     * HashMap record = (HashMap) data.get(0);
     * Iterator it = record.keySet().iterator();
     * Vector columns = new Vector();
     * while (it.hasNext()) {
     * columns.add(it.next());
     * }
     * super.setColumnIdentifiers(columns);
     * addData(data);
     * }
     */
    /**
     * Set the Vector of HashMaps of data into the model
     */

    public synchronized void setData(int data[]) {
        if (data == null || data.length == 0)
            return;
        for (int i = 0; i < memorySize; i++) {
            if (i >= data.length)
                break;
            this.setValueAt(new Integer(i), i, 0);
            this.setValueAt(data[i], i, 1);
            this.data[i] = data[i];
        }
    }

    public synchronized int getData(int addr) {
        try {
            return (int) this.getValueAt(addr, 1);
        } catch (Exception ex) {
            return -1;
        }
    }

    public synchronized int[] dumpData() {
        try {
            for (int i = 0; i < memorySize; i++) {
                this.data[i] = (int) this.getValueAt(i, 1);
            }
            return this.data;
        } catch (Exception ex) {
            return null;
        }
    }

    /*
     * public synchronized void addData(Vector data) {
     * if (data == null)
     * return;
     * int columnCount = super.getColumnCount();
     * for (int i = 0; i < data.size(); i++) {
     * HashMap record = (HashMap) data.get(i);
     * String field;
     * Vector v = new Vector();
     * for (int j = 0; j < columnCount; j++) {
     * field = (String) super.columnIdentifiers.get(j);
     * v.add(record.get(field));
     * // System.out.println("'" + record.get(field) + "'");
     * }
     * super.dataVector.add(v);
     * }
     * }
     * 
     * public synchronized int getData(int row) {
     * Vector vrecord = (Vector) dataVector.get(row);
     * int index = columnIdentifiers.indexOf("Value");
     * return (Integer) vrecord.get(index);
     * }
     */
    /**
     * Returns a Vector of HashMaps of data
     */

    /*
     * public synchronized Vector dumpData(Vector columnNames) {
     * Vector results = new Vector();
     * int length = super.columnIdentifiers.size();
     * for (int i = 0; i < super.dataVector.size(); i++) {
     * Vector vrecord = (Vector) dataVector.get(i);
     * HashMap record = new HashMap();
     * for (int j = 0; j < length; j++) {
     * String field = (String) super.columnIdentifiers.get(j);
     * if (columnNames.contains(field)) {
     * record.put(field, vrecord.get(j));
     * }
     * }
     * results.add(record);
     * }
     * return results;
     * }
     * 
     * public synchronized Vector dumpData() {
     * return dumpData(super.columnIdentifiers);
     * }
     */
    /**
     * Returns a Vector of HashMaps of data
     */
    /*
     * public synchronized Vector dumpData(Vector columnNames, int[] rows) {
     * Vector results = new Vector();
     * int length = super.columnIdentifiers.size();
     * for (int i = 0; i < rows.length; i++) {
     * Vector vrecord = (Vector) dataVector.get(rows[i]);
     * HashMap record = new HashMap();
     * for (int j = 0; j < length; j++) {
     * String field = (String) super.columnIdentifiers.get(j);
     * if (columnNames.contains(field))
     * record.put(field, vrecord.get(j));
     * }
     * results.add(record);
     * }
     * return results;
     * }
     * 
     * public synchronized Vector dumpData(int[] rows) {
     * return dumpData(super.columnIdentifiers, rows);
     * }
     */

    /*
     * public synchronized int addEmptyRow() {
     * super.addRow(new Vector());
     * return super.getRowCount() - 1;
     * }
     * 
     * public synchronized void removeRows(int[] selected) {
     * for (int i = selected.length - 1; i >= 0; i--) {
     * super.removeRow(selected[i]);
     * }
     * }
     */
}