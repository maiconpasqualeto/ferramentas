/**
 * 
 */
package br.com.sixinf.ferramentas.swing.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * @author mpasqualeto
 *
 */
public class CustomTableCellEditor extends DefaultCellEditor implements TableCellEditor{


	public CustomTableCellEditor(JCheckBox checkBox) {
		super(checkBox);
		// TODO Auto-generated constructor stub
	}

	public CustomTableCellEditor(JComboBox comboBox) {
		super(comboBox);
		// TODO Auto-generated constructor stub
	}

	public CustomTableCellEditor(JTextField textField) {
		super(textField);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		Component c = super.getTableCellEditorComponent(
				table, 
				value, 
				isSelected, 
				row, 
				column);
		return c;
	}

	@Override
	public Object getCellEditorValue() {
		
		return super.getCellEditorValue();
	}

}
