
public class Main {

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		con.ejecutarQuery("insert into indicator values ('000','alguno','en un lugar de la mancha')");
		con.cerrarConexion();

		
	
		conexion con = new conexion();
		con.connectToAccess();
		con.ejecutarQuery("Select * from indicator");
		con.cerrarConexion();
	}

}
