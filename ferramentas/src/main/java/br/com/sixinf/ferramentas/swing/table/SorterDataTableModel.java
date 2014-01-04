/**
 * 
 */
package br.com.sixinf.ferramentas.swing.table;

import java.util.Collection;
import java.util.Collections;

/**
 * @author maicon
 */
public class SorterDataTableModel<T> extends DataTableModel<T> {
	
	private boolean tipoOrdenacao = true; // true ascendente - false descendente
	private int coluna = 0;
	
	/**
	 * @param dadosCol
	 * @param propriedades
	 * @param labels
	 * @param classe
	 * @throws CampoNaoEncontradoException
	 */
	public SorterDataTableModel(Collection<T> dadosCol, String[] propriedades,
			String[] labels, Class<T> classe) {
		
		super(dadosCol, propriedades, labels, classe);
		
	}
	
	/*
	private int comparaLinhas(int linha1, int linha2, int coluna){		
		
		Object o1 = getValueAt(linha1, coluna);
		Object o2 = getValueAt(linha2, coluna);
		
        if (o1 == null && o2 == null) {
            return 0; 
        }
        else if (o1 == null) { 
            return 1; 
        } 
        else if (o2 == null) { 
            return -1; 
        }

        Class tipo1 = o1.getClass();
		
		if (tipo1.getSuperclass() == Number.class) {
			Number n1 = (Number) o1;
			double d1 = n1.doubleValue();
			Number n2 = (Number) o2;
			double d2 = n2.doubleValue();
						
			if (d2 < d1)
				return -1;
			else if (d2 > d1)
				return 1;
			else
				return 0;
		} else if (tipo1 == String.class){
			String s1 = (String) o1;
			String s2 = (String) o2;
			return s2.compareTo(s1);
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	private void trocaLinhas(int l1, int l2){
		Object o = dados.elementAt(l1);
		dados.setElementAt(dados.elementAt(l2), l1);
		dados.setElementAt(o, l2);
	}
	
	
	public void sortColumn(int coluna){
		for (int i=0; i<dados.size(); i++){
			for (int j=(i+1); j<dados.size(); j++){
				if (comparaLinhas(i, j, coluna) < 0) {
						trocaLinhas(i, j);
				}
			}
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public void sortColumn(int coluna){
		if (coluna != this.coluna) {
			this.tipoOrdenacao = true;
			this.coluna = coluna;
		}
		Collections.sort(dados, new Comparador<T>(dados, metodosGet[coluna], tipoOrdenacao));
		this.tipoOrdenacao = !tipoOrdenacao;
	}
	
}
