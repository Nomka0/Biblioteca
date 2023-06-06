/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Date;
import modelo.Recurso;
import modelo.Usuario;

/**
 *
 * @author jhon
 */
public class Prestamo {
    private int ID;
    private int idrecurso;
    private int idusuario;
    private String estado;
    private String diaRegistro, mesRegistro, anioRegistro,
    diaLimite, mesLimite, anioLimite,diaDevolucion, mesDevolucion, anioDevolucion;

    public Prestamo(int ID, int usuario,int recurso, String estado,
     String diaRegistro, String mesRegistro, String anioRegistro,
     String diaLimite, String mesLimite, String anioLimite,
     String diaDevolucion, String mesDevolucion, String anioDevolucion) {
        this.idrecurso = recurso;
        this.idusuario = usuario;
        this.estado = estado;
        this.diaRegistro = diaRegistro;
        this.mesRegistro = diaRegistro;
        this.anioRegistro = diaRegistro;

        this.diaLimite = diaLimite;
        this.mesLimite = mesLimite;
        this.anioLimite = anioLimite;

        this.diaDevolucion = diaDevolucion;
        this.mesDevolucion = mesDevolucion;
        this.anioDevolucion = anioDevolucion;
    }

    public Prestamo() {
    }

    public int getID() {
        return ID;
    }
    
    

    public int getIDrecurso() {
        return idrecurso;
    }

    public int getIDusuario() {
        return idusuario;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaRegistro() {
        return diaRegistro + "/ " + mesRegistro + "/ " + anioRegistro;
    }

    public String getFechaLimite() {
        return diaLimite + "/ " + mesLimite + "/ " + anioLimite;
    }

    public String getFechaDevolucion() {
        return diaDevolucion + "/ " + mesDevolucion + "/ " + anioDevolucion;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    

    public void setIDrecurso(int idrecurso) {
        this.idrecurso = idrecurso;
    }

    public void setIDusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaRegistro(String diaReg, String mesReg, String anioReg) {
        diaRegistro = diaReg;
        mesRegistro = mesReg;
        anioRegistro = anioReg;
    }

    public void setFechaLimite(String diaLim, String mesLim, String anioLim) {
        diaLimite = diaLim;
        mesLimite = mesLim;
        anioLimite = anioLim;
    }

    public void setFechaDevolucion(String diaDev, String mesDev, String anioDev) {
        diaDevolucion = diaDev;
        mesDevolucion = mesDev;
        anioDevolucion = anioDev;
    }    
}
