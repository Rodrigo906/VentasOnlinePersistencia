package ar.unrn.tp.ui;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MyTableModel extends DefaultTableModel{

private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	Class[] columnTypes;
	
	public MyTableModel(final Object[][] datos, final String[] titulos) {
		super(datos,titulos);
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return super.getValueAt(row, column);
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
	}
	
	public void setColumnTypes(@SuppressWarnings("rawtypes") Class[] columnTypes) {
		this.columnTypes = columnTypes;
	}
	
	
	
	
}
