/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.PrestamoDAOImpl;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdministrarUsuarios;
import vista.Vista;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.Prestamo;
import vista.AdministrarPrestamos;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;

/**
 *
 * @author jhon
 */
public class ControladorPrestamos {
    private AdministrarPrestamos ventana;
    private PrestamoDAOImpl prestamoDao;
    private Prestamo prestamo;
    private String rutaArchivo, rutaArchivoRecursos, rutaArchivoUsuarios;
    public String opcionSeleccionada;
    private List<Prestamo> datosTabla;
    private List<Prestamo> datosTablaCopia;// para que no se repitan datos
    private int contadorDatos;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private PrestamoDAOImpl prestamoDaoPers;
    private PrestamoDAOImpl prestamosTotalesDAO;
    private List<Prestamo> prestamosTotales;// Aquí se almacenarán los persistentes, y los que se crean en la sesión,
                                            // para manejarlos todos
    private int filaSeleccionada;
    private ArrayList<Integer> datosAEliminar;
    private ArrayList<Integer> indexadorTabla;// tendrá los indices actualizados de la tabla en una lista, para después
                                              // no borrar cosas que no se supone debían ser borradas
    private int contadorTotal;
    private int contadorLista;
    private String diaReg, mesReg, anioReg, diaDev, mesDev, anioDev, diaLim, mesLim, anioLim;

    public ControladorPrestamos(AdministrarPrestamos ventana, Prestamo prestamo) {
        this.ventana = ventana;
        this.prestamo = prestamo;
        prestamoDao = new PrestamoDAOImpl();
        opcionSeleccionada = "Abierto";
        contadorDatos = 0;
        tabla = ventana.getTabla();
        modeloTabla = ventana.getModeloTabla();
        prestamosTotalesDAO = new PrestamoDAOImpl();
        prestamosTotales = prestamosTotalesDAO.obtenerTodosLosPrestamos();
        rutaArchivo = "src/txt/TablaPrestamos.txt"; // archivo de texto con el id del usuario
        rutaArchivoRecursos = "src/txt/TablaRecursos.txt";
        rutaArchivoUsuarios = "src/txt/TablaUsuarios.txt";
        datosAEliminar = new ArrayList<>();
        indexadorTabla = new ArrayList<>();
        prestamoDaoPers = new PrestamoDAOImpl();

        diaReg = "1";
        mesReg = "1";
        anioReg = "2023";

        ventana.habilitarEditar(false);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        ventana.jTableListener(new ControladorPrestamos.ManejadoraDeMouse());
        ventana.btnListarListener(new ControladorPrestamos.ListarListener());
        ventana.btnGuardarListener(new ControladorPrestamos.GuardarListener());
        ventana.btnEditarListener(new ControladorPrestamos.EditarListener());

        ventana.addComboBoxListener(new ControladorPrestamos.ComboBoxListener());
        ventana.addRegistroListener(new ControladorPrestamos.ComboBoxListener());
        ventana.addLimiteListener(new ControladorPrestamos.ComboBoxListener());
        ventana.addDevolucionListener(new ControladorPrestamos.ComboBoxListener());

        ventana.btnEliminarListener(new ControladorPrestamos.EliminarListener());
        ventana.btnOkListener(new OkListener());
    }

    public void eliminarPrestamosTotales() {
        // prestamosTotalesDAO.obtenerTodosLosUsuarios() =
        // prestamosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
        sort(datosAEliminar);
        reverse(datosAEliminar);
        for (int i = 0; i < datosAEliminar.size(); i++) {
            prestamosTotalesDAO.eliminarPrestamo(datosAEliminar.get(i));
        }
        // prestamosTotalesDAO.obtenerTodosLosUsuarios() =
        // prestamosTotalesDAO.obtenerTodosLosUsuarios()DAO.obtenerTodosLosUsuarios();
    }

    public boolean compararIDsRecursos(int recursoID) {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivoRecursos))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(", ");
                int numeroEnLinea = Integer.parseInt(valores[0].trim());
                if (recursoID == numeroEnLinea) {
                    return true; // El número se encuentra en el archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El número no se encuentra en el archivo
    }

    public boolean compararIDsUsuarios(int usuarioID) {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivoUsuarios))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(", ");
                int numeroEnLinea = Integer.parseInt(valores[0].trim());
                if (usuarioID == numeroEnLinea) {
                    return true; // El número se encuentra en el archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El número no se encuentra en el archivo
    }

    public boolean recursoOcupado(int recursoID) {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(", ");
                int numeroEnLinea = Integer.parseInt(valores[2].trim());
                if (recursoID == numeroEnLinea) {
                    return true; // El número se encuentra en el archivo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El número no se encuentra en el archivo
    }


    public void GuardarEnArchivo() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {

            try {// lista datos
                if(compararIDsUsuarios(ventana.getIDUsuario()) == false && compararIDsRecursos(ventana.getIDRecurso()) == false){
                    ventana.displayErrorMessage("!El Usuario no existe!");
                    ventana.displayErrorMessage("!El Recurso no existe!");
                    } else if(compararIDsUsuarios(ventana.getIDUsuario()) == false){
                        ventana.displayErrorMessage("!El Usuario no existe!");
                    } else if(compararIDsRecursos(ventana.getIDRecurso()) == false){
                        ventana.displayErrorMessage("!El Recurso no existe!");
                    } else if(recursoOcupado(ventana.getIDRecurso()) == true){
                        ventana.displayErrorMessage("!El Recurso ya está en prestamo por alguien más!");
                    } else datosALista();
                    
            } catch (NumberFormatException ex) {
                ventana.displayErrorMessage("¡Revisa los datos ingresados!");
            }

            eliminarPrestamosTotales();
            eliminarDatosEnArchivo();
            System.out.println("prestamos totales: " + prestamosTotalesDAO.obtenerTodosLosPrestamos());
            for (Prestamo prestamo : prestamosTotalesDAO.obtenerTodosLosPrestamos()) {
                int ID = prestamo.getID();
                int idUsuario = prestamo.getIDusuario();
                int idRecurso = prestamo.getIDrecurso();
                String estado = prestamo.getEstado();
                String fRegistro = prestamo.getFechaRegistro();
                String fLimite = prestamo.getFechaLimite();
                String fDevolucion = prestamo.getFechaDevolucion();
                Object[] datos = { ID, idUsuario, idRecurso, estado, fRegistro, fLimite, fDevolucion };
                String datosString = Arrays.toString(datos);
                String datosStringRecortado = datosString.substring(1, datosString.length() - 1);
                escritor.write(datosStringRecortado);
                escritor.newLine();
                // contadorDatos++;
            }
            // else ventana.displayErrorMessage("Error: ¡No hay ningún usuario para
            // guardar!");
            contadorLista = prestamosTotalesDAO.obtenerTodosLosPrestamos().size() - 1;
            datosAEliminar.clear();
            reiniciarIndexador();
            // ventana.deshabilitarGuardar();
            // postGuardar();
            ventana.displayErrorMessage("Guardado");

            // }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
        }

    }

    // reinicia indexador tabla, para volver a almacenar las filas que se quedan en
    // la tabla. Esto es para cuando empieza la ventana, o cuando se guardan datos
    public void reiniciarIndexador() {
        contadorTotal = prestamoDaoPers.obtenerTodosLosPrestamos().size();
        indexadorTabla.clear();
        if (contadorTotal == 1) {
            indexadorTabla.add(0);
        } else {
            for (int i = 0; i < contadorTotal; i++) {
                indexadorTabla.add(i);
            }
        }

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

    class GuardarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("guardar")) {
                GuardarEnArchivo();
            }
        }
    }

    public void leerArchivos() {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String lineaArchivo;
            try {

                while ((lineaArchivo = lector.readLine()) != null) {

                    String[] datosReconstruidos = lineaArchivo.split(", ");
                    Prestamo prestamoNuevo;
                    int id;
                    int idusuario;
                    int idrecurso;
                    String estado;
                    String diaFechar;
                    String diaFechal;
                    String diaFechad;

                    prestamoNuevo = new Prestamo();

                    id = Integer.parseInt(datosReconstruidos[0]);
                    idusuario = Integer.parseInt(datosReconstruidos[1]);
                    idrecurso = Integer.parseInt(datosReconstruidos[2]);
                    estado = datosReconstruidos[3];
                    diaFechar = datosReconstruidos[4];
                    String mesFechar = datosReconstruidos[5];
                    String anioFechar = datosReconstruidos[6];
                    diaFechal = datosReconstruidos[7];
                    String mesFechal = datosReconstruidos[8];
                    String anioFechal = datosReconstruidos[9];
                    diaFechad = datosReconstruidos[10];
                    String mesFechad = datosReconstruidos[11];
                    String anioFechad = datosReconstruidos[12];

                    prestamoNuevo.setID(id);
                    prestamoNuevo.setIDusuario(idusuario);
                    prestamoNuevo.setIDrecurso(idrecurso);
                    prestamoNuevo.setEstado(estado);
                    prestamoNuevo.setFechaRegistro(diaFechar, mesFechar, anioFechar);
                    prestamoNuevo.setFechaLimite(diaFechal, mesFechal, anioFechal);
                    prestamoNuevo.setFechaDevolucion(diaFechad, mesFechad, anioFechad);
                    prestamoDaoPers.crearPrestamo(prestamoNuevo);
                    System.out.println(prestamo.getIDusuario());
                }
                datosPersistentes(prestamoDaoPers);
                prestamosTotalesDAO.obtenerTodosLosPrestamos().addAll(prestamoDaoPers.obtenerTodosLosPrestamos());
                contadorTotal = prestamoDaoPers.obtenerTodosLosPrestamos().size();
                reiniciarIndexador();
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("no se encontró el archivo.");
        }
    }

    public void datosPersistentes(PrestamoDAOImpl prestamo) {
        for (Prestamo prestamos : prestamo.obtenerTodosLosPrestamos()) {
            ventana.addDatosTabla(prestamos);
        }
    }

    public void nuevosDatos() {
        datosTablaCopia = datosTabla.subList(contadorDatos, datosTabla.size());
    }

    public String mostrarDatos() {
        int filaSeleccionada = tabla.getSelectedRow();
        // int columnaSeleccionada = tabla.getSelectedColumn();

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

    public void rellenarDatos() {
        String datosARellenar = mostrarDatos();
        String[] splitDatos = datosARellenar.split(", ");
        int id = Integer.parseInt(splitDatos[0]);
        int idusuario = Integer.parseInt(splitDatos[1]);
        int idrecurso = Integer.parseInt(splitDatos[2]);
        String estado = splitDatos[3];
        int diaFechar = Integer.parseInt(splitDatos[4]);
        int mesFechar = Integer.parseInt(splitDatos[5]);
        int anioFechar = Integer.parseInt(splitDatos[6]);
        int diaFechal = Integer.parseInt(splitDatos[7]);
        int mesFechal = Integer.parseInt(splitDatos[8]);
        int anioFechal = Integer.parseInt(splitDatos[9]);
        int diaFechad = Integer.parseInt(splitDatos[10]);
        int mesFechad = Integer.parseInt(splitDatos[11]);
        int anioFechad = Integer.parseInt(splitDatos[12]);

        ventana.setDatos(id, idusuario, idrecurso, diaFechar, mesFechar, anioFechar, diaFechal, mesFechal, anioFechal,
                diaFechad, mesFechad, anioFechad);
        cambioComboBox(estado);
    }

    public void cambioComboBox(String tipo) {
        switch (tipo) {
            case "Abierto":
                ventana.setComboBox(0);
                break;
            case "Parcialmente Cerrado":
                ventana.setComboBox(1);
                break;
            case "Completamente Cerrado":
                ventana.setComboBox(2);
                break;
        }
    }

    public void datosALista() {
        Prestamo prestamoNuevo;
        int id;
        int idusuario;
        int idrecurso;
        String estado;

        prestamoNuevo = new Prestamo();
        id = ventana.getID();
        idusuario = ventana.getIDUsuario();
        idrecurso = ventana.getIDRecurso();
        estado = opcionSeleccionada;
        String diaFechar = ventana.getDiaReg();
        String mesFechar = ventana.getMesReg();
        String anioFechar = ventana.getAnioReg();
        String diaFechal = ventana.getDiaLim();
        String mesFechal = ventana.getMesLim();
        String anioFechal = ventana.getAnioLim();
        String diaFechad = ventana.getDiaDev();
        String mesFechad = ventana.getMesDev();
        String anioFechad = ventana.getAnioDev();

        prestamoNuevo.setID(id);
        prestamoNuevo.setIDusuario(idusuario);
        prestamoNuevo.setIDrecurso(idrecurso);
        prestamoNuevo.setEstado(estado);
        prestamoNuevo.setFechaRegistro(diaFechar, mesFechar, anioFechar);
        prestamoNuevo.setFechaLimite(diaFechal, mesFechal, anioFechal);
        prestamoNuevo.setFechaDevolucion(diaFechad, mesFechad, anioFechad);
        prestamoDao.crearPrestamo(prestamoNuevo);
        System.out.println(prestamoDao.obtenerTodosLosPrestamos());
        ventana.addDatosTabla(prestamoNuevo);
        // nuevosDatos();
        ventana.setCamposVacios();
        // GuardarEnArchivo();
        contadorTotal++;
        contadorDatos++;
        contadorLista = prestamosTotalesDAO.obtenerTodosLosPrestamos().size() - 1;
        contadorLista++;

        indexadorTabla.add(contadorLista);
        // vista.setArea(modelo.getArea());
        // vista.activarControles(false);
        ventana.habilitarGuardar();

        System.out.println("indexador tabla: " + indexadorTabla);
        System.out.println("usuarios Dao: " + prestamoDao.obtenerTodosLosPrestamos());
        prestamosTotalesDAO.obtenerTodosLosPrestamos()
                .add(prestamoDao.obtenerTodosLosPrestamos().get(contadorDatos - 1));
    }

    class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            opcionSeleccionada = (String) ventana.getComboBox().getSelectedItem();
            diaReg = (String) ventana.getDiaReg();
            mesReg = (String) ventana.getMesReg();
            anioReg = (String) ventana.getAnioReg();

            diaLim = (String) ventana.getDiaLim();
            mesLim = (String) ventana.getMesLim();
            anioLim = (String) ventana.getAnioLim();

            diaDev = (String) ventana.getDiaDev();
            mesDev = (String) ventana.getMesDev();
            anioDev = (String) ventana.getAnioDev();
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
            System.out.println("Estos son los usuarios totales: " + prestamosTotalesDAO.obtenerTodosLosPrestamos());
            System.out.println("contador lista: " + contadorTotal);
            System.out.println(prestamoDaoPers.obtenerTodosLosPrestamos());
        }
    }

    class ListarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("listar")) {
                try {
                    if(compararIDsUsuarios(ventana.getIDUsuario()) == false && compararIDsRecursos(ventana.getIDRecurso()) == false){
                        ventana.displayErrorMessage("!El Usuario no existe!");
                        ventana.displayErrorMessage("!El Recurso no existe!");
                        } else if(compararIDsUsuarios(ventana.getIDUsuario()) == false){
                            ventana.displayErrorMessage("!El Usuario no existe!");
                        } else if(compararIDsRecursos(ventana.getIDRecurso()) == false){
                            ventana.displayErrorMessage("!El Recurso no existe!");
                        } else if(recursoOcupado(ventana.getIDRecurso()) == true){
                            ventana.displayErrorMessage("!El Recurso ya está en prestamo por alguien más!");
                        } else datosALista();
                              
                } catch (NumberFormatException ex) {
                    ventana.displayErrorMessage("Error: ¡Revisa los datos ingresados!");
                }

            }
            // System.out.print("guardado");
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
            contadorLista = prestamosTotalesDAO.obtenerTodosLosPrestamos().size() - 1;

            System.out.println("indexador tabla: " + indexadorTabla);
            System.out.println("datos A Eliminar: " + datosAEliminar);
            System.out.println("usuarios totales: " + prestamosTotalesDAO.obtenerTodosLosPrestamos());
            ventana.habilitarGuardar();
            ventana.setCamposVacios();
            ventana.habilitarEliminar(false);
            ventana.habilitarEditar(false);
        }
    }

    public void editarElementos(int index) {
        Prestamo prestamoEditado = new Prestamo();
        int id = ventana.getID();
        int idusuario = ventana.getIDUsuario();
        int idrecurso = ventana.getIDRecurso();
        String estado = opcionSeleccionada;
        String diaFechar = ventana.getDiaReg();
        String mesFechar = ventana.getMesReg();
        String anioFechar = ventana.getAnioReg();
        String diaFechal = ventana.getDiaLim();
        String mesFechal = ventana.getMesLim();
        String anioFechal = ventana.getAnioLim();
        String diaFechad = ventana.getDiaDev();
        String mesFechad = ventana.getMesDev();
        String anioFechad = ventana.getAnioDev();

        prestamoEditado.setID(id);
        prestamoEditado.setIDusuario(idusuario);
        prestamoEditado.setIDrecurso(idrecurso);
        prestamoEditado.setEstado(estado);
        prestamoEditado.setFechaRegistro(diaFechar, mesFechar, anioFechar);
        prestamoEditado.setFechaLimite(diaFechal, mesFechal, anioFechal);
        prestamoEditado.setFechaDevolucion(diaFechad, mesFechad, anioFechad);

        ventana.editarElementoTabla(index, prestamoEditado);
        prestamosTotalesDAO.actualizarPrestamo(index, prestamoEditado);
    }

    class EditarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("editar")) {
                try {
                    int index = tabla.getSelectedRow(); // la fila a cambiar los datos
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
            // System.out.print("guardado");
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
