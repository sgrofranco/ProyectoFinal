
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistencia {

    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsn;

    public String servidor, basededatos, usuario, clave, ejecutar;

    public Connection conectarse() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            servidor = "localhost:3308/";
            basededatos = "proyectofinal";
            usuario = "root";
            clave ="";

            cn = (Connection) DriverManager.getConnection("jdbc:mysql://" + servidor + 
                    basededatos+"?autoReconnect=true&useSSL=false",usuario, clave);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cn;
    }

    public ResultSet consultaSQL(String busqueda) {

        try {
            
            ps = conectarse().prepareStatement(busqueda);

            rs = (ResultSet) ps.executeQuery();

            rsn = rs.getMetaData();

        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
