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
import br.com.sixinf.ferramentas.persistencia.Entidade;

/**
 *
 * @author maicon.pasqualeto
 */
public class HibernateBaseDAOImp implements IBaseDAO {

    private static Logger logger = Logger.getLogger(HibernateBaseDAOImp.class);

    public <T extends Entidade> Long adicionar(T o) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();

            try {
                    tr.begin();

                    new GenericDAO<T>().salvar(o, em);

                    tr.commit();
            } catch (DAOException e) {
                    tr.rollback();
                    throw new DAOException("Erro ao incluir objeto", e, logger);
            } finally {
                em.close();
            }

            return o.getIdentificacao();
    }

    public <T extends Entidade> Long alterar(T o) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();

            try {
                    tr.begin();

                    new GenericDAO<T>().merge(o, em);

                    tr.commit();
            } catch (DAOException e) {
                    tr.rollback();
                    throw new DAOException("Erro ao alterar objeto", e, logger);
            } finally {
                em.close();
            }

            return o.getIdentificacao();
    }

    public <T extends Entidade> void excluir(T objeto, Class<T> classe)throws DAOException {
            EntityManager em = AdministradorPersistencia.getEntityManager();
            EntityTransaction tr = em.getTransaction();
            try{
                    GenericDAO<T> dao = new GenericDAO<T>();
                    T o = dao.buscar(objeto.getIdentificacao(), classe, em);
                    tr.begin();

                    dao.excluir(o, em);

                    tr.commit();
            } catch(DAOException e){
                    tr.rollback();
                    throw new DAOException("Erro ao remover objeto", e, logger);
            } finally {
                em.close();
            }
    }

    public <T extends Entidade> T buscar(Serializable id, Class<T> classe) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            T o = null;
            try{
                    o = new GenericDAO<T>().buscar(id, classe, em);
            } catch(DAOException e){
                    throw new DAOException("Erro ao buscar objeto por id ", e, logger);
            } finally {
                em.close();
            }
            return o;
    }

    public <T extends Entidade> List<T> buscarTodos(Class<T> c) throws DAOException{
            EntityManager em = AdministradorPersistencia.getEntityManager();
            List<T> lista = null;
            try {
                    lista = new GenericDAO<T>().buscarTodos(c, em);
            } catch (DAOException e) {
                    throw new DAOException("Erro ao buscar todos objetos", e, logger);
            } finally {
                em.close();
            }

            return lista;
    }

}
