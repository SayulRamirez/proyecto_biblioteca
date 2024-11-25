import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class conexion {
    Connection conecta;
    PreparedStatement consulta;
    ResultSet respuesta;
    
    public void Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conecta = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    "Alej@ndro13");

        } catch (Exception e) {
            System.out.println("Error " + e);

        }

    }
    public int obtenerUltimoId() {
       
    if (conecta == null) {
        System.out.println("Error: La conexión a la base de datos no está inicializada.");
        return 0;
    }

    try {
        PreparedStatement accion = conecta.prepareStatement("SELECT idLibro FROM libro ORDER BY IdLibro DESC LIMIT 1;");
        ResultSet registros = accion.executeQuery();

        if (registros.next()) {
            return registros.getInt("idLibro");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return 0;
}
    public String ClaveLibro(){
        try{
            consulta = conecta.prepareStatement("select clavelibro from libro where IdAutor = '?'  ;");
                    respuesta = consulta.executeQuery();
                    respuesta.next();
                    
                    return respuesta.getString("clavelibro");
                    }catch(SQLException e){
                        System.out.println("Error"+ e);
                    }
        return null;
        
    }

    
    public List<String> obtenerAutor(String seccion){
        List<String> lista  = new ArrayList<>();
        try{
            consulta = conecta.prepareStatement("select nomAutor from autor;");
            consulta.setString(1, seccion);
            respuesta = consulta.executeQuery();
             while(respuesta.next()){
                 lista.add(respuesta.getString("nomAutor"));
             }
        }catch(SQLException e){
            System.out.println("Error"+ e);
        }
        return  lista;
    }
    
    public List<String> obtnerSeccion(String Autor){
        List<String> lista = new ArrayList<>();
        try{
            consulta = conecta.prepareStatement("select nomSeccion from seccion;");
            consulta.setString(1, Autor);
            respuesta = consulta.executeQuery();
            while(respuesta.next()){
                lista.add(respuesta.getString("nomSeccion"));
            }
            
        }catch(SQLException e){
                    System.out.println("Error " + e);
                    }
        return lista;
            
    }
    
}
