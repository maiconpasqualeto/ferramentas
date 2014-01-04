package br.com.sixinf.ferramentas.swing.table;

import java.awt.Color;
import java.awt.Component;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormattedRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		NumberFormat numero = new DecimalFormat("0.00");
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat time = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT);
		if (value instanceof Time){
			setText(time.format((Time) value));
			setHorizontalAlignment(CENTER);
		} else if (value instanceof Date){
			setText(data.format((Date) value));
			setHorizontalAlignment(CENTER);
		} else if (value instanceof Float){
			setText(numero.format((Float) value));			
			setHorizontalAlignment(RIGHT);
		} else if (value instanceof String){
			setHorizontalAlignment(LEFT);
		} else if (value instanceof Integer){
			setHorizontalAlignment(CENTER);
		} else {
			setHorizontalAlignment(LEFT);
		}
        if (!isSelected)
        {
            setBackground(row % 2 == 0 ? null : new Color(239, 245, 253));
        }
		return this;
	}

	
}