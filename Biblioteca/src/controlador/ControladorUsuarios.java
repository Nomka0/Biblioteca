/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdministrarUsuarios;
import modelo.Usuario;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import vista.Vista;

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
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private UsuarioDAOImpl usuarioDaoPers;
    private UsuarioDAOImpl usuariosTotalesDAO;
    //private List<Usuario> usuariosTotales;//Aquí se almacenarán los persistentes, y los que se crean en la sesión, para manejarlos todos
    private int filaSeleccionada;
    private ArrayList<Integer> datosAEliminar;
    private ArrayList<Integer> indexadorTabla;//tendrá los indices actualizados de la tabla en una lista, para después no borrar cosas que no se supone debían ser borradas
    private int contadorTotal;
    private int contadorLista;

    public ControladorUsuarios(AdministrarUsuarios ventana, Usuario usuario) {
        this.ventana = ventana;
        this.usuario = usuario;
        usuarioDao = new UsuarioDAOImpl();
        opcionSeleccionada = "Estudiante";
        contadorDatos = 0;
        tabla = ventana.getTabla();
        modeloTabla = ventana.getModeloTabla();
        usuariosTotalesDAO = new UsuarioDAOImpl();
        //usuariosTotales = usuariosTotalesDAO.obtenerTodosLosUsuarios();
        rutaArchivo = "src/txt/TablaUsuarios.txt"; //archivo de texto con el id del usuario
        datosAEliminar = new ArrayList<>();
        indexadorTabla = new ArrayList<>();
        usuarioDaoPers = new UsuarioDAOImpl();

        ventana.habilitarEditar(false);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        ventana.jTableListener(new ManejadoraDeMouse());
        ventana.btnListarListener(new ListarListener());
        ventana.btnGuardarListener(new GuardarListener());
        ventana.btnEditarListener(new EditarListener());
        ventana.addComboBoxListener(new ComboBoxListener());
        ventana.btnEliminarListener(new EliminarListener());
        ventana.btnOkListener(new OkListener());
    }

    public void eliminarUsuariosTotales() {
        //usuariosTotalesDAO.obtenerTodosLosUsuarios() = usuariosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
        sort(datosAEliminar);
        reverse(datosAEliminar);
        for (int i = 0; i < datosAEliminar.size(); i++) {
            usuariosTotalesDAO.eliminarUsuario(datosAEliminar.get(i));
        }
        //usuariosTotalesDAO.obtenerTodosLosUsuarios() = usuariosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
    }

    public void GuardarEnArchivo() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {

            try {//lista datos
                //if(ventana.getEstaVacio() == false){
                    datosALista();
                //}
            } catch (NumberFormatException ex) {
                ventana.displayErrorMessage("¡Revisa los datos ingresados!");
            }

            eliminarUsuariosTotales();
            eliminarDatosEnArchivo();
            System.out.println("usuarios totales: " + usuariosTotalesDAO.obtenerTodosLosUsuarios());
            for (Usuario usuarios : usuariosTotalesDAO.obtenerTodosLosUsuarios()) {
                int ID = usuarios.getID();
                String nombre = usuarios.getNombre();
                String correo = usuarios.getCorreo();
                long telefono = usuarios.getTelefono();
                String estamento = usuarios.getEstamento();
                Object[] datos = {ID, nombre, correo, telefono, estamento};
                String datosString = Arrays.toString(datos);
                String datosStringRecortado = datosString.substring(1, datosString.length() - 1);
                escritor.write(datosStringRecortado);
                escritor.newLine();
                //contadorDatos++;
            }
            //else ventana.displayErrorMessage("Error: ¡No hay ningún usuario para guardar!");
            contadorLista = usuariosTotalesDAO.obtenerTodosLosUsuarios().size() - 1;
            datosAEliminar.clear();
            reiniciarIndexador();
            //ventana.deshabilitarGuardar();
            //postGuardar();
            ventana.displayErrorMessage("Guardado");

            //}
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
        }

    }

    public void leerArchivos() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String lineaArchivo;
            try {
                while ((lineaArchivo = lector.readLine()) != null) {

                    String[] datosReconstruidos = lineaArchivo.split(", ");
                    Usuario usuarioNuevo;
                    int id;
                    String nombre;
                    String correo;
                    long telefono;
                    String estamento;

                    usuarioNuevo = new Usuario();

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
                    System.out.println(usuarioNuevo.getCorreo());
                }

                datosPersistentes(usuarioDaoPers);
                usuariosTotalesDAO.obtenerTodosLosUsuarios().addAll(usuarioDaoPers.obtenerTodosLosUsuarios());
                contadorTotal = usuarioDaoPers.obtenerTodosLosUsuarios().size();

                reiniciarIndexador();

            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se encontró el archivo.");
        }
    }

    //reinicia indexador tabla, para volver a almacenar las filas que se quedan en la tabla. Esto es para cuando empieza la ventana, o cuando se guardan datos
    public void reiniciarIndexador() {
        contadorTotal = usuarioDaoPers.obtenerTodosLosUsuarios().size();
        indexadorTabla.clear();
        if (contadorTotal == 1) {
            indexadorTabla.add(0);
        } else {
            for (int i = 0; i < contadorTotal; i++) {
                indexadorTabla.add(i);
            }
        }

    }

    public void datosPersistentes(UsuarioDAOImpl usuario) {
        for (Usuario usuarios : usuario.obtenerTodosLosUsuarios()) {
            ventana.addDatosTabla(usuarios);
        }
    }
    
        public void nuevosDatos() {
            datosTablaCopia = datosTabla.subList(contadorDatos, datosTabla.size());
        }

    // cuando le das click a una fila, se muestran sus datos
    public String mostrarDatos() {
        int filaSeleccionada = tabla.getSelectedRow();
        //int columnaSeleccionada = tabla.getSelectedColumn();

        int numeroColumnas = modeloTabla.getColumnCount();
        Object[] filaSeleccionadaData = new Object[numeroColumnas];

        for (int i = 0; i < numeroColumnas; i++) {
            Object valorSeleccionado = tabla.getValueAt(filaSeleccionada, i);
            filaSeleccionadaData[i] = valorSeleccionado;
        }
        String filaString = Arrays.toString(filaSeleccionadaData);
        String filaStringRecortada = filaString.substring(1, filaString.length() - 1);
        System.out.println("Valores de la fila seleccionada: " + filaStringRecortada);
        return filaStringRecortada;
    }

    //llena los campos con los datos qque seleccionas cuando los clickeas
    public void rellenarDatos() {
        String datosARellenar = mostrarDatos();
        String[] splitDatos = datosARellenar.split(", ");
        int id = Integer.parseInt(splitDatos[0]);
        String nombre = splitDatos[1];
        String correo = splitDatos[2];
        long telefono = Long.parseLong(splitDatos[3]);
        String estamento = splitDatos[4];

        ventana.setDatos(id, nombre, correo, telefono);
        cambioComboBox(estamento);
    }

    public void eliminarDatosEnArchivo() {
        File archivo = new File(rutaArchivo);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(archivo));
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioComboBox(String tipo) {
        switch (tipo) {
            case "Estudiante":
                ventana.setComboBox(0);
                break;
            case "Empleado":
                ventana.setComboBox(1);
                break;
            case "Profesor":
                ventana.setComboBox(2);
                break;
        }
    }

    public void datosALista() {
        Usuario usuarioNuevo;
        int id;
        String nombre;
        String correo;
        long telefono;
        String estamento;

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
        //nuevosDatos();
        ventana.setCamposVacios();
        //GuardarEnArchivo();
        contadorTotal++;
        contadorDatos++;
        contadorLista = usuariosTotalesDAO.obtenerTodosLosUsuarios().size() - 1;
        contadorLista++;

        indexadorTabla.add(contadorLista);
        //vista.setArea(modelo.getArea());
        //vista.activarControles(false);
        ventana.habilitarGuardar();

        System.out.println("indexador tabla: " + indexadorTabla);
        System.out.println("usuarios Dao: " + usuarioDao.obtenerTodosLosUsuarios());
        usuariosTotalesDAO.obtenerTodosLosUsuarios().add(usuarioDao.obtenerTodosLosUsuarios().get(contadorDatos - 1));
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

    class ManejadoraDeMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent evento) {
            filaSeleccionada = tabla.getSelectedRow();
            mostrarDatos();
            rellenarDatos();
            ventana.habilitarEditar(true);
            ventana.habilitarEliminar(true);
            System.out.println("indexador tabla:" + indexadorTabla);
            System.out.println("datos A Eliminar: " + datosAEliminar);
            System.out.println("Index de fila seleccionada: " + filaSeleccionada);
            System.out.println("Estos son los usuarios totales: " + usuariosTotalesDAO.obtenerTodosLosUsuarios());
            System.out.println("contador lista: " + contadorTotal);
            System.out.println(usuarioDaoPers.obtenerTodosLosUsuarios());
        }
    }

    class ListarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("listar")) {
                try {
                    datosALista();
                } catch (NumberFormatException ex) {
                    ventana.displayErrorMessage("Error: ¡Revisa los datos ingresados!");
                }

            }
            //System.out.print("guardado");
        }
    }

    class EliminarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * if(filaSeleccionada != 0){ filaSeleccionada -= 1; }
             */

            ventana.eliminarFila(filaSeleccionada);
            datosAEliminar.add(indexadorTabla.get(filaSeleccionada));
            indexadorTabla.remove(filaSeleccionada);

            contadorTotal--;
            contadorLista = usuariosTotalesDAO.obtenerTodosLosUsuarios().size() - 1;

            System.out.println("indexador tabla: " + indexadorTabla);
            System.out.println("datos A Eliminar: " + datosAEliminar);
            System.out.println("usuarios totales: " + usuariosTotalesDAO.obtenerTodosLosUsuarios());
            ventana.habilitarGuardar();
            ventana.setCamposVacios();
            ventana.habilitarEliminar(false);
            ventana.habilitarEditar(false);
        }
    }

    public void editarElementos(int index) {
        Usuario usuarioEditado = new Usuario();
        int id = ventana.getID();
        String nombre = ventana.getNombre();
        String correo = ventana.getCorreo();
        long telefono = ventana.getTelefono();
        String estamento = opcionSeleccionada;

        usuarioEditado.setID(id);
        usuarioEditado.setNombre(nombre);
        usuarioEditado.setCorreo(correo);
        usuarioEditado.setTelefono(telefono);
        usuarioEditado.setEstamento(estamento);

        ventana.editarElementoTabla(index, usuarioEditado);
        usuariosTotalesDAO.actualizarUsuario(index, usuarioEditado);
    }

    class EditarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("editar")) {
                try {
                    int index = tabla.getSelectedRow(); //la fila a cambiar los datos
                    editarElementos(index);
                    ventana.habilitarEditar(false);
                    ventana.displayErrorMessage("Datos actualizados exitosamente.");
                    ventana.setCamposVacios();
                    ventana.habilitarEliminar(false);
                } catch (NumberFormatException ex) {
                    ventana.displayErrorMessage("Error: ¡Revisa los datos ingresados!");
                }
            }

            System.out.print("la fila" + tabla.getSelectedRow() + " ha sido editada");
        }

    }

    class OkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("ok")) {
                Vista ventanaVista = new Vista();
                ventanaVista.setVisible(true);
                ventana.dispose();
            }
        }
    }
}
