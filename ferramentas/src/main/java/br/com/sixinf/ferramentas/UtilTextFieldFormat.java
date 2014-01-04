package br.com.sixinf.ferramentas;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class UtilTextFieldFormat extends MaskFormatter {
	
	public static MaskFormatter cnpjFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("##.###.###/####-##");
		maskFormatter.setValidCharacters("0123456789");
		maskFormatter.setValueContainsLiteralCharacters(false);
		//maskFormatter.setPlaceholder("*");
		return maskFormatter;
	}
	
	public static MaskFormatter cpfFormatter(){		
		MaskFormatter maskFormatter = null;
		try {
			maskFormatter = new MaskFormatter("###.###.###-##");
			maskFormatter.setValidCharacters("0123456789");
			maskFormatter.setValueContainsLiteralCharacters(false);
			//maskFormatter.setPlaceholder("*");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maskFormatter;
	}
	
	public static MaskFormatter inscEstadualFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("##.##.####-#");
		maskFormatter.setValidCharacters("0123456789");
		maskFormatter.setValueContainsLiteralCharacters(false);
		//maskFormatter.setPlaceholder("*");
		return maskFormatter;
	}
	
	public static MaskFormatter cepFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("#####-###");
		maskFormatter.setValidCharacters("0123456789");
		maskFormatter.setValueContainsLiteralCharacters(false);
		//maskFormatter.setPlaceholder("*");
		return maskFormatter;
	}
	
	public static MaskFormatter placaFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("UUU-####");		
		return maskFormatter;
	}
	
	public static MaskFormatter horaFormatter() throws ParseException{		
		MaskFormatter maskFormatter = new MaskFormatter("##:##");
		return maskFormatter;
	}	
	
	public static MaskFormatter foneFormatter() {
		MaskFormatter maskFormatter = null;
		try {
			maskFormatter = new MaskFormatter("(##)####-####");
			maskFormatter.setValidCharacters("0123456789");
			maskFormatter.setValueContainsLiteralCharacters(true);
			maskFormatter.setCommitsOnValidEdit(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maskFormatter;
	}
	
	public static MaskFormatter currencyFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("#.###.###,##");
		maskFormatter.setValidCharacters("0123456789");
		maskFormatter.setValueContainsLiteralCharacters(false);
		//maskFormatter.setPlaceholder("*");
		return maskFormatter;
	}
	
	public static MaskFormatter numberSimpleFormatter() throws ParseException{
		MaskFormatter maskFormatter = new MaskFormatter("########");
		maskFormatter.setValidCharacters("0123456789");
		maskFormatter.setValueContainsLiteralCharacters(false);
		//maskFormatter.setPlaceholder("*");
		return maskFormatter;
	}
	
	public static MaskFormatter tamanhoColunasFormatter(int tamanho) throws ParseException{
		StringBuffer buffer = new StringBuffer(tamanho);
		for(int i=0; i<tamanho; i++)
			buffer.append("*");	
		String str = buffer.toString();
		MaskFormatter maskFormatter = new MaskFormatter(str);
		return maskFormatter;
	}
	
	/**
	 * formata��o para MOEDA REAL
	 * @return
	 */
	public static NumberFormatter numberFormatter() {
		DecimalFormat decimal = new DecimalFormat("#,###,##0.00");
		decimal.setGroupingUsed(false);
		NumberFormatter numFormatter = new NumberFormatter(decimal);
		decimal.setMaximumFractionDigits(2);
		numFormatter.setFormat(decimal);
 		numFormatter.setAllowsInvalid(false);
 		
		return numFormatter;
	}
	
	public static NumberFormatter numberIntegerFormatter() {
		NumberFormatter numFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		return numFormatter;
	}
	
	public static NumberFormatter numberFloatFormatter() {
		NumberFormatter numFormatter = new NumberFormatter(NumberFormat.getNumberInstance());		
		return numFormatter;
	}
	
	public static Format percentFormatter(){
		Format format = NumberFormat.getPercentInstance();
		return format;
	}	
}
