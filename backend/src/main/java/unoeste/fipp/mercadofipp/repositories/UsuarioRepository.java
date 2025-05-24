package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuario WHERE usr_name = :nome", nativeQuery = true)
    public Usuario findByName(
            @Param("nome") String nome
    );


}
