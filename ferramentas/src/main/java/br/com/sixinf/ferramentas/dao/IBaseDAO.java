/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixinf.ferramentas.dao;


import java.io.Serializable;
import java.util.List;

import br.com.sixinf.ferramentas.persistencia.Entidade;

/**
 *
 * @author maicon.pasqualeto
 */
public interface IBaseDAO {

        public <T extends Entidade> Long adicionar(T o) throws DAOException;

        public <T extends Entidade> Long alterar(T o) throws DAOException;

        public <T extends Entidade> void excluir(T objeto, Class<T> classe)throws DAOException;

        public <T extends Entidade> T buscar(Serializable id, Class<T> classe) throws DAOException;

        public <T extends Entidade> List<T> buscarTodos(Class<T> c) throws DAOException;
}
