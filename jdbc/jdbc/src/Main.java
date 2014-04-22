package jdbc.src;


public class Main {
	

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		con.ejecutarQuery("insert into indicator values ('666','afeo','en un lugar de la mancha')");
		con.cerrarConexion();

	}

}
