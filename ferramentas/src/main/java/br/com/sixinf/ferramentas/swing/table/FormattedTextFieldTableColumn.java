package br.com.sixinf.ferramentas.swing.table;

import java.awt.Component;
import java.text.Format;
import java.text.ParseException;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 * Classe para colocar o O FormattedTextField de MOEDA dentro da table
 * @author Chamber
 *
 */
public class FormattedTextFieldTableColumn<T> extends AbstractCellEditor implements TableCellRenderer,	TableCellEditor {
	JCustomTable<T> table;
	JFormattedTextField renderField;
	JFormattedTextField editField;
	Float numero;
	int editedColumn;
	
	public FormattedTextFieldTableColumn(JCustomTable<T> table, int column,Object formatador) {
		super();
		this.table = table;
		
		if(formatador instanceof NumberFormatter){
			NumberFormatter numberFormatter = (NumberFormatter) formatador;
			renderField = new JFormattedTextField(numberFormatter);
			editField = new JFormattedTextField(numberFormatter);
		}else if(formatador instanceof Format){
			Format format = (Format) formatador;
			renderField = new JFormattedTextField(format);
			editField = new JFormattedTextField(format);			
		}else if(formatador instanceof MaskFormatter){
			MaskFormatter maskFormatter = (MaskFormatter) formatador;
			renderField = new JFormattedTextField(maskFormatter);
			editField = new JFormattedTextField(maskFormatter);			
		}
				
		renderField.setHorizontalAlignment(JFormattedTextField.RIGHT);
		editField.setHorizontalAlignment(JFormattedTextField.RIGHT);
		renderField.setBorder(null);
		editField.setBorder(null);
		//Utilizado para Tables com Editing cells
		//editField.addFocusListener(new FocusAdapter() {
		//	public void focusLost(FocusEvent e) {
		//		fireEditingStopped();					
		//	}
		//});		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		if (hasFocus) {
			renderField.setForeground(table.getForeground());
			renderField.setBackground(table.getSelectionBackground());
		} else if (isSelected) {
			renderField.setForeground(table.getSelectionForeground());
			renderField.setBackground(table.getSelectionBackground());
		} else {
			renderField.setForeground(table.getForeground());
			//renderField.setBackground(UIManager
			//		.getColor("Button.background"));
		}

		renderField.setValue((Float)value);
		return renderField;
	}
	
	public Component getTableCellEditorComponent(JTable table,Object value, boolean isSelected, int row, int column) {
		numero = (value == null) ? new Float(9999999.9999) : (Float)value;
		editField.setValue(numero);
		renderField.setValue(numero);
		editedColumn = column;
		return editField;
	}

	
	public Object getCellEditorValue() {
		try {
			editField.commitEdit();
			numero = ((Number)editField.getValue()).floatValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numero;
	}
	
	/* Utilizado para tables com editing cells
	@Override
	public boolean stopCellEditing() {
		if((Float)getCellEditorValue() < 0)
			return false;
		renderField.setValue((Float)numero);
		return super.stopCellEditing();
	}}*/
}
	
