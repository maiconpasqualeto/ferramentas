package br.com.sixinf.ferramentas.swing.table;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DefaultCustomTableModel<T> extends DefaultTableModel{
	
	private boolean camposEditaveis = false; 
	private boolean updated = false;
	
	public DefaultCustomTableModel() {
		super();
	}

	public DefaultCustomTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public DefaultCustomTableModel(Vector<T> columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public DefaultCustomTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public DefaultCustomTableModel(Vector<T> data, Vector<T> columnNames) {
		super(data, columnNames);
	}

	public DefaultCustomTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return this.camposEditaveis;
	}

	/**
	 * @return Returns the camposEditaveis.
	 */
	public boolean isCamposEditaveis() {
		return camposEditaveis;
	}

	/**
	 * @param camposEditaveis The camposEditaveis to set.
	 */
	public void setCamposEditaveis(boolean camposEditaveis) {
		this.camposEditaveis = camposEditaveis;
	}

	/**
	 * @return Returns the updated.
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * @param updated The updated to set.
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
}
