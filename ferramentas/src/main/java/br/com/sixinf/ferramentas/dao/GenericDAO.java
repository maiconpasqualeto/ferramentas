/**
 * 
 */
package br.com.sixinf.ferramentas.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;


/**
 * @author maicon
 *
 */
public class GenericDAO {

	static Logger logger = Logger.getLogger(GenericDAO.class);
		
		
	public static <T> void salvar(T objeto, EntityManager em)throws DAOException{
		try{
			em.persist(objeto);
		} catch(Exception e){
			throw new DAOException("Erro ao persistir objeto", e, logger);
		} 
	}
	
	public static <T> T merge(T objeto, EntityManager em)throws DAOException{
		T o = null;
		try{
			o = em.merge(objeto);
		} catch(Exception e){
			throw new DAOException("Erro ao fazer merge no objeto", e, logger);
		} 
		
		return o;
	}
	
	public static <T> T atualizar(T objeto, EntityManager em)throws DAOException{
		T o;
		try{
			o = em.merge(objeto);
		} catch(Exception e){
			throw new DAOException("Erro ao fazer merge no objeto", e, logger);
		} 
		return o;
	}
	
	public static <T> void excluir(T objeto, EntityManager em)throws DAOException{
		try{			
			em.remove(objeto);
		} catch(Exception e){
			throw new DAOException("Erro ao remover objeto", e, logger);
		} 
	}
	
	public static <T> void excluir(Serializable id, Class<T> classe, EntityManager em)throws DAOException{
		try{
			em.remove(em.find(classe, id));
		} catch(Exception e){
			throw new DAOException("Erro ao remover objeto", e, logger);
		} 
	}	
	
	public static <T> T buscar(Serializable id, Class<T> classe, EntityManager em) throws DAOException{
		T o = null;
		try{
			o = em.find(classe, id);
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por id ", e, logger);
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> buscarTodos(Class<T> classe, EntityManager em) throws DAOException{
		Query query = null;
		List<T> lista = null;
		try{
			query = em.createQuery("from " + classe.getCanonicalName());
			lista = query.getResultList();
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por id ", e, logger);
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> buscarTodosOrdenados(Class<T> classe, String propriedade, EntityManager em)throws DAOException{
		Query query = null;
		List<T> lista = null;
		try{
			query = em.createQuery("from " + classe.getCanonicalName() + " order by " + propriedade);
			lista = query.getResultList();
		} catch(Exception e){
			throw new DAOException("Erro ao buscar objeto por id ", e, logger);
		}
		return lista;
	}
}
