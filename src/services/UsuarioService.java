package services;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    //Lista que armazena os usuários cadastrados
    private List<Usuario> usuarios;

    //Construtor
    public UsuarioService(){
        usuarios = new ArrayList<>();
    }

    //Casdastrar usuário
    public boolean cadastrarUsuario(Usuario usuario){
        if(buscarPorLogin(usuario.getLogin()) != null){
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    //Buscar usuário pelo Login
    public Usuario buscarPorLogin(String login){
        for (Usuario usuario : usuarios){
            if (usuario.getLogin().equals(login)){
                return usuario;
            }
        }
        return null;
    }

    //Realizar login
    public Usuario realizarLogin(String login, String senha){
        Usuario usuario = buscarPorLogin(login);

        //Usuario não cadastrado
        if(usuario == null){
            return null;
        }

        //Senha incorreta
        if (!usuario.getSenha().equals(senha)){
            return null;
        }

        //Login realizado com sucesso
        return usuario;
    }

    //Listar todos os usuários
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    //Remover usuário
    public boolean removerUsuario(int id){
        for (Usuario usuario : usuarios){
            if (usuario.getId() == id){
                usuarios.remove(usuario);
                return true;
            }
        }
        return false;
    }
}
