/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Usuario;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

/**
 *
 * @author jhon
 */
public class AdministrarUsuarios extends javax.swing.JFrame {

    private Usuario usuario;

    /**
     * Creates new form AdministrarUsuarios
     */
    public AdministrarUsuarios() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        vacio = true;
        jPanel1 = new javax.swing.JPanel();
        estamento = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        estamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Empleado", "Profesor" }));
        estamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estamentoActionPerformed(evt);
            }
        });

        jLabel1.setText("ID :");

        jLabel2.setText("Nombre :");

        jLabel3.setText("Correo :");

        jLabel4.setText("Telefono :");

        jLabel5.setText("Estamento :");

        btnGuardar.setText("Guardar");

        btnListar.setText("Listar");
        btnListar.setToolTipText("");

        btnEditar.setText("Editar");

        btnOk.setText("Ok");

        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(estamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCorreo)
                    .addComponent(txtNombre)
                    .addComponent(txtID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar)
                    .addComponent(btnOk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(estamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String [] {
                "ID", "Nombre", "Correo", "Telefono", "Estamento"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getTabla() {
        return jTable1;
    }

    public int getID() {
        return Integer.parseInt(txtID.getText());
    }

    public long getTelefono() {
        return Long.parseLong(txtTelefono.getText());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getCorreo() {
        return txtCorreo.getText();
    }

    public void setID(int ID) {
        txtID.setText(String.valueOf(ID));
       
    }

    public void setIDVacio() {
        txtID.setText("");
    }

    public void setTelefono(long telefono) {
        txtTelefono.setText(String.valueOf(telefono));
        
    }

    public void setTelefonoVacio() {
        txtTelefono.setText("");
    }

    public void setCamposVacios() {
        setTelefonoVacio();
        setIDVacio();
        setCorreo("");
        setNombre("");
        
    }

    public boolean getEstaVacio(){
        return vacio;
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
        
    }

    public void setCorreo(String correo) {
        txtCorreo.setText(correo);
        
    }

    //para no usar todos los setters uno por uno
    public void setDatos(int ID, String nombre, String correo, long telefono) {
        setID(ID);
        setNombre(nombre);
        setCorreo(correo);
        setTelefono(telefono);
        vacio = false;
    }

    public void displayErrorMessage(String erroMessage) {
        JOptionPane.showMessageDialog(this, erroMessage);
    }


    public JComboBox<String> getComboBox() {
        return estamento;
    }

    public void setComboBox(int index) {
        estamento.setSelectedIndex(index);
    }

    public Object[] getDatosUsuario(Usuario usuario) {
        Object[] elemento = {usuario.getID(), usuario.getNombre(), usuario.getCorreo(), usuario.getTelefono(), usuario.getEstamento()};
        return elemento;
    }

    public Object[][] getDatosTabla() {
        return data;
    }

    //toggle para el botón de editar. Si es true, el botón se habilita, sino, se deshabilita
    public void habilitarEditar(boolean toggle) {
        if (toggle == true) {
            btnEditar.setEnabled(true);
        } else {
            btnEditar.setEnabled(false);
        }
    }

    public void deshabilitarGuardar() {
        btnGuardar.setEnabled(false);
    }

    public void habilitarGuardar() {
        btnGuardar.setEnabled(true);
    }

    public void habilitarEliminar(boolean toggle) {
        btnEliminar.setEnabled(toggle);
    }

    public void addDatosTabla(Usuario usuario) {
        // Crear una nueva matriz temporal con una fila adicional
        Object[][] newData = new Object[data.length + 1][];

        // Copiar los elementos existentes de data a newData
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        // Añadir el nuevo elemento a newData
        newData[newData.length - 1] = getDatosUsuario(usuario);

        // Asignar newData a data
        data = newData;

        // Copiar los elementos existentes de data a newData
        //swingutilites para envolver el código de actualización de la interfaz de usuario.
        SwingUtilities.invokeLater(() -> {
            jTable1.setModel(getModeloTabla());
        });

        System.out.println(jTable1.getModel());

    }

    public void editarElementoTabla(int Index, Usuario usuario) {
        Object[] nuevosDatos = getDatosUsuario(usuario);
        data[Index] = nuevosDatos;
        SwingUtilities.invokeLater(() -> {
            jTable1.setModel(getModeloTabla());
        });
    }

    public void eliminarFila(int filaEliminar) {
        
        // Convertir la matriz en una lista de arreglos
        List<Object[]> listaMatriz = new ArrayList<>(Arrays.asList(data));

        // Eliminar la fila especificada
        listaMatriz.remove(filaEliminar);

        // Convertir la lista de arreglos nuevamente en una matriz
        Object[][] nuevaMatriz = new Object[listaMatriz.size()][];
        listaMatriz.toArray(nuevaMatriz);
        
        data = nuevaMatriz;
        
        SwingUtilities.invokeLater(() -> {
            jTable1.setModel(getModeloTabla());
        });
    }

    /**
     * función que define los datos de la tabla y los nombres de cada columna.
     * Después se necesitará el modelo en sí, para recuperar los datos
     * correspondientes de cada fila así que por eso hice la función para
     * obtenerla.
     */
    public DefaultTableModel getModeloTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel(
                data,
                new String[]{
                    "ID", "Nombre", "Correo", "Telefono", "Estamento"
                }
        );
        return modeloTabla;
    }

    private void estamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estamentoActionPerformed

    public void btnGuardarListener(ActionListener listenControles) {
        btnGuardar.addActionListener(listenControles);
    }

    public void btnEditarListener(ActionListener listenControles) {
        btnEditar.addActionListener(listenControles);
    }

    public void btnListarListener(ActionListener listenControles) {
        btnListar.addActionListener(listenControles);
    }
    
    public void btnOkListener(ActionListener listenControles) {
        btnOk.addActionListener(listenControles);
    }

    public void jTableListener(MouseAdapter listenControles) {
        jTable1.addMouseListener(listenControles);
    }

    public void btnEliminarListener(ActionListener listenControles) {
        btnEliminar.addActionListener(listenControles);
    }

    public void addBtnGuardarListener(ActionListener listenControles) {
        btnGuardar.addActionListener(listenControles);
    }

    public void addComboBoxListener(ActionListener listenControles) {
        estamento.addActionListener(listenControles);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarUsuarios().setVisible(true);
            }
        });
    }

    boolean vacio; // verifica si todo está vacio
    private DefaultTableModel model;
    private Object[][] data = {};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> estamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
