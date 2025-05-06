package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "foto_anuncio")
public class Foto {
    @Id
    @Column(name = "fot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fot_file")
    private byte[] arquivo;
    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    public Foto() {
        this(0L, null, null);
    }

    public Foto(Long id, byte[] arquivo, Anuncio anuncio) {
        this.id = id;
        this.arquivo = arquivo;
        this.anuncio = anuncio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
