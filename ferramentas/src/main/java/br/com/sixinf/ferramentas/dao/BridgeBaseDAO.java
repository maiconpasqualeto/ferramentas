/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixinf.ferramentas.dao;


import java.io.Serializable;
import java.util.List;

import br.com.sixinf.ferramentas.persistencia.Entidade;

/**
 * Pattern Bridge
 * @author maicon.pasqualeto
 */
public abstract class BridgeBaseDAO implements IBaseDAO {

    private IBaseDAO baseDAO;

    public BridgeBaseDAO(IBaseDAO baseDAO){
        this.baseDAO = baseDAO;
    }

    /**
     * @return the hibernateBaseDAO
     */
    public IBaseDAO getBaseDAO() {
        return baseDAO;
    }

    public <T extends Entidade> Long adicionar(T o) throws DAOException {
        return getBaseDAO().adicionar(o);
    }

    public <T extends Entidade> Long alterar(T o) throws DAOException {
        return getBaseDAO().alterar(o);
    }

    public <T extends Entidade> void excluir(T objeto, Class<T> classe) throws DAOException {
        getBaseDAO().excluir(objeto, classe);
    }

    public <T extends Entidade> T buscar(Serializable id, Class<T> classe) throws DAOException {
        return getBaseDAO().buscar(id, classe);
    }

    public <T extends Entidade> List<T> buscarTodos(Class<T> c) throws DAOException {
        return getBaseDAO().buscarTodos(c);
    }

}
