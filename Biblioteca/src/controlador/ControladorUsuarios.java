/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdministrarUsuarios;
import modelo.Usuario;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhon
 */
public class ControladorUsuarios {

    private AdministrarUsuarios ventana;
    private UsuarioDAOImpl usuarioDao;
    private Usuario usuario;
    private String rutaArchivo;
    public String opcionSeleccionada;
    private List<Usuario> datosTabla;
    private List<Usuario> datosTablaCopia;//para que no se repitan datos    
    private int contadorDatos;

    public ControladorUsuarios(AdministrarUsuarios ventana, Usuario usuario) {
        this.ventana = ventana;
        this.usuario = usuario;
        usuarioDao = new UsuarioDAOImpl();
        opcionSeleccionada = "Estudiante";
        datosTabla = usuarioDao.obtenerTodosLosUsuarios();//lista con los datos de todos los usuarios. El usuario se crea en el listener de listar
        datosTablaCopia = datosTabla;
        contadorDatos = 0;

        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        this.ventana.btnListarListener(new ListarListener());
        this.ventana.btnGuardarListener(new GuardarListener());
        this.ventana.addComboBoxListener(new ComboBoxListener());
    }

    //usuarioDao.obtenerTodosLosUsuarios(); 
    /**
     * String dataString = "";
     *
     * for(int i = 0; i< data.length ; i
     *
     *
     * ++){ dataString += Arrays.toString(data[i]) + "\n"; }
     *
     * System.out.println (dataString); return "\n" + dataString ;
     */
    public void GuardarEnArchivo() {
        rutaArchivo = "src/txt/TablaUsuarios.txt"; //archivo de texto con el id del usuario
        //List<Usuario> datosTabla = usuarioDao.obtenerTodosLosUsuarios();//lista con los datos de todos los usuarios. El usuario se crea en el listener de listar
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            //nuevosDatos();
            if (datosTablaCopia.isEmpty()) {
                ventana.displayErrorMessage("Error: ¡No hay ningún usuario para guardar!");
            } else {
                for (Usuario usuarios : datosTablaCopia) {
                    int ID = usuarios.getID();
                    String nombre = usuarios.getNombre();
                    String correo = usuarios.getCorreo();
                    long telefono = usuarios.getTelefono();
                    String estamento = usuarios.getEstamento();
                    Object[] datos = {ID,nombre,correo,telefono,estamento};
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
            BufferedReader lector = new BufferedReader(new FileReader("src/txt/TablaUsuarios.txt"));
            String lineaArchivo;
            try {
                while ((lineaArchivo = lector.readLine()) != null) {
                    String[] datosReconstruidos = lineaArchivo.split(", ");
                    UsuarioDAOImpl usuarioDaoPers;
                    Usuario usuarioNuevo;
                    int id;
                    String nombre;
                    String correo;
                    long telefono;
                    String estamento;

                    usuarioNuevo = new Usuario();
                    usuarioDaoPers = new UsuarioDAOImpl();
                    id = Integer.parseInt(datosReconstruidos[0]);
                    nombre = datosReconstruidos[1];
                    correo = datosReconstruidos[2];
                    telefono = Long.parseLong(datosReconstruidos[3]);
                    estamento = datosReconstruidos[4];

                    usuarioNuevo.setID(id);
                    usuarioNuevo.setNombre(nombre);
                    usuarioNuevo.setCorreo(correo);
                    usuarioNuevo.setTelefono(telefono);
                    usuarioNuevo.setEstamento(estamento);
                    usuarioDaoPers.crearUsuario(usuarioNuevo);
                    datosPersistentes(usuarioDaoPers);
                    System.out.println(usuarioNuevo.getCorreo());
                }
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se encontró el archivo.");
        }
    }

    public void datosPersistentes(UsuarioDAOImpl usuario){
        
        for(Usuario usuarios : usuario.obtenerTodosLosUsuarios()){
            ventana.addDatosTabla(usuarios);
        } 
    }
    
    
    public void nuevosDatos() {
        datosTablaCopia = datosTabla.subList(contadorDatos, datosTabla.size());
    }

    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            opcionSeleccionada = (String) ventana.getComboBox().getSelectedItem();
        }
    }

    class GuardarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("guardar")) {
                GuardarEnArchivo();

            }
        }
    }

    class ListarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("listar")) {
                Usuario usuarioNuevo;
                int id;
                String nombre;
                String correo;
                long telefono;
                String estamento;
                try {
                    usuarioNuevo = new Usuario();
                    id = ventana.getID();
                    nombre = ventana.getNombre();
                    correo = ventana.getCorreo();
                    telefono = ventana.getTelefono();
                    estamento = opcionSeleccionada;

                    usuarioNuevo.setID(id);
                    usuarioNuevo.setNombre(nombre);
                    usuarioNuevo.setCorreo(correo);
                    usuarioNuevo.setTelefono(telefono);
                    usuarioNuevo.setEstamento(estamento);
                    usuarioDao.crearUsuario(usuarioNuevo);//añade el usuario con todos sus atributos a una lista de la implementación de la interfaz DAO de usuario
                    System.out.println(usuarioDao.obtenerTodosLosUsuarios());
                    ventana.addDatosTabla(usuarioNuevo);
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
}
