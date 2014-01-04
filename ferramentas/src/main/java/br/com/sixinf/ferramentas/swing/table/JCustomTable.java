package br.com.sixinf.ferramentas.swing.table;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JCustomTable<T> extends JTable {
	
	private final boolean debug = true;

	public JCustomTable() {
		super();
		setTableProperties();
	}

	public JCustomTable(TableModel dm) {
		super(dm);
		setTableProperties();
	}

	public JCustomTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		setTableProperties();
	}

	public JCustomTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		setTableProperties();
	}

	public JCustomTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		setTableProperties();
	}

	public JCustomTable(Vector<T> rowData, Vector<String> columnNames) {
		super(rowData, columnNames);
		setTableProperties();
	}

	public JCustomTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		setTableProperties();
	}
	
	private void setTableProperties(){
		setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		getTableHeader().setReorderingAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		FormattedRenderer renderer = new FormattedRenderer();
		FormattedEditor editor = new FormattedEditor();
		setDefaultRenderer(Float.class, renderer);
		setDefaultRenderer(Date.class, renderer);
		setDefaultRenderer(Object.class, renderer);
		setDefaultRenderer(Integer.class, renderer);
		setDefaultEditor(Date.class, editor);
		setDefaultEditor(Float.class, editor);
	}
	
	public void setDataModel(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){				
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};
			dataModel.sortColumn(0);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});
			if (debug) {
				th.addMouseMotionListener(new MouseMotionAdapter(){
					@Override
					public void mouseMoved(MouseEvent e) {
						JTableHeader th = (JTableHeader) e.getSource();
						TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
						th.setToolTipText("tamanho:" + tc.getWidth());
					}
				});
			}
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDataModelComSorter(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe, int colunaSorter){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){				
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};
			dataModel.sortColumn(colunaSorter);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});
			if (debug) {
				th.addMouseMotionListener(new MouseMotionAdapter(){
					@Override
					public void mouseMoved(MouseEvent e) {
						JTableHeader th = (JTableHeader) e.getSource();
						TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
						th.setToolTipText("tamanho:" + tc.getWidth());
					}
				});
			}
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	public void setDataModelSegurancaAcao(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){		
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					if(columnIndex > 3){
						return true;
					}else{
						return false;
					}
				}
			};
			dataModel.sortColumn(0);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});					
			th.addMouseMotionListener(new MouseMotionAdapter(){
				@Override
				public void mouseMoved(MouseEvent e) {
					JTableHeader th = (JTableHeader) e.getSource();
					TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
					th.setToolTipText("tamanho:" + tc.getWidth());
				}
			});
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDataModelItemVenda(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){		
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					if(columnIndex == 0 || // codProduto
					   columnIndex == 1 || // botao busca produto
					   columnIndex == 3 || // Quantidade
					   columnIndex == 4 || // valor
					   columnIndex == 5 || // desconto moeda
					   columnIndex == 6){  // desconto porcentagem
						return true;
					}else{
						return false;
					}
				}
			};
			dataModel.sortColumn(0);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});					
			th.addMouseMotionListener(new MouseMotionAdapter(){
				@Override
				public void mouseMoved(MouseEvent e) {
					JTableHeader th = (JTableHeader) e.getSource();
					TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
					th.setToolTipText("tamanho:" + tc.getWidth());
				}
			});
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setDataModelPagamentoVista(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){		
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					if(columnIndex == 0 || // forma de pagamento
					   columnIndex == 1 ){  // valor pago					   
						return true;
					}else{
						return false;
					}
				}
			};
			dataModel.sortColumn(0);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});					
			th.addMouseMotionListener(new MouseMotionAdapter(){
				@Override
				public void mouseMoved(MouseEvent e) {
					JTableHeader th = (JTableHeader) e.getSource();
					TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
					th.setToolTipText("tamanho:" + tc.getWidth());
				}
			});
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDataModelBuscaProduto(int[] colSize, Collection<T> collection, String[] propriedades, String[] labels, Class<T> classe){
		try {
			final SorterDataTableModel<T> dataModel = new SorterDataTableModel<T>(collection, propriedades, labels, classe){		
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					 return false;					
				}
			};
			dataModel.sortColumn(0);
			setModel(dataModel);
			JTableHeader th = getTableHeader();
			th.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					int indiceColuna = getColumnModel().getColumnIndexAtX(e.getX());
					if (e.getButton() == 1) {
						dataModel.sortColumn(indiceColuna);
						updateUI();
					}				
				}
			});					
			th.addMouseMotionListener(new MouseMotionAdapter(){
				@Override
				public void mouseMoved(MouseEvent e) {
					JTableHeader th = (JTableHeader) e.getSource();
					TableColumn tc = getColumnModel().getColumn(getColumnModel().getColumnIndexAtX(e.getX()));
					th.setToolTipText("tamanho:" + tc.getWidth());
				}
			});
			if (colSize.length != propriedades.length){
				throw new Exception("Quantidade de medidas das colunas inv�lido.");
			}
			setColumnsWidth(colSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public void setColumnsWidth(int[] colSize){
		for (int i=0; i<colSize.length -1; i++){
			TableColumn column = getColumnModel().getColumn(i);
			int width = colSize[i];
			column.setPreferredWidth(width);
			column.setMinWidth(20);
			column.setMaxWidth(getPreferredScrollableViewportSize().width);
		}
	}
	
	public void columnResizer(){
		TableColumnModel columnModel = this.getColumnModel();		
		for (int col=0; col<this.getColumnCount(); col++) {
			int maxwidth = 0;            
			for (int row=0; row<this.getRowCount(); row++) {
				TableCellRenderer rend = this.getCellRenderer(row, col); 
				Object value = this.getValueAt (row, col); 
				Component comp = rend.getTableCellRendererComponent (this,value,false,false,row,col);
					maxwidth = Math.max (comp.getPreferredSize().width, maxwidth); 
			} // for row
			
			TableColumn column = columnModel.getColumn (col); 
			column.setPreferredWidth (maxwidth);
		} // for col 
	}
	
	@SuppressWarnings("unchecked")
	public DataTableModel<T> getDataModel(){
		return (DataTableModel<T>) getModel();
	}

	
}
