package br.com.sixinf.ferramentas.facade;
/**
 * 
 */

import java.io.Serializable;
import java.util.Collection;


/**
 * @author maicon
 *
 */
public interface FacadeEjbInterface<T> {
	
	public T incluir(T objeto) throws FacadeException;
	
	public void alterar(T objeto) throws FacadeException;
		
	public void excluir(Serializable id, Class<T> classe) throws FacadeException;
	
	public T buscar(Serializable pk, Class<T> classe) throws FacadeException;
		
	public Collection<T> buscarTodos(Class<T> classe) throws FacadeException;
	
	public Collection<T> buscarTodosOrdenados(String atributo) throws FacadeException;
	
	/**
	 * Busca quantidade total de registros de uma determinada consulta
	 * 
	 * @return
	 * @throws FacadeException 
	 */
	public int buscaTotalRegistros() throws FacadeException;
	
	/**
	 * 
	 * @param posicao
	 * @return
	 * @throws FacadeException 
	 */
	public T buscarPorPosicao(Integer posicao) throws FacadeException;	
	
	/**
	 * M�todo que faz busca paginada para preencher tela de busca
	 * 
	 * @param inicio
	 * @param quantidade
	 * @param propriedade
	 * @param expressao
	 * @return
	 * @throws FacadeException 
	 */
	public Collection<T> buscarTodosPaginado(int inicio, int quantidade, String propriedade, Object expressao) throws FacadeException;
	
}
