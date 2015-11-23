/**
 * 
 */
package br.com.sixinf.ferramentas.persistencia;

import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

/**
 * @author maicon
 *
 */
public class AdministradorPersistenciaSql2o {
	
	private static Sql2o sql2o;
	
	public static Sql2o getsql2o() {
		if (sql2o == null)
			throw new Sql2oException("Connection parameters not set");
		return sql2o;
	}
	
	public static void inicializaPersistencia(
			String dataName,
            String dataBaseIp, String dataBasePort,  
            String dataBaseUser, String dataBasePass) {
		
		sql2o = new Sql2o("jdbc:postgresql://" + dataBaseIp + ":" + dataBasePort + "/" + dataName,
	            dataBaseUser, dataBasePass);		
	}
	
	
	
	

}
