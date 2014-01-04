package br.com.sixinf.ferramentas.swing.table;

import java.awt.Component;
import java.text.ParseException;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import br.com.sixinf.ferramentas.UtilTextFieldFormat;

import com.toedter.calendar.JDateChooser;

/**
 * 
 */
public class FormattedEditor extends AbstractCellEditor implements TableCellEditor {

	JDateChooser dc = null;
	JFormattedTextField ftf = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		Component c = null;
		
		Class clazz = table.getColumnClass(column);
		
		if (clazz.getName().equals("java.util.Date")){
			dc = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
			dc.setDate((Date) value);
			c = dc;
		} else 
			if (clazz.getName().equals("java.lang.Float")) {				
				ftf = new JFormattedTextField(UtilTextFieldFormat.numberFormatter());
				ftf.setHorizontalAlignment(JFormattedTextField.TRAILING);
				ftf.setValue(value);
				c = ftf;
		}
		
		return c;		
	}

	@Override
	public Object getCellEditorValue() {
		Object value = null;
		if (dc != null){
			value = dc.getDate();
			dc = null;
		} else 
			if (ftf != null) {				
				try {
					ftf.commitEdit();
				} catch (ParseException e1) { }				
				if ("0.00".equals(ftf.getText())){
					ftf.setValue(null);
				}
				if (ftf.getValue() != null)
					value = ((Number) ftf.getValue()).floatValue();
				ftf = null;
			}
		return value;
	}
	
	

}
