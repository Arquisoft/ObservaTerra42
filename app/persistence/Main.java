
public class Main {

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		//business
		con.ejecutarQuery("insert into business values ('juanG','Juan Garcia','123','juan@juan.com','business','true','9365778M','business','684596320','catalans campus','a')");
		con.ejecutarQuery("insert into business values ('AnaRosa','Ana Rosa','123','anarosa@hotmail.com','business','true','47896527k','business','789654123','Ramiro I','' )");
		//collaborator
		con.ejecutarQuery("insert into collaborator values ('AdrianR','Adrian Robles','123','adrian@hotmail.com','collaborator','true','7458962002','catalans campus','uniovi','student')");
		con.ejecutarQuery("insert into collaborator values ('josell','Jose Magollan','123','jose@gmail.es','collaborator','true','478521999','Milans campus','uniovi','student')");
		//user
		con.ejecutarQuery("insert into user values ('joseRua','Josefina Rua','123','josefa@gmail.com','user','true')");
		con.ejecutarQuery("insert into user values ('cortez','Jose Cortez','123','jocortez@hotmail.com','user','true')");
		//country
		con.ejecutarQuery("insert into area values ('ES','España')");
		con.ejecutarQuery("insert into area values ('FR','Francia')");
		//indicator
		con.ejecutarQuery("insert into indicator values ('pov','poverty')");
		con.ejecutarQuery("insert into indicator values ('ill','illiteracy')");
		//Observation
		con.ejecutarQuery("insert into observation values ('00001','70','ES','pov')");
		con.ejecutarQuery("insert into observation values ('00002','100','FR','ill')");
		
		con.cerrarConexion();

	}

}
