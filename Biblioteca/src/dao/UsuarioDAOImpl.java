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
    public Usuario obtenerUsuarioPorID(int ID) {
        for (Usuario usuario : usuarios) {
            if (usuario.getID() == ID) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getID() == usuario.getID()) {
                u.setNombre(usuario.getNombre());
                u.setCorreo(usuario.getCorreo());
                u.setTelefono(usuario.getTelefono());
                u.setEstamento(usuario.getEstamento());
                break;
            }
        }
    }

    @Override
    public void eliminarUsuario(int ID) {
        usuarios.removeIf(usuario -> usuario.getID() == ID);
    }
}
