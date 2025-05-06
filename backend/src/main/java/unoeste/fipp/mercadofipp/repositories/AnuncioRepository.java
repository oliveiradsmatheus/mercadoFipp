package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pergunta_anuncio (per_text, anu_id) VALUES (:texto, :id_anuncio)", nativeQuery = true)
    public void addPergunta(@Param("texto") String texto, @Param("id_anuncio") Long id_anuncio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO foto_anuncio (fot_file, anu_id) VALUES (:foto, :id_anuncio)", nativeQuery = true)
    public void addFoto(@Param("foto") byte[] foto, @Param("id_anuncio") Long id_anuncio);
}
