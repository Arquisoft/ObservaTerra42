package models;

/**
 * Clase que representa cualquier archivo que se suba a la web
 * @author spolan
 *
 */
public class Data {

	/**
	 * Representa el nombre del archivo
	 */
	public String nombre;
	
	/**
	 * Representa la direccion del archivo
	 */
	public String direccion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
}
