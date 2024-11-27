import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositorio {
    
    private final Connection conexion;
    
    public LibroRepositorio() {
        conexion = Conexion.obtenerConexion();
    }
    
    public int obtenerUltimoId() {
        try {
            ResultSet respuesta = conexion.prepareStatement("SELECT MAX(idLibro) as id FROM libros")
                    .executeQuery();
            
            respuesta.next();
            return respuesta.getInt("id");
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
        }
        return 0;
    }
    
    public int agregarLibro(Libro libro) {
        
        try {
            
            PreparedStatement seccion = conexion.prepareStatement("select idSeccion from secciones where nomSeccion = ?");
            seccion.setString(1, libro.getNomSeccion());
            
            ResultSet resultadoSeccion = seccion.executeQuery();
            resultadoSeccion.next();
            int idSeccion = resultadoSeccion.getInt("idSeccion");
            
            PreparedStatement autor = conexion.prepareStatement("select idAutor from autores where nomAutor = ?");
            autor.setString(1, libro.getNomAutor());
            
            ResultSet resultadoAutor = autor.executeQuery();
            resultadoAutor.next();
            int idAutor= resultadoAutor.getInt("idSeccion");
            
            PreparedStatement query = conexion.prepareStatement("""
                                                                insert into libros (claveLibro, idSeccion, idAutor, nomLibro, resumenLibro, existenciasLibro)
                                                                values (?, ?, ?, ?, ?, ?);
                                                                """);
            
            query.setString(1, libro.getClaveLibro());
            query.setInt(2, idSeccion);
            query.setInt(3, idAutor);
            query.setString(4, libro.getNomLibro());
            query.setString(5, libro.getResumenLibro());
            query.setInt(6, libro.getExistenciasLibro());
            
            return query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return 0;
        }
    }
    
    public List<Libro> obtenerLibros (){
        
        List<Libro> lista  = new ArrayList<>();
        try{
            ResultSet resultado = conexion.prepareStatement("""
                                                            SELECT 
                                                                l.idLibro, 
                                                                l.claveLibro, 
                                                                l.nomLibro, 
                                                                l.resumenLibro, 
                                                                l.existenciasLibro,
                                                                s.nomSeccion, 
                                                                a.nomAutor 
                                                            FROM 
                                                                libros l
                                                            JOIN 
                                                                secciones s ON l.idSeccion = s.idSeccion
                                                            JOIN 
                                                                autores a ON l.idAutor = a.idAutor
                                                            ORDER BY 
                                                                l.idLibro ASC;
                                                            """)
                    .executeQuery();
            while(resultado.next()){
                lista.add(new Libro(
                        resultado.getInt("idLibro"),
                        resultado.getString("claveLibro"),
                        resultado.getString("nomLibro"),
                        resultado.getString("resumenLibro"),
                        resultado.getInt("existenciasLibro"),
                        resultado.getString("nomSeccion"),
                        resultado.getString("nomAutor")
                ));
            }
        }catch(SQLException e){
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
        }
        return  lista;
    } 
    
    public int actualizarLibro(Libro libro) {
        try {
            PreparedStatement seccion = conexion.prepareStatement("select idSeccion from secciones where nomSeccion = ?");
            seccion.setString(1, libro.getNomSeccion());
            
            ResultSet resultadoSeccion = seccion.executeQuery();
            resultadoSeccion.next();
            int idSeccion = resultadoSeccion.getInt("idSeccion");
            
            PreparedStatement autor = conexion.prepareStatement("select idAutor from autores where nomAutor = ?");
            autor.setString(1, libro.getNomAutor());
            
            ResultSet resultadoAutor = autor.executeQuery();
            resultadoAutor.next();
            int idAutor= resultadoAutor.getInt("idSeccion");
            
            PreparedStatement queryActualizacion = conexion.prepareStatement("""
                                                                             update libros set claveLibro = ?,
                                                                             idSeccion = ?,
                                                                             idAutor = ?,
                                                                             nomLibro = ?,
                                                                             resumenLibro = ?,
                                                                             existenciasLibro = ?
                                                                             """);
            
            queryActualizacion.setString(1, libro.getClaveLibro());
            queryActualizacion.setInt(2, idSeccion);
            queryActualizacion.setInt(3, idAutor);
            queryActualizacion.setString(4, libro.getNomLibro());
            queryActualizacion.setString(5, libro.getResumenLibro());
            queryActualizacion.setInt(6, libro.getExistenciasLibro());
            
            return queryActualizacion.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return 0;
        }
    }
    
    public int eliminarLibro(String clave) {
        try {
            PreparedStatement queryEliminar = conexion.prepareStatement("delete from libros where claveLibro = ?");
            queryEliminar.setString(1, clave);
            
            return queryEliminar.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Ocurrio un error: " + e.getErrorCode() + ", mensaje: " + e.getMessage());
            return 0;
        }
    }
}
