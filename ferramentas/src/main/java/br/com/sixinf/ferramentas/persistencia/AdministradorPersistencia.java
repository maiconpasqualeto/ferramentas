/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixinf.ferramentas.persistencia;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 *
 * @author maicon.pasqualeto
 */

public class AdministradorPersistencia {

    private static final Logger logger = Logger.getLogger(AdministradorPersistencia.class);

	protected static EntityManagerFactory emf;

	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

	public static void close(){
            if (emf != null && emf.isOpen())
                    emf.close();
	}

    /**
     * Inicia conexão com o banco de dados e cria o EntityManager
     * Para passar os parâmtros customizados, antes de chamar esse método, chame o método 'createEntityManagerFactory'
     * 
     * @throws PersistenciaException
     */
    public static void iniciaUnidadeDePersistencia(String unidadePersistencia) throws PersistenciaException {
        try {
            if (emf == null)
                createEntityManagerFactory(unidadePersistencia);

            emf.createEntityManager();
        } catch (Exception ex){
            throw new PersistenciaException(ex, logger);
        }
    }
    
    /**
     * Cria Factory passando como parâmetro o nome da dataBaseUnit
     * @param dataBaseUnit
     */
    public static void createEntityManagerFactory(String dataBaseUnit) throws PersistenciaException {
        emf = Persistence.createEntityManagerFactory(dataBaseUnit);
    }

    /**
     * Cria Factory passando todos os parâmetros de conexão com banco
     * 
     * @param dbUnit
     * @param dataBaseIp
     * @param dataBasePort
     * @param dataBasePath
     */
    public static void createEntityManagerFactory(
            String dbUnit, String dataBaseIp, 
            String dataBasePort, String dataBasePath, 
            String dataBaseUser, String dataBasePass) throws PersistenciaException{

        try {
            Map<String, String> configOverrides = new HashMap<String, String>();

            configOverrides.put("hibernate.connection.url",
                    "jdbc:postgresql://" + dataBaseIp + ":" + dataBasePort + "/" + dataBasePath);
            configOverrides.put("hibernate.connection.username", dataBaseUser);
            configOverrides.put("hibernate.connection.password", dataBasePass);

            emf = Persistence.createEntityManagerFactory(dbUnit, configOverrides);
            
        } catch (Exception ex){
            throw new PersistenciaException(ex, logger);
        }
    }
    
    /**
     * Inicia conexão com o banco de dados e cria o EntityManager
     * Para passar os parâmtros customizados, antes de chamar esse método, chame o método 'createEntityManagerFactory'
     * 
     * @throws PersistenciaException
     */
    public static void iniciaUnidadeDePersistencia(String dbUnit, String dataBaseIp, 
    		String dataBasePort, String dataBasePath, String dataBaseUser, String dataBasePass) throws PersistenciaException {
        try {
            if (emf == null)
                createEntityManagerFactory(dbUnit, dataBaseIp, dataBasePort, dataBasePath, dataBaseUser, dataBasePass);

            emf.createEntityManager();
        } catch (Exception ex){
            throw new PersistenciaException(ex, logger);
        }
    }
}

