/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    private List<Usuario> usuarios;

    public UsuarioDAOImpl() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario obtenerUsuario(int index) {
        return usuarios.get(index);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }
    
    @Override 
    public void actualizarUsuario(int index, Usuario usuarioAct){
        usuarios.set(index, usuarioAct);
    }

    @Override
    public void eliminarUsuario(int index) {
        usuarios.remove(index);
    }
}
