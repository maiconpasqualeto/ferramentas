/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixinf.ferramentas.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.persistencia.AdministradorPersistencia;

/**
 *
 * @author maicon.pasqualeto
 */
public class HibernateBaseDAOImp implements IBaseDAO {

    private static Logger logger = Logger.getLogger(HibernateBaseDAOImp.class);

    @Override
    public <T> T adicionar(T o) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();

            try {
                tr.begin();

                GenericDAO.salvar(o, em);

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
