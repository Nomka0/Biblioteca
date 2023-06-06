/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdministrarRecursos;
import modelo.Recurso;
import dao.RecursoDAO;
import dao.RecursoDAOImpl;
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
public class ControladorRecursos {

    private AdministrarRecursos ventana;
    private RecursoDAOImpl recursoDao;
    private Recurso recurso;
    private String rutaArchivo;
    public String opcionSeleccionada;
    private int contadorDatos;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private RecursoDAOImpl recursoDaoPers;
    private RecursoDAOImpl recursosTotalesDAO;
    //private List<Recurso> recursosTotales;//Aquí se almacenarán los persistentes, y los que se crean en la sesión, para manejarlos todos
    private int filaSeleccionada;
    private ArrayList<Integer> datosAEliminar;
    private ArrayList<Integer> indexadorTabla;//tendrá los indices actualizados de la tabla en una lista, para después no borrar cosas que no se supone debían ser borradas
    private int contadorTotal;
    private int contadorLista;

    public ControladorRecursos(AdministrarRecursos ventana, Recurso recurso) {
        this.ventana = ventana;
        this.recurso = recurso;
        recursoDao = new RecursoDAOImpl();
        opcionSeleccionada = "Estudiante";
        contadorDatos = 0;
        tabla = ventana.getTabla();
        modeloTabla = ventana.getModeloTabla();
        recursosTotalesDAO = new RecursoDAOImpl();
        //recursosTotales = recursosTotalesDAO.obtenerTodosLosRecursos();
        rutaArchivo = "src/txt/TablaRecursos.txt"; //archivo de texto con el id del recurso
        datosAEliminar = new ArrayList<>();
        indexadorTabla = new ArrayList<>();
        recursoDaoPers = new RecursoDAOImpl();

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

    public void eliminarRecursosTotales() {
        //recursosTotalesDAO.obtenerTodosLosRecursos() = recursosTotalesDAO.obtenerTodosLosRecursos()DAO.obtenerTodosLosRecursos();
        sort(datosAEliminar);
        reverse(datosAEliminar);
        for (int i = 0; i < datosAEliminar.size(); i++) {
            recursosTotalesDAO.eliminarRecurso(datosAEliminar.get(i));
        }
        //recursosTotalesDAO.obtenerTodosLosRecursos() = recursosTotalesDAO.obtenerTodosLosRecursos()DAO.obtenerTodosLosRecursos();
    }

    public void GuardarEnArchivo() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {

            try {//lista datos
                if(ventana.getEstaVacio() == false){
                    datosALista();
                }
            } catch (NumberFormatException ex) {
                ventana.displayErrorMessage("¡Revisa los datos ingresados!");
            }

            eliminarRecursosTotales();
            eliminarDatosEnArchivo();
            System.out.println("recursos totales: " + recursosTotalesDAO.obtenerTodosLosRecursos());
            for (Recurso recursos : recursosTotalesDAO.obtenerTodosLosRecursos()) {
                int ID = recursos.getID();
                String titulo = recursos.getTitulo();
                String autor = recursos.getAutor();
                String genero = recursos.getGenero();
                String tipoRecurso = recursos.getTipoRecurso();
                Object[] datos = {ID, titulo, autor, genero, tipoRecurso};
                String datosString = Arrays.toString(datos);
                String datosStringRecortado = datosString.substring(1, datosString.length() - 1);
                escritor.write(datosStringRecortado);
                escritor.newLine();
                //contadorDatos++;
            }
            //else ventana.displayErrorMessage("Error: ¡No hay ningún recurso para guardar!");
            contadorLista = recursosTotalesDAO.obtenerTodosLosRecursos().size() - 1;
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
                    Recurso recursoNuevo;
                    int id;
                    String titulo;
                    String autor;
                    String genero;
                    String tipoRecurso;

                    recursoNuevo = new Recurso();

                    id = Integer.parseInt(datosReconstruidos[0]);
                    titulo = datosReconstruidos[1];
                    autor = datosReconstruidos[2];
                    genero = datosReconstruidos[3];
                    tipoRecurso = datosReconstruidos[4];

                    recursoNuevo.setID(id);
                    recursoNuevo.setTitulo(titulo);
                    recursoNuevo.setAutor(autor);
                    recursoNuevo.setGenero(genero);
                    recursoNuevo.setTipoRecurso(tipoRecurso);
                    recursoDaoPers.crearRecurso(recursoNuevo);
                    System.out.println(recursoNuevo.getAutor());
                }

                datosPersistentes(recursoDaoPers);
                recursosTotalesDAO.obtenerTodosLosRecursos().addAll(recursoDaoPers.obtenerTodosLosRecursos());
                contadorTotal = recursoDaoPers.obtenerTodosLosRecursos().size();

                reiniciarIndexador();

            } catch (IOException ex) {
                Logger.getLogger(ControladorRecursos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorRecursos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se encontró el archivo.");
        }
    }

    //reinicia indexador tabla, para volver a almacenar las filas que se quedan en la tabla. Esto es para cuando empieza la ventana, o cuando se guardan datos
    public void reiniciarIndexador() {
        contadorTotal = recursoDaoPers.obtenerTodosLosRecursos().size();
        indexadorTabla.clear();
        if (contadorTotal == 1) {
            indexadorTabla.add(0);
        } else {
            for (int i = 0; i < contadorTotal; i++) {
                indexadorTabla.add(i);
            }
        }

    }

    public void datosPersistentes(RecursoDAOImpl recurso) {
        for (Recurso recursos : recurso.obtenerTodosLosRecursos()) {
            ventana.addDatosTabla(recursos);
        }
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
        String titulo = splitDatos[1];
        String autor = splitDatos[2];
        String genero = splitDatos[3];
        String tipoRecurso = splitDatos[4];

        ventana.setDatos(id, titulo, autor, genero);
        cambioComboBox(tipoRecurso);
    }

    public void eliminarDatosEnArchivo() {
        File archivo = new File(rutaArchivo);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(archivo));
            writer.write("");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioComboBox(String tipo) {
        switch (tipo) {
            case "Libro":
                ventana.setComboBox(0);
                break;
            case "Revista":
                ventana.setComboBox(1);
                break;
            case "Comic":
                ventana.setComboBox(2);
                break;
        }
    }

    public void datosALista() {
        Recurso recursoNuevo;
        int id;
        String titulo;
        String autor;
        String genero;
        String tipoRecurso;

        recursoNuevo = new Recurso();
        id = ventana.getID();
        titulo = ventana.getTitulo();
        autor = ventana.getAutor();
        genero = ventana.getGenero();
        tipoRecurso = opcionSeleccionada;

        recursoNuevo.setID(id);
        recursoNuevo.setTitulo(titulo);
        recursoNuevo.setAutor(autor);
        recursoNuevo.setGenero(genero);
        recursoNuevo.setTipoRecurso(tipoRecurso);
        recursoDao.crearRecurso(recursoNuevo);//añade el recurso con todos sus atributos a una lista de la implementación de la interfaz DAO de recurso
        System.out.println(recursoDao.obtenerTodosLosRecursos());
        ventana.addDatosTabla(recursoNuevo);
        //nuevosDatos();
        ventana.setCamposVacios();
        //GuardarEnArchivo();
        contadorTotal++;
        contadorDatos++;
        contadorLista = recursosTotalesDAO.obtenerTodosLosRecursos().size() - 1;
        contadorLista++;

        indexadorTabla.add(contadorLista);
        //vista.setArea(modelo.getArea());
        //vista.activarControles(false);
        ventana.habilitarGuardar();

        System.out.println("indexador tabla: " + indexadorTabla);
        System.out.println("recursos Dao: " + recursoDao.obtenerTodosLosRecursos());
        recursosTotalesDAO.obtenerTodosLosRecursos().add(recursoDao.obtenerTodosLosRecursos().get(contadorDatos - 1));
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
            System.out.println("Estos son los recursos totales: " + recursosTotalesDAO.obtenerTodosLosRecursos());
            System.out.println("contador lista: " + contadorTotal);
            System.out.println(recursoDaoPers.obtenerTodosLosRecursos());
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
            contadorLista = recursosTotalesDAO.obtenerTodosLosRecursos().size() - 1;

            System.out.println("indexador tabla: " + indexadorTabla);
            System.out.println("datos A Eliminar: " + datosAEliminar);
            System.out.println("recursos totales: " + recursosTotalesDAO.obtenerTodosLosRecursos());
            ventana.habilitarGuardar();
            ventana.setCamposVacios();
            ventana.habilitarEliminar(false);
            ventana.habilitarEditar(false);
        }
    }

    public void editarElementos(int index) {
        Recurso recursoEditado = new Recurso();
        int id = ventana.getID();
        String titulo = ventana.getTitulo();
        String autor = ventana.getAutor();
        String genero = ventana.getGenero();
        String tipoRecurso = opcionSeleccionada;

        recursoEditado.setID(id);
        recursoEditado.setTitulo(titulo);
        recursoEditado.setAutor(autor);
        recursoEditado.setGenero(genero);
        recursoEditado.setTipoRecurso(tipoRecurso);

        ventana.editarElementoTabla(index, recursoEditado);
        recursosTotalesDAO.actualizarRecurso(index, recursoEditado);
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