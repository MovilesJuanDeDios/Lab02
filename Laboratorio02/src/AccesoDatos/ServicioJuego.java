
package AccesoDatos;

import LogicaNegocio.Juego;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;

public class ServicioJuego extends Servicio {

    private static final String INSERTAJUEGO = "{call insertarJuego(?,?,?,?,?)}";
    private static final String LISTARJUEGO = "{?=call listarJuego()}";
    private static final String BUSCARJUEGO = "{?=call buscarJuego(?)}";
    private static final String ACTUALIZARJUEGO = "{call actualizarJuego(?,?,?)}";
    private static final String ELIMINARJUEGO = "{call eliminarJuego(?)}";

    public ServicioJuego() {

    }

    public void insertarJuego(Juego juego) throws GlobalException, NoDataException, SQLException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTAJUEGO);
            pstmt.setString(1, juego.getCodigo());

            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo la inserción");
            } else {
                System.out.println("\nInserción Satisfactoria!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public Collection listarJuego() throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Juego juego = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARJUEGO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                juego = new Juego(rs.getString("codJuego"));
                coleccion.add(juego);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
       
        return coleccion;
    }

    public Juego buscarJuego(String nombre) throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        Juego juego = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARJUEGO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                juego = new Juego(rs.getString("codJuego"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (juego == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(juego.toString());
        return juego;
    }

    public void actualizarJuego(Juego juego) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARJUEGO);
            pstmt.setString(1, juego.getCodigo());
            
            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public void eliminarJuego(String nombre) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARJUEGO);
            pstmt.setString(1, nombre);

            boolean resultado = pstmt.execute();

            if (resultado == true) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                disconnect();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
}
