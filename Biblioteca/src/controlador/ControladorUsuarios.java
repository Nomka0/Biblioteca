/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AdministrarUsuarios;
import modelo.Usuario;

/**
 *
 * @author jhon
 */
public class ControladorUsuarios {
    private AdministrarUsuarios ventana;
    private Usuario usuario;
    
    public ControladorUsuarios(AdministrarUsuarios ventana, Usuario usuario) {
        this.ventana = ventana;
        this.usuario = usuario;
        
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        
        this.ventana.btnGuardarListener(new GuardarListener());
    }
    
    class GuardarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.print("guardado");           
        }
        
        
    }
}
