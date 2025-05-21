package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "foto_anuncio")
public class Foto {
    @Id
    @Column(name = "fot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fot_file")
    private byte[] arquivo;
    @Transient // Atributo não será persistido
    private String img64;
    @Column(name = "fot_ext")
    private String extensao;
    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    public Foto() {
        this(0L, null, null, null);
    }

    public Foto(Long id, byte[] arquivo, String extensao, Anuncio anuncio) {
        this.id = id;
        this.arquivo = arquivo;
        this.anuncio = anuncio;
        this.extensao = extensao;
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

    public String getImg64() {
        if (arquivo != null && arquivo.length > 0 && extensao != null) {
            return "data:" + extensao + ";base64," + Base64.getEncoder().encodeToString(arquivo);
        }
        return null;
    }

    public void setImg64(String img64) {
        this.img64 = img64;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
}
