/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author escritorio
 */
public class ConexionBD {
private String url= "";
public Connection con = null;
private Statement stmt = null;
private ResultSet rs = null;

public ConexionBD() {
    url= "jdbc:sqlite:reto5db.db";
    try {

    con = DriverManager.getConnection(url);
    if(con != null) {
        DatabaseMetaData meta = con.getMetaData();
        System.out.println("Base de datos conectada " + meta.getDriverName());
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public Connection getConnection() {
    return con;
        }

    public void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet consultarBD(String sentencia) {
        try {
            stmt= con.createStatement();
            rs= stmt.executeQuery(sentencia);
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            return rs;
    }

    public boolean insertarBD(String sentencia) {
        try {
            stmt= con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
        return false;
        }
        return true;
    }
    public boolean borrarBD(String sentencia) {
        try {
            stmt= con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
        return false;
        }
        return true;
    }

    public boolean actualizarBD(String sentencia) {
        try {
            stmt= con.createStatement();
            stmt.executeUpdate(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
    public boolean setAutoCommitBD(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit" + sqlex.getMessage());
            return false;
        }
        return true;
    }
    public void cerrarConexion() {
        closeConnection(con);
    }
    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer commit" + sqlex.getMessage());
            return false;
        }
    }
    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback" + sqlex.getMessage());
            return false;
        }
    }
    
}
