package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario getByNome(String nome) {
        return usuarioRepository.findByName(nome);
    }

    public List<String> getAllNames () {
        List<Usuario> usuarios =  usuarioRepository.findAll();
        List<String> nomes = new ArrayList<>();
        for (Usuario usuario : usuarios)
            nomes.add(usuario.getNome());
        return nomes;
    }

    private Usuario getByNameSenha(String nome, String senha) {
        Usuario usuario = usuarioRepository.findByName(nome);
        if (usuario != null)
            if (usuario.getSenha().equals(senha))
                return usuario;
        return null;
    }

    public String autenticar(String nome, String senha) {
        Usuario consultado = getByNameSenha(nome, senha);
        if (consultado != null)
            return JWTTokenProvider.getToken(consultado.getNome(), consultado.getId(), consultado.getNome(), "" + consultado.getNivel());
        return null;
    }

    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        try {
            usuarioRepository.delete(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
