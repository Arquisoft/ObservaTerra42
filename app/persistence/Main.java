
public class Main {

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		//indicator
		con.ejecutarQuery("insert into indicator values ('000','pobreza infantil','nivel de pobreza infantil que presenta dicha zona')");
		con.ejecutarQuery("insert into indicator values ('001','violencia de genero','indice que violencia de genero que presenta en la zona')");
		con.ejecutarQuery("insert into indicator values ('002','analfabetismo','Indice de personas analfabetas de la zona')");
		//area
		con.ejecutarQuery("insert into area values ('000','españa','pais españa perteneciente a la union europea','pais')");
		con.ejecutarQuery("insert into area values ('001','UE','Union Europea, es una comunidad política de Derecho constituida en régimen de organización internacional','zona')");
		con.ejecutarQuery("insert into area values ('002','Francia','pais francia perteneciente a la union europea','pais')");
		//users
		con.ejecutarQuery("insert into users values ('000','polan','000','polan@polan.es','colaborador','true','78945612f','polan estudiante','654120255','alguna 0','a','uniovi','conductor','2014-02-05')");
		con.ejecutarQuery("insert into users values ('001','diego','001','diego@diego.es','usuario','true','7145875f','diego','654120255','alguna 1','b','uniovi','consultor','2014-02-05')");
		con.ejecutarQuery("insert into users values ('002','master','002','master@master.es','colaborador','true','7245896f','master tpa','258741025','alguna 2','c','uniovi','director','2014-02-05')");
		//observation
		con.ejecutarQuery("insert into observation values ('001','001','001','kg','200','365')");
		con.ejecutarQuery("insert into observation values ('002','002','002','ml','100','30')");
		con.cerrarConexion();

	}

}
