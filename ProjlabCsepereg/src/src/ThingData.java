package src;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * <b>ThingData class</b><br>
 * <i>Inherits from AbstractTableModel.</i><br><br>
 * 
 * @author Martin
 */
public class ThingData<T> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<? super T> thingList;
	
	/**
	 * constructor method, it copies the arrayList given as parameter to the thingList
	 * @param _t the array list we want to display with thingdata
	 */
	public ThingData(ArrayList<? super T> _t){
		thingList = _t;
	}
	
	/**
	 * returns the name of the selected column
	 * @param col the number of the selected column
	 */
	public String getColumnName(int col) {
		switch(col) {
		case 0: return "Data";
		default: return "Empty";
		}
	}
	
	public int getRowCount() {
		return thingList.size();
	}
	
	public int getColumnCount() {
		return 1;
	}
	
	public Class<? extends Object> getColumnClass(int column){
		return getValueAt(0, column).getClass();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		return thingList.get(rowIndex);
	}
	
	public void setValueAt(Object value, int row, int col) {}
}
