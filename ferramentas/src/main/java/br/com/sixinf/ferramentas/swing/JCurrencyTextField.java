/**
 * 
 */
package br.com.sixinf.ferramentas.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class JCurrencyTextField extends JTextField {
	
	private static final Logger log = Logger.getLogger(JCurrencyTextField.class);
	private static final NumberFormat df = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
	
	static {
		df.setMinimumFractionDigits(2);
	}
	
	
	public JCurrencyTextField() {
		addKeyListener();
	}

	private void addKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char k = e.getKeyChar();
				if (!Character.isDigit(k) &&
						k != KeyEvent.VK_BACK_SPACE &&
						k != KeyEvent.VK_DELETE){					
					e.consume();
				} else {
					if (e.getModifiers() == 0) {
						int idx = 1;
						if (k == KeyEvent.VK_BACK_SPACE ||
								k == KeyEvent.VK_DELETE)
							idx = 2;
						
						JTextField txt = (JTextField) e.getSource();
						StringBuilder valor = new StringBuilder(txt.getText());
						// remove a v�rgula
						int i = valor.indexOf(",");
						if (i >= 0)
							valor.deleteCharAt(i);
						
						if (valor.length() > idx) {
							valor.insert(valor.length() - idx, ',');
							if (i > 0 && valor.charAt(0) == '0' && k != KeyEvent.VK_DELETE)
								valor.deleteCharAt(0);					
						} else {
							if (valor.toString().equals("00"))
								valor.delete(0, 2);
							else 
								if (valor.length() == (idx - 1)) 
								valor.insert(0, "0,0"); 
							else
								if (valor.length() == idx)
								valor.insert(0, "0,");
						}
						txt.setText(valor.toString());
					}
				}
				super.keyTyped(e);
			}
		});
	}
	
	public Float getValue(){		
		Float valor = null;
		try {
			if (getText() != null && getText().length() > 0)
			valor = df.parse(getText()).floatValue();
		} catch (ParseException e) {
			log.error("valor do campo inv�lido", e);
		}
		return valor;
	}
	
	public void setValue(Float valor){
		if (valor != null) {			
			String txt = df.format(valor);
			setText(txt);
		}
	}

}
