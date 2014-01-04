/**
 * 
 */
package br.com.sixinf.ferramentas.swing;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * @author maicon.pasqualeto
 *
 */
public class SwingHelper {
	
	public static void showWarningMessage(Component parentComponent, String mensagem){		
		JOptionPane.showMessageDialog(parentComponent, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showErrorMessage(Component parentComponent, String mensagem){		
		JOptionPane.showMessageDialog(parentComponent, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showErrorMessage(Component parentComponent, String mensagem, Throwable t){
		String errorMessage = t.toString();
		JOptionPane.showMessageDialog(parentComponent, mensagem + "\n" + errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public static int showQuestionMessage(Component parentComponent, String mensagem){
		return JOptionPane.showConfirmDialog(parentComponent, mensagem, "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int showQuestionMessage(Component parentComponent, String mensagem, int optionType){
		return JOptionPane.showConfirmDialog(parentComponent, mensagem, "Confirmação", optionType, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int showMessage(Component parentComponent, String mensagem){
		return JOptionPane.showConfirmDialog(parentComponent, mensagem, "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

}
