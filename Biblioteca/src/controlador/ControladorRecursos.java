/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.RecursoDAO;
import dao.RecursoDAOImpl;
import dao.UsuarioDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import vista.AdministrarRecursos;
import modelo.Recurso;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import vista.AdministrarUsuarios;
/**
 *
 * @author jhon
 */
public class ControladorRecursos {
    private AdministrarRecursos ventana;
    private RecursoDAOImpl recursoDao;
    private Recurso recurso;
    private String rutaArchivo;
    public String opcionSeleccionada;
    private List<Recurso> datosTabla;
    private List<Recurso> datosTablaCopia;
    private int contadorDatos;
    
    
    public ControladorRecursos(AdministrarRecursos ventana, Recurso recurso) {
        this.ventana = ventana;
        this.recurso = recurso;
        recursoDao = new RecursoDAOImpl();
        opcionSeleccionada = "Libro";
        datosTabla = recursoDao.obtenerTodosLosRecursos();//lista con los datos de todos los usuarios. El usuario se crea en el listener de listar
        datosTablaCopia = datosTabla;
        contadorDatos = 0;
        
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        //this.ventana.btnListarListener(new ControladorUsuarios.ListarListener());
        this.ventana.btnGuardarListener(new ControladorRecursos.GuardarListener());
        this.ventana.addBtnListarListener(new ControladorRecursos.ListarListener());
        this.ventana.addComboBoxListener(new ControladorRecursos.ComboBoxListener());
    }
    
    public void GuardarEnArchivo() {
        rutaArchivo = "src/txt/TablaRecursos.txt"; //archivo de texto con el id del usuario
        //List<Usuario> datosTabla = usuarioDao.obtenerTodosLosUsuarios();//lista con los datos de todos los usuarios. El usuario se crea en el listener de listar
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            //nuevosDatos();
            if (datosTablaCopia.isEmpty()) {
                ventana.displayErrorMessage("Error: ¡No hay ningún recurso para guardar!");
            } else {
                for (Recurso recursos : datosTablaCopia) {
                    int ID = recursos.getID();
                    String titulo = recursos.getTitulo();
                    String autor = recursos.getAutor();
                    String genero = recursos.getGenero();
                    String tipo = recursos.getTipoRecurso();
                    Object[] datos = {ID,titulo,autor,genero,tipo};
                    String datosString = Arrays.toString(datos);
                    String datosStringRecortado = datosString.substring(1,datosString.length()-1);
                    escritor.write(datosStringRecortado);
                    escritor.newLine();
                    contadorDatos++;
                }

                ventana.deshabilitarGuardar();
                System.out.println("Archivo creado exitosamente.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
        }
    }
    
    public void leerArchivos() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("src/txt/TablaRecursos.txt"));
            String lineaArchivo;
            try {
                while ((lineaArchivo = lector.readLine()) != null) {
                    String[] datosReconstruidos = lineaArchivo.split(", ");
                    RecursoDAOImpl recursoDaoPers;
                    Recurso recursoNuevo;
                    int id;
                    String titulo;
                    String autor;
                    String genero;
                    String tipo;

                    recursoNuevo = new Recurso();
                    recursoDaoPers = new RecursoDAOImpl();
                    id = Integer.parseInt(datosReconstruidos[0]);
                    titulo = datosReconstruidos[1];
                    autor = datosReconstruidos[2];
                    genero = datosReconstruidos[3];
                    tipo = datosReconstruidos[4];

                    recursoNuevo.setID(id);
                    recursoNuevo.setTitulo(titulo);
                    recursoNuevo.setAutor(autor);
                    recursoNuevo.setGenero(genero);
                    recursoNuevo.setTipoRecurso(tipo);
                    recursoDaoPers.crearRecurso(recursoNuevo);
                    datosPersistentes(recursoDaoPers);
                    System.out.println(recursoNuevo.getAutor());
                }
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se encontró el archivo.");
        }
    }
    
    public void datosPersistentes(RecursoDAOImpl recurso){
        
        for(Recurso recursos : recurso.obtenerTodosLosRecursos()){
            ventana.addDatosTabla(recursos);
        } 
    }
    
    public void nuevosDatos() {
        datosTablaCopia = datosTabla.subList(contadorDatos, datosTabla.size());
    }
    
    class GuardarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("guardar")) {
                GuardarEnArchivo();
                System.out.print("guardado");

            }
        }
    }
    
    class ListarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("listar")) {
                Recurso recursoNuevo;
                int id;
                String titulo;
                String autor;
                String genero;
                String tipo;
                try {
                    recursoNuevo = new Recurso();
                    id = ventana.getID();
                    titulo = ventana.getTitulo();
                    autor = ventana.getAutor();
                    genero = ventana.getGenero();
                    tipo = opcionSeleccionada;

                    recursoNuevo.setID(id);
                    recursoNuevo.setTitulo(titulo);
                    recursoNuevo.setAutor(autor);
                    recursoNuevo.setGenero(genero);
                    recursoNuevo.setTipoRecurso(tipo);
                    recursoDao.crearRecurso(recursoNuevo);//añade el usuario con todos sus atributos a una lista de la implementación de la interfaz DAO de usuario
                    System.out.println(recursoDao.obtenerTodosLosRecursos());
                    ventana.addDatosTabla(recursoNuevo);
                    nuevosDatos();
                    ventana.setCamposVacios();
                    //GuardarEnArchivo();

                    //vista.setArea(modelo.getArea());
                    //vista.activarControles(false);
                    ventana.habilitarGuardar();
                } catch (NumberFormatException ex) {
                    ventana.displayErrorMessage("Error: ¡Revisa los datos ingresados!");
                }

            }

            //System.out.print("guardado");
        }

    }
    
    
    
    
    
    /*class GuardarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("guardar")) {
                int id;
                String titulo;
                String autor;
                String genero;
                String tipo;
                try {
                    id = ventana.getID();
                    titulo = ventana.getTitulo();
                    autor = ventana.getAutor();
                    genero = ventana.getGenero();
                    tipo = opcionSeleccionada;

                    recurso.setID(id);
                    recurso.setTitulo(titulo);
                    recurso.setAutor(autor);
                    recurso.setGenero(genero);
                    recurso.setTipoRecurso(tipo);                   
               

                    //vista.setArea(modelo.getArea());
                    //vista.activarControles(false);
                } catch (NumberFormatException ex) {
                    ventana.displayErrorMessage("¡Error: Revisa los datos ingresados!");
                }
            }
           
            if(e.getActionCommand().equalsIgnoreCase("listar")){
                ventana.addDatosTabla(recurso);
            }
            

            System.out.print("guardado");
 
        }*/
        
    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            opcionSeleccionada = (String) ventana.getComboBox().getSelectedItem();
        }
    }    
        
}

    
   

