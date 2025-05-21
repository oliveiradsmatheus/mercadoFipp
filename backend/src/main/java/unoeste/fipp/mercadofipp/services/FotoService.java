package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.repositories.FotoRepository;

@Service
public class FotoService {
    @Autowired
    FotoRepository fotoRepository;

    public Foto getById(long id) {
        return fotoRepository.findById(id).orElse(null);
    }
}
