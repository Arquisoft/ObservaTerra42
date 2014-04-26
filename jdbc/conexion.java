import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.*;

public class conexion {
    static Statement  st   = null;  
    static ResultSet  res  = null; 
    static String     sql  = null;
    private Connection conexion;

    public conexion()
    {

    }
    
    

    public boolean connectToAccess() {

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
     
    public Vector ejecutarQuery(String sql)
    {
        Vector rows = new Vector();
         
        try{
            Statement stmt = conexion.createStatement();
            stmt.executeQuery(sql);//muestra resultados equivalentes en SQL  a utilizar SELECT
            ResultSet rs= stmt.getResultSet(); //obtiene el resultado de la consulta y lo guarda en rs
            rs.close();
            stmt.close();

        }catch(SQLException e ){System.out.println("Hubo un error");};
         
        return null;
         
    }
    public void cerrarConexion()
    {
        try{
            this.conexion.close();
        }catch(SQLException e){};
    }

}
