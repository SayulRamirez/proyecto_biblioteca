package modelos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sayul
 */
public class Libro {
    
    private int idLibro;
    private String claveLibro;
    private String nomLibro;
    private String resumenLibro;
    private int existenciasLibro;
    private String nomSeccion;
    private String nomAutor;
    
    public Libro(int idLibro, String claveLibro, String nomLibro, String resumenLibro, int existenciasLibro, String nomSeccion, String nomAutor) {
        this.idLibro = idLibro;
        this.claveLibro = claveLibro;
        this.nomLibro = nomLibro;
        this.resumenLibro = resumenLibro;
        this.existenciasLibro = existenciasLibro;
        this.nomSeccion = nomSeccion;
        this.nomAutor = nomAutor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getClaveLibro() {
        return claveLibro;
    }

    public void setClaveLibro(String claveLibro) {
        this.claveLibro = claveLibro;
    }

    public String getNomLibro() {
        return nomLibro;
    }

    public void setNomLibro(String nomLibro) {
        this.nomLibro = nomLibro;
    }

    public String getResumenLibro() {
        return resumenLibro;
    }

    public void setResumenLibro(String resumenLibro) {
        this.resumenLibro = resumenLibro;
    }

    public int getExistenciasLibro() {
        return existenciasLibro;
    }

    public void setExistenciasLibro(int existenciasLibro) {
        this.existenciasLibro = existenciasLibro;
    }

    public String getNomSeccion() {
        return nomSeccion;
    }

    public void setNomSeccion(String nomSeccion) {
        this.nomSeccion = nomSeccion;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }
}
