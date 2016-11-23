/**
 * 
 */
package br.com.sixinf.ferramentas;

/**
 * @author maicon
 *
 */
public class ConversorCoordenadas {
	
	
	/**
	 * Graus Minutos.minutos para Graus.decimal de graus
	 *  
	 * @return coordenada em graus decimais arrendodada para 6 casas decimais depois da vírgula
	 */			
	public static double grausMinutosParaGrausDecimais(double coordenada) {
		//.d = M.m / 60
		//Decimal Degrees = Degrees + .d
		
		int graus = (int) (coordenada / 100);		
		double min = coordenada - (graus * 100);
		return Utilitarios.round(graus + (min / 60), 6);
	}

}
