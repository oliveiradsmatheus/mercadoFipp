package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;

import java.util.List;

@Service
public class AnuncioService {
    @Autowired
    AnuncioRepository anuncioRepository;

    public List<Anuncio> getAll() {
        return anuncioRepository.findAll();
    }

    public Anuncio getById(long id) {
        return anuncioRepository.findById(id).orElse(null);
    }

    public Anuncio save(Anuncio anuncio) {
        Anuncio novoAnuncio = anuncioRepository.save(anuncio);
        // gravar fotos na tabela
        return novoAnuncio;
    }

    public boolean addPergunta(long id_anuncio, String texto) {
        try {
            anuncioRepository.addPergunta(texto, id_anuncio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addFoto(MultipartFile[] fotos, long id_anuncio) {
        try {
            for (MultipartFile foto : fotos) {
                byte[] bytes = foto.getBytes();
                anuncioRepository.addFoto(bytes, id_anuncio);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(long id) {
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        try {
            anuncioRepository.delete(anuncio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
