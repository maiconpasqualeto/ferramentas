/**
 * 
 */
package br.com.sixinf.ferramentas.jaas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.dao.BridgeBaseDAO;
import br.com.sixinf.ferramentas.dao.HibernateBaseDAOImp;
import br.com.sixinf.ferramentas.persistencia.AdministradorPersistencia;
import br.com.sixinf.ferramentas.seguranca.persistencia.UsuarioSeguranca;

/**
 * @author maicon
 *
 */
public class SegurancaDAO extends BridgeBaseDAO {
	
	private static final Logger LOG = Logger.getLogger(SegurancaDAO.class);
	
	private static SegurancaDAO dao;
	
	public static SegurancaDAO getInstance(){
		if (dao == null)
			dao = new SegurancaDAO();
		return dao;
	}

	public SegurancaDAO() {
		super(new HibernateBaseDAOImp());
	}
	
	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public UsuarioSeguranca buscarUsuarioPorCpf(String nomeUsuario) {
		EntityManager em = AdministradorPersistencia.getEntityManager();
		
		UsuarioSeguranca obj = null;
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("select u from UsuarioSeguranca u ");
			hql.append("where u.nomeUsuario = :nomeUsuario ");
			TypedQuery<UsuarioSeguranca> q = em.createQuery(hql.toString(), UsuarioSeguranca.class);
			q.setParameter("nomeUsuario", nomeUsuario);
			
			obj = q.getSingleResult();
			
		} catch (NoResultException e) { 
		
		} catch (Exception e) {
			LOG.error("Erro ao buscar usuarioSeguranca por nomeUsuario", e);
		} finally {
            em.close();
        }
		return obj;
	}

}
