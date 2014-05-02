
public class Main {

	public static void main(String[] args) {
		conexion con = new conexion();
		con.connectToAccess();
		//indicator
		con.ejecutarQuery("insert into indicator values ('000a','child poverty','level of child poverty in this area')");
		con.ejecutarQuery("insert into indicator values ('001a','gender violence','index of gender violence that presents the area')");
		con.ejecutarQuery("insert into indicator values ('002a','illiteracy','index of illiterate people in the area')");
		//area
		con.ejecutarQuery("insert into area values ('000a','Spain','country Spain-EU','country')");
		con.ejecutarQuery("insert into area values ('001a','UE','European union, european union, a political community is built right regimen international organization','area')");
		con.ejecutarQuery("insert into area values ('002a','France','country France-EU','country')");
		//users
		con.ejecutarQuery("insert into users values ('000a','Sergio Polan','000','polan@polan.es','collaborator','true','78945612f','student','654120255','The catalan campus','a','uniovi','informatic engineer','2014-02-05')");
		con.ejecutarQuery("insert into users values ('001a','Diego ','001','diego@diego.es','user','true','7145875f','student','654120255','The catalan campus','b','uniovi','consultant','2014-02-05')");
		con.ejecutarQuery("insert into users values ('002a','Master','002','master@master.es','business','true','7245896f','student','258741025','The catalan campus','c','uniovi','director','2014-02-05')");
		con.ejecutarQuery("insert into users values ('003a','Juan','003','juan@juan.es','admin','true','75862568g','student','75896325','The catalan campus','c','uniovi','director','2014-02-05')");
		//observation
		con.ejecutarQuery("insert into observation values ('001a','001a','001a','percent','200','365')");
		con.ejecutarQuery("insert into observation values ('002a','002a','002a','percent','100','30')");
		con.cerrarConexion();

	}

}
