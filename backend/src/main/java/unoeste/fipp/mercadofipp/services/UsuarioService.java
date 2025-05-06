package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;

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
