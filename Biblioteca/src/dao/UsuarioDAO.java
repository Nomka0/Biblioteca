/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Usuario;

public interface UsuarioDAO {
    
     void crearUsuario(Usuario usuario);
    
     Usuario obtenerUsuario(int index);
   
     List<Usuario> obtenerTodosLosUsuarios();
    
     void actualizarUsuario(int index,Usuario usuario);
    
     void eliminarUsuario(int index);
     
}

