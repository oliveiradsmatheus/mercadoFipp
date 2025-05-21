package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "apis/anuncio")
public class AnuncioRestController {
    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Anuncio> anuncios = anuncioService.getAll();
        if (anuncios != null && !anuncios.isEmpty())
            return ResponseEntity.ok(anuncios);
        return ResponseEntity.badRequest().body(new Erro("Anúncios não encontrados!"));
    }

    @GetMapping(value = "add-pergunta/{id}/{texto}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name = "id") Long idAnuncio, @PathVariable(name = "texto") String texto) {
        if (anuncioService.addPergunta(idAnuncio, texto))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar a pergunta!"));
    }

    @PostMapping("add-foto/{id}")
    public ResponseEntity<Object> addFoto(@PathVariable(name = "id") Long idAnuncio, @RequestBody MultipartFile[] fotos) {
        if (anuncioService.addFoto(fotos, idAnuncio))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar fotos!"));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAnuncioId(@PathVariable(name = "id") Long id) {
        Anuncio anuncio = anuncioService.getById(id);
        if (anuncio != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Anúncio não encontrado!"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addAnuncio(@RequestPart ("anuncio") Anuncio anuncio, @RequestPart ("fotos") MultipartFile[] fotos) {
        Anuncio novo = anuncioService.save(anuncio, fotos);
        if (novo != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar anúncio!"));
    }

    /*@PutMapping
    public ResponseEntity<Object> updAnuncio(@RequestBody Anuncio anuncio) {
        Anuncio novo = anuncioService.save(anuncio);
        if (novo != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar anúncio!"));
    }*/
}
