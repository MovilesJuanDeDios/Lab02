
package AccesoDatos;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

public class Servicio {

    public Servicio() {
    }

    public Connection connect() throws NoDataException, GlobalException, SQLException {
        try {
            ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            cn = ds.getConnection(user, password);
            return cn;
        } catch (SQLException ex) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
    }

    public Boolean disconnect() {
        try {
            cn.close();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    protected Connection cn;
    private final String user = "servidor";
    private final String password = "servidor";
    private final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private OracleDataSource ds;
}
