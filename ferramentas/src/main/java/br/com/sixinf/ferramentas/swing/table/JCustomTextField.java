/**
 * 
 */
package br.com.sixinf.ferramentas.swing.table;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Format;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

import br.com.sixinf.ferramentas.Utilitarios;

/**
 * @author Mariana
 *
 */
public class JCustomTextField extends JFormattedTextField {
	
	
	
	public JCustomTextField(AbstractFormatter formatter) {
		super(formatter);
		addComitaCampoNull();
	}

	public JCustomTextField(AbstractFormatterFactory factory,
			Object currentValue) {
		super(factory, currentValue);
		addComitaCampoNull();
	}

	public JCustomTextField(AbstractFormatterFactory factory) {
		super(factory);
		addComitaCampoNull();
	}

	public JCustomTextField(Format format) {
		super(format);
		addComitaCampoNull();
	}

	public JCustomTextField(Object value) {
		super(value);
		addComitaCampoNull();
	}

	private int limit = 0;
	
	public JCustomTextField() {
		addComitaCampoNull();
	}

	/**
	 * 
	 */
	public JCustomTextField(int tamanhoMaximo) {
		delimitaTamanhocampo(tamanhoMaximo);
		addComitaCampoNull();
	}
	
	public JCustomTextField(int tamanhoMaximo, Format format) {
		super(format);
		delimitaTamanhocampo(tamanhoMaximo);
		addComitaCampoNull();
	}
	
	private void delimitaTamanhocampo(int tamanhoMaximo){
		this.limit = tamanhoMaximo;	
		this.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){     
				if(getText().length() >= limit)
					e.consume();     
			}    
		});
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	private void addComitaCampoNull(){
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				JFormattedTextField txt = (JFormattedTextField) e.getSource();				
				if (getFormatter() instanceof NumberFormatter){
					if ("0.00".equals(txt.getText()))
						txt.setValue(null);
				} else {
					String str = Utilitarios.removeMascara(txt.getText());
					if (str.length() <= 0)
						txt.setValue(null);
				}
			}
		});
	}

}
