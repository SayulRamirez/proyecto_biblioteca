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
public class AutorRepositorio {
    
    private final Connection conexion;

    public AutorRepositorio() {
        conexion = Conexion.obtenerConexion();
    }
    
    public List<String> obtenerAutores() {
        
        List<String> autores = new ArrayList<>();
        
        try {
            ResultSet resultado = conexion.createStatement()
                    .executeQuery("select nomAutor from autores");
            
            while(resultado.next()) {
                autores.add(resultado.getString("nomAutor"));
            }
            
            return autores;
        } catch(SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return autores;
        }
    }
    
    public int obtenerIdPorElNombre(String nombreAutor) {
        
        try {
            PreparedStatement queryAutor = conexion.prepareStatement("select idAutor from autores where nomAutor = ?");
            queryAutor.setString(1, nombreAutor);
            
            ResultSet resultadoAutor = queryAutor.executeQuery();
            resultadoAutor.next();
            return resultadoAutor.getInt("idAutor");
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return 0;
        }
    }
}
