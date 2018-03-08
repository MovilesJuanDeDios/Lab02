/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Jugador;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author casca
 */
public class ServicioJugador extends Servicio {

    private static final String INSERTAJUGADOR = "{call insertarJugador(?,?,?,?,?)}";
    private static final String LISTARJUGADOR = "{?=call listarJugador()}";
    private static final String BUSCARJUGADOR = "{?=call buscarJugador(?)}";
    private static final String ACTUALIZARJUGADOR = "{call actualizarJugador(?,?,?)}";
    private static final String ELIMINARJUGADOR = "{call eliminarJugador(?)}";

    public ServicioJugador() {

    }

    public void insertarJugador(Jugador jugador) throws GlobalException, NoDataException, SQLException {
        connect();
        CallableStatement pstmt = null;

        try {
            pstmt = cn.prepareCall(INSERTAJUGADOR);
            pstmt.setString(1, jugador.getNickName());
            pstmt.setInt(2, jugador.getPuntaje());

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

    public Collection listarJugador() throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Jugador jugador = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(LISTARJUGADOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                jugador = new Jugador(rs.getString("nickname"),
                        rs.getInt("puntos"));
                coleccion.add(jugador);
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

    public Jugador buscarJugador(String nombre) throws GlobalException, NoDataException, SQLException {
        connect();

        ResultSet rs = null;
        Jugador jugador = null;
        CallableStatement pstmt = null;
        try {
            pstmt = cn.prepareCall(BUSCARJUGADOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, nombre);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                jugador = new Jugador(rs.getString("nickname"),
                        rs.getInt("puntos"));
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
        if (jugador == null) {
            throw new NoDataException("No hay datos");
        }
        System.out.print(jugador.toString());
        return jugador;
    }

    public void actualizarJugador(Jugador jugador) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ACTUALIZARJUGADOR);
            pstmt.setString(1, jugador.getNickName());
            pstmt.setInt(2, jugador.getPuntaje());
            
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

    public void eliminarJugador(String nombre) throws GlobalException, NoDataException, SQLException {
        connect();
        PreparedStatement pstmt = null;
        try {
            pstmt = cn.prepareStatement(ELIMINARJUGADOR);
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

