package utils;

import java.util.List;

public class Functions {

	/**
	 * Suma de una serie de datos
	 * @param datos
	 * @return
	 */
	public Double add(List<Double> datos)
	{
		double resultado = 0;
		for(Double d : datos){
			resultado += d;
		}
		return resultado;		
	}
	
	/**
	 * Media aritmetica de una serie de datos
	 * @param datos
	 * @return
	 */
	public Double mean(List<Double> datos)
	{
		double resultado = add(datos);
		return resultado/datos.size();		
	}
	
	/**
	 * Devuelve el maximo valor de los datos
	 * @param datos
	 * @return
	 */
	public Double max(List<Double> datos)
	{
		double resultado = 0;
		for(Double d : datos){
			if(d > resultado)
				resultado = d;
		}
		return resultado;
	}
	
	/**
	 * Devuelve el min valor de los datos
	 * @param datos
	 * @return
	 */
	public Double min(List<Double> datos)
	{
		double resultado = 0;
		int i = 0;
		for(Double d : datos){
			if(i == 0)
				resultado = d;
			else if(d < resultado)
				resultado = d;
		}
		return resultado;
	}
	
}
