/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jhon
 */
public class Recurso {
    private int ID;
    private String titulo;
    private String autor;
    private String genero;
    private String tipoRecurso;

    public Recurso(int ID, String titulo, String autor, String genero, String tipoRecurso) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.tipoRecurso = tipoRecurso;
    }

    public Recurso() {
        
    }

    public int getID() {
        return ID;
    }
    

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    
    
}
