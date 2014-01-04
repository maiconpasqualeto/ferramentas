/*
 * Created on 12/08/2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.sixinf.ferramentas.swing.table;


import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;




/**
 * @author maicon
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTableModel<T> extends AbstractTableModel implements TableModelListener {
	
	private static final Logger log = Logger.getLogger(DataTableModel.class);
	
	protected Vector<T> dados;
	protected Vector<T> dadosAux;
	private String[] colunas;
	private String[] labels;
	private boolean updated;
	protected Method[] metodosGet;
	protected Method[] metodosSet;
		
	public DataTableModel(Collection<T> dadosCol, String[] propriedades, String[] labels, Class<T> classe) {
		try {
			if (propriedades.length < 2){
				throw new UnsupportedOperationException("A tabela deve possuir pelo menos 2 colunas");
			}
			if (propriedades.length != labels.length){
				throw new UnsupportedOperationException("Numero de colunas diferente do numero de labels");
			}
			this.colunas = propriedades;
			this.labels = labels;			
			this.dados = new Vector<T>(dadosCol);
			this.dadosAux = dados;
			//this.classe = classe;
			this.updated = false;
			int numCampos = colunas.length;			
			this.metodosGet = new Method[numCampos];
			this.metodosSet = new Method[numCampos];
			for (int i=0; i<numCampos; i++){
				Method metodoGet = classe.getMethod(getGetMethodName(i));				
				this.metodosGet[i] = metodoGet;
				this.metodosSet[i] = classe.getMethod(getSetMethodName(i), metodoGet.getReturnType());
			}
			addTableModelListener(this);
		} catch (SecurityException e) {
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (NoSuchMethodException e) {
			String mensagem = e.getMessage();
			mensagem = mensagem.substring(classe.getName().length() + 4);
			mensagem = Introspector.decapitalize(mensagem);
			mensagem = mensagem.substring(0, mensagem.length()-2);
			mensagem = "A propriedade : '" + mensagem + "' n�o foi encontrada na classe '" + classe.getName() + "'";
			log.error(mensagem, e);
		}
	}

	public int getColumnCount() {
		return colunas.length;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return metodosGet[columnIndex].getReturnType();
	}

	public String getColumnName(int column) {
		return labels[column];
	}
	
	public int getRowCount() {
		return dados.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object retorno = null;
		try {
			Object o = dados.elementAt(rowIndex);				
			Object dado = metodosGet[columnIndex].invoke(o, new Object[0]);
			retorno = dado;
		} catch (SecurityException e) {
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (InvocationTargetException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		}
		return retorno;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
		try {
			Object o = dados.elementAt(rowIndex);
			metodosSet[columnIndex].invoke(o, aValue);
		} catch (SecurityException e) {
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (InvocationTargetException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
		
	public void addRow(T o){
		dados.add(o);
		fireTableRowsInserted(dados.size(), dados.size());
	}
		
	public void updateRow(T o, int rowIndex){
		dados.setElementAt(o, rowIndex);
		fireTableRowsUpdated(dados.size(), dados.size());
	}
	
	public void removeRow(int rowIndex){
		if (dados.size() == 0)
			return;
		dados.removeElementAt(rowIndex);
		fireTableRowsDeleted(dados.size(), dados.size());
	}
		
	public List<T> getTableCollection(){
		 return dados;
	}
	
	public T getObject(int rowIndex){
		return dados.elementAt(rowIndex);
	}
		
	public void setTableCollection(Collection<T> collection){
		dados = new Vector<T>(collection);
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
	
	private String getGetMethodName(int columnIndex){
		StringBuffer nome = new StringBuffer(colunas[columnIndex]);
		nome.insert(0, "get");
		int ch = (int) nome.charAt(3);
		nome.setCharAt(3, (char) (ch - 32));
		return nome.toString();
	}

	private String getSetMethodName(int columnIndex){
		StringBuffer nome = new StringBuffer(colunas[columnIndex]);
		nome.insert(0, "set");
		int ch = (int) nome.charAt(3);
		nome.setCharAt(3, (char) (ch - 32));
		return nome.toString();
	}	
	
	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE){
			this.updated = true;
			log.debug("update");
		} else if (e.getType() == TableModelEvent.DELETE){
			log.debug("delete");
		} else if (e.getType() == TableModelEvent.INSERT){
			log.debug("insert");
		} else if (e.getType() == TableModelEvent.ALL_COLUMNS){
			log.debug("all columns");
		} else if (e.getType() == TableModelEvent.HEADER_ROW){
			log.debug("header row");
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void filtrar(Object parametro, int columnIndex){
		Vector vector = new Vector();		
		try {
			for (int i=0; i<dados.size(); i++){
				Object o = dados.elementAt(i);							
				Object dado = metodosGet[columnIndex].invoke(o, new Object[0]);
				if (dado instanceof String){
					String str = (String) dado;
					String par = (String) parametro;
					//CharSequence seq = par.subSequence(0, par.length());
					//if (str.contains(seq))
					if (str.regionMatches(true, 0, par, 0, par.length()))
						vector.add(o);
				} else if (dado instanceof Integer){
					Integer inte = (Integer) dado;
					try {	
						if (inte.equals(Integer.valueOf((String) parametro)))
							vector.add(o);
					} catch (NumberFormatException ex){ 
						// se ocorrer exception n�o filtra nada
					}
				} else {					
					Filter filter = (Filter) dado;
					if (filter.match(parametro))
						vector.add(o);
				}
			}
			dados = vector;
		} catch (SecurityException e){
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		} catch (InvocationTargetException e) {
			log.error(e);
		} catch (ClassCastException e) {
			throw new ClassCastException("o a classe a ser filtrada deve implementar a interface 'Filter'");
		}
		fireTableDataChanged();
	}
	
	
	@SuppressWarnings("unchecked")
	public void filtrarData(Date dataInicial, Date dataFinal, int columnIndex){
		Vector vector = new Vector();
		try {
			for (int i=0; i<dados.size(); i++){
				Object o = dados.elementAt(i);
				Object dado = metodosGet[columnIndex].invoke(o, new Object[0]);
				if (dado instanceof Date){
					Timestamp timestamp = (Timestamp) dado;
					Date data = new Date(timestamp.getTime());
					if ((data.compareTo(dataInicial) >= 0) && (data.compareTo(dataFinal) <= 0))
						vector.add(o);
				}
			}
			dados = vector;
		} catch (SecurityException e){
			log.error(e);
		} catch (IllegalArgumentException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		} catch (InvocationTargetException e) {
			log.error(e);
		}
		fireTableDataChanged();
	}
	
	public void removerFiltro(){
		dados = dadosAux;
		fireTableDataChanged();
	}
	
	/**
	 * 
	 *
	 */
	public void clearAll(){
		dadosAux = new Vector<T>();
		dados = dadosAux;
		fireTableDataChanged();
	}	
	
}
