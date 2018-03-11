
package AccesoDatos;

import LogicaNegocio.Ficha;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;

public class ServicioFicha extends Servicio {

    private static final String INSERTAFICHA = "{call insertarFicha(?)}";
    private static final String LISTARFICHA = "{?=call listarFicha()}";
    private static final String BUSCARFICHA = "{?=call buscarFicha(?)}";
    private static final String ACTUALIZARFICHA = "{call actualizarFicha(?,?,?)}";
    private static final String ELIMINARFICHA = "{call eliminarFicha(?)}";

    public ServicioFicha() {

    }

    public void insertarFicha(Ficha ficha) throws GlobalException, NoDataException, SQLException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTAFICHA);
            pstmt.setInt(1, ficha.getTotal());
            pstmt.setInt(2, ficha.getValorDer());
            pstmt.setInt(3, ficha.getValorIzq());

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

    public Collection listarFicha() throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Ficha ficha = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARFICHA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ficha = new Ficha(rs.getInt("total"),
                        rs.getInt("valorDer"),
                        rs.getInt("valorIzq"));
                coleccion.add(ficha);
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

    public Ficha buscarFicha(int total) throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        Ficha ficha = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARFICHA);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, total);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ficha = new Ficha(rs.getInt("total"),
                        rs.getInt("valorDer"),
                        rs.getInt("valorIzq"));
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
        if (ficha == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(ficha.toString());
        return ficha;
    }

    public void actualizarFicha(Ficha ficha) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARFICHA);
            pstmt.setInt(1, ficha.getTotal());
            pstmt.setInt(2, ficha.getValorIzq());
            pstmt.setInt(3, ficha.getValorDer());
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

    public void eliminarFicha(int total) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARFICHA);
            pstmt.setInt(1, total);

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

