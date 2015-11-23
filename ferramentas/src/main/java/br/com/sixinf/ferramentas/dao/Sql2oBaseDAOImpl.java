/**
 * 
 */
package br.com.sixinf.ferramentas.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import br.com.sixinf.ferramentas.persistencia.AdministradorPersistencia;
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

            try {
            	Connection c = sql2o.beginTransaction();

                c.createQuery(queryText)

                tr.commit();
            } catch (DAOException e) {
                tr.rollback();
                throw new DAOException("Erro ao incluir objeto", e, logger);
            } finally {
                em.close();
            }

            return o;
    }

    @Override
    public <T> void alterar(T o) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();

            try {
                tr.begin();

                GenericDAO.merge(o, em);

                tr.commit();
            } catch (DAOException e) {
                tr.rollback();
                throw new DAOException("Erro ao alterar objeto", e, logger);
            } finally {
                em.close();
            }
    }

    @Override
    public <T> void excluir(Serializable id, Class<T> classe)throws DAOException {
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();
            try{
                tr.begin();
                
                T o = GenericDAO.buscar(id, classe, em);

                GenericDAO.excluir(o, em);

                tr.commit();
            } catch(DAOException e){
                tr.rollback();
                throw new DAOException("Erro ao remover objeto", e, logger);
            } finally {
                em.close();
            }
    }
    
    @Override
    public <T> void excluir(T o)throws DAOException {
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();
            try{
                tr.begin();

                GenericDAO.excluir(o, em);

                tr.commit();
            } catch(DAOException e){
                tr.rollback();
                throw new DAOException("Erro ao remover objeto", e, logger);
            } finally {
                em.close();
            }
    }

    @Override
    public <T> T buscar(Serializable id, Class<T> classe) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            T o = null;
            try{
                o = GenericDAO.buscar(id, classe, em);
            } catch(DAOException e){
                throw new DAOException("Erro ao buscar objeto por id ", e, logger);
            } finally {
                em.close();
            }
            return o;
    }

    @Override
    public <T> List<T> buscarTodos(Class<T> c) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            List<T> lista = null;
            try {
                lista = GenericDAO.buscarTodos(c, em);
            } catch (DAOException e) {
                throw new DAOException("Erro ao buscar todos objetos", e, logger);
            } finally {
                em.close();
            }

            return lista;
    }

}
