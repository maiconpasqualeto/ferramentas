/**
 * 
 */
package br.com.sixinf.ferramentas.dao;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import br.com.sixinf.ferramentas.Utilitarios;
import br.com.sixinf.ferramentas.persistencia.AdministradorPersistenciaSql2o;

/**
 * @author maicon
 *
 */
public class Sql2oBaseDAOImpl implements IBaseDAO {

    private static Logger logger = Logger.getLogger(Sql2oBaseDAOImpl.class);

    @Override
    public <T> T adicionar(T o) throws DAOException{
            Sql2o sql2o = AdministradorPersistenciaSql2o.getsql2o();            
            Connection c = sql2o.beginTransaction();
            
            try {
            	String tableName = o.getClass().getCanonicalName();
            	tableName = Utilitarios.splitStringByCapitalLeter(tableName);
            	            	
            	StringBuilder str = new StringBuilder("insert into " + tableName + " (");
            	PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();
            	appendClassPropertiesList(str, pds);
            	str.append(") values (");
            	appendClassValueList(str, pds); 
            	str.append(")");
            	
                c.createQuery(str.toString()).bind(o).executeUpdate();
                
                c.commit();
                
            } catch (Exception e) {
            	c.rollback();
                throw new DAOException("Erro ao incluir objeto", e, logger);
			} finally {
                c.close();
            }

            return o;
    }

    @Override
    public <T> void alterar(T o) throws DAOException{
    	Sql2o sql2o = AdministradorPersistenciaSql2o.getsql2o();            
        Connection c = sql2o.beginTransaction();
        
        try {
        	String tableName = o.getClass().getCanonicalName();
        	tableName = Utilitarios.splitStringByCapitalLeter(tableName);
        	
        	StringBuilder str = new StringBuilder("update " + tableName + " set ");
        	PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();
        	int i = 1; // não conta a propriedade 'class'
        	for (PropertyDescriptor pd : pds) {
        		if (pd.getName().equals("class"))
        			continue;
        		str.append("set " + pd.getName() + " = :" + pd.getName());
        		if (++i < pds.length)
        			str.append(", ");
        	}
        	
            c.createQuery(str.toString()).bind(o).executeUpdate();
            
            c.commit();
            
        } catch (Exception e) {
        	c.rollback();
            throw new DAOException("Erro ao alterar objeto", e, logger);
		} finally {
            c.close();
        }

    }

    @Override
    public <T> void excluir(Serializable id, Class<T> classe)throws DAOException {
    	Sql2o sql2o = AdministradorPersistenciaSql2o.getsql2o();            
        Connection c = sql2o.beginTransaction();
        
        try {
        	String tableName = classe.getCanonicalName();
        	tableName = Utilitarios.splitStringByCapitalLeter(tableName);
        	
        	StringBuilder str = new StringBuilder("delete from " + tableName + " where ");
        	str.append("id = :id");
        	c.createQuery(str.toString()).addParameter("id", id).executeUpdate();
        	
        	c.commit();
        } catch(Exception e){
            c.rollback();
            throw new DAOException("Erro ao excluir objeto", e, logger);
        } finally {
            c.close();
        }
    }
    
    @Override
	public <T> void excluir(T o) throws DAOException {
		throw new DAOException("Método não implementado para slq2o", logger);
	}
    
    @Override
    public <T> T buscar(Serializable id, Class<T> classe) throws DAOException{
    	Sql2o sql2o = AdministradorPersistenciaSql2o.getsql2o();
    	Connection c = sql2o.open();
        T o = null;
        try{
            o = c.createQuery("select * from " + 
            		Utilitarios.splitStringByCapitalLeter(classe.getCanonicalName()) + 
            		" where id=:id")
            		.addParameter("id", id)
            		.executeAndFetchFirst(classe);
        } catch(Exception e){
            throw new DAOException("Erro ao buscar objeto por id ", e, logger);
        } finally {
            c.close();
        }
        return o;
    }

    @Override
    public <T> List<T> buscarTodos(Class<T> classe) throws DAOException{
    	Sql2o sql2o = AdministradorPersistenciaSql2o.getsql2o();
    	Connection c = sql2o.open();
        List<T> lista = null;
        try {
        	c.createQuery("select * from " + Utilitarios.splitStringByCapitalLeter(classe.getCanonicalName()))
    		.executeAndFetch(classe);
        	
        } catch (Exception e) {
            throw new DAOException("Erro ao buscar todos objetos", e, logger);
        } finally {
            c.close();
        }

        return lista;
    }
    
    /**
	 * 
	 * @param str
	 * @param pds
	 */
	private void appendClassPropertiesList(StringBuilder str, PropertyDescriptor[] pds) {
		int i = 1; // não conta a propriedade 'class'
    	for (PropertyDescriptor pd : pds) {
    		if (pd.getName().equals("class"))
    			continue;
    		str.append(pd.getName());
    		if (++i < pds.length)
    			str.append(", ");
    	}
	}
	
	/**
	 * 
	 * @param str
	 * @param pds
	 */
	private void appendClassValueList(StringBuilder str, PropertyDescriptor[] pds) {
		int i = 1; // não conta a propriedade 'class'
    	for (PropertyDescriptor pd : pds) {
    		if (pd.getName().equals("class"))
    			continue;
    		str.append(":" + pd.getName());
    		if (++i < pds.length)
    			str.append(", ");
    	}
	}
	
}
