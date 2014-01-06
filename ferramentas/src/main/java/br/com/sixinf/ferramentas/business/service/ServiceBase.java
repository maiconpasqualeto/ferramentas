/**
 * 
 */
package br.com.sixinf.ferramentas.business.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.dao.DAOException;
import br.com.sixinf.ferramentas.dao.GenericDAO;
import br.com.sixinf.ferramentas.facade.FacadeException;
import br.com.sixinf.ferramentas.persistencia.AdministradorPersistencia;
import br.com.sixinf.ferramentas.persistencia.Entidade;


/**
 * @author Maicon
 *
 */
public class ServiceBase {
	
	private static Logger logger = Logger.getLogger(ServiceBase.class);
	
	public <T extends Entidade> Long adicionar(T o) throws FacadeException{
		EntityManager em = AdministradorPersistencia.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();
			
			GenericDAO.salvar(o, em);
			
			tr.commit();
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException("Erro ao incluir objeto", e, logger);
		}
		
		return o.getIdentificacao();
	}
	
	public <T extends Entidade> Long alterar(T o) throws FacadeException{
		EntityManager em = AdministradorPersistencia.getEntityManager();
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin();
			
			GenericDAO.merge(o, em);
			
			tr.commit();
		} catch (DAOException e) {
			tr.rollback();
			throw new FacadeException("Erro ao incluir objeto", e, logger);
		}
		
		return o.getIdentificacao();
	}
	
	public <T extends Entidade> void excluir(T objeto, Class<T> classe)throws FacadeException {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		EntityTransaction tr = em.getTransaction();		
		try{
			
			T o = GenericDAO.buscar(objeto.getIdentificacao(), classe, em);
			tr.begin();
			
			GenericDAO.excluir(o, em);
			
			tr.commit();
		} catch(DAOException e){
			tr.rollback();
			throw new FacadeException("Erro ao remover objeto", e, logger);
		} 
	}
	
	public <T extends Entidade> T buscar(Serializable id, Class<T> classe) throws FacadeException{
		EntityManager em = AdministradorPersistencia.getEntityManager();
		T o = null;
		try{
			o = GenericDAO.buscar(id, classe, em);
		} catch(DAOException e){
			throw new FacadeException("Erro ao buscar objeto por id ", e, logger);
		}
		return o;
	}
	
	public <T extends Entidade> List<T> buscarTodos(Class<T> c) throws FacadeException{
		EntityManager em = AdministradorPersistencia.getEntityManager();
		List<T> lista = null;
		try {
			lista = GenericDAO.buscarTodos(c, em);		
		} catch (DAOException e) {
			throw new FacadeException("Erro ao incluir objeto", e, logger);
		}
		
		return lista;
	}
	
}
