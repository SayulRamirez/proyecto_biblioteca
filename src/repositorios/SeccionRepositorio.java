/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sayul
 */
public class SeccionRepositorio {
    
    private final Connection conexion;

    public SeccionRepositorio() {
        conexion = Conexion.obtenerConexion();
    }
    
    public List<String> obtenerSecciones() {
        
        List<String> secciones = new ArrayList<>();
        
        try {
            ResultSet resultado = conexion.createStatement()
                    .executeQuery("select nomSeccion from secciones");
            
            while(resultado.next()) {
                secciones.add(resultado.getString("nomSeccion"));
            }
            
            return secciones;
        } catch(SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return secciones;
        }
    }
    
    
    public int obtenerIdPorElNombre(String nombreSeccion) {
        
        try {
            PreparedStatement seccion = conexion.prepareStatement("select idSeccion from secciones where nomSeccion = ?");
            seccion.setString(1, nombreSeccion);
            
            ResultSet resultadoSeccion = seccion.executeQuery();
            resultadoSeccion.next();
            return resultadoSeccion.getInt("idSeccion");
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return 0;
        }
    }
}
