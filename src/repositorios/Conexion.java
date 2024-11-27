package repositorios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sayul
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static Connection conexion;
    
    private Conexion() {
        conectar();
    }
    
    private static void conectar() {
        try {
            
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "Alej@ndro13"
            );
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
        }
    }
    
    public static Connection obtenerConexion() {
        if (conexion == null) {
            new Conexion();
        }
        
        return conexion;
    }
    
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            }
        }
    }
}
