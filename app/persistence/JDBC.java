
public class Main {

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		//business
		con.ejecutarQuery("insert into business values ('001','Juan Garcia','123','juan@juan.com','business','true','9365778M','business','684596320','catalans campus','a')");
		con.ejecutarQuery("insert into business values ('002','Ana Rosa','123','anarosa@hotmail.com','business','true','47896527k','business','789654123','Ramiro I','' )");
		//collaborator
		con.ejecutarQuery("insert into collaborator values ('001','Adrian Robles','123','adrian@hotmail.com','collaborator','true','7458962002','catalans campus','uniovi','student')");
		con.ejecutarQuery("insert into collaborator values ('002','Jose Magollan','123','jose@gmail.es','collaborator','true','478521999','Milans campus','uniovi','student')");
		//user
		con.ejecutarQuery("insert into user values ('001','Josefina Rua','123','josefa@gmail.com','user','true')");
		con.ejecutarQuery("insert into user values ('002','Jose Cortez','123','jocortez@hotmail.com','user','true')");
		//country
		con.ejecutarQuery("insert into area values ('ES','España')");
		con.ejecutarQuery("insert into area values ('FR','Francia')");
		//indicator
		con.ejecutarQuery("insert into indicator values ('001','poverty')");
		con.ejecutarQuery("insert into indicator values ('002','illiteracy')");
		//Observation
		con.ejecutarQuery("insert into observation values ('001','70','ES','001')");
		con.ejecutarQuery("insert into observation values ('002','100','FR','002')");
		
		con.cerrarConexion();

	}

}
