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
import java.util.Map;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAnuncioId(@PathVariable(name = "id") Long id) {
        Anuncio anuncio = anuncioService.getById(id);
        if (anuncio != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Anúncio não encontrado!"));
    }

    @GetMapping(value = "/get-por-usuario/{id}")
    public ResponseEntity<Object> getAnuncioByUser(@PathVariable(name = "id") Long id) {
        List<Anuncio> anuncios = anuncioService.getByUser(id);
        if (anuncios != null)
            return ResponseEntity.ok(anuncios);
        return ResponseEntity.badRequest().body(new Erro("Anúncios não encontrados!"));
    }

    @GetMapping(value = "get-anuncios/{filtro}")
    public ResponseEntity<Object> getAnunciosByFilter(@PathVariable(name = "filtro") String filtro) {
        List<Anuncio> anuncios = anuncioService.getByFilter("%" + filtro + "%");

        if (anuncios != null)
            return ResponseEntity.ok(anuncios);
        return ResponseEntity.badRequest().body(new Erro("Anúncios não encontrados!"));
    }

    @PostMapping(value = "add-pergunta/{id}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name = "id") Long idAnuncio, @RequestBody Map dados) {
        if (anuncioService.addPergunta(idAnuncio, (String) dados.get("pergunta")))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar a pergunta!"));
    }

    @PostMapping(value = "add-resposta/{id}/{resposta}")
    public ResponseEntity<Object> addResposta(@PathVariable(name = "id") Long id, @PathVariable(name = "resposta") String resposta) {
        if (anuncioService.addResposta(id, resposta))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar resposta!"));
    }

    @PostMapping("add-foto/{id}")
    public ResponseEntity<Object> addFoto(@PathVariable(name = "id") Long idAnuncio, @RequestBody MultipartFile[] fotos) {
        if (anuncioService.addFoto(fotos, idAnuncio))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar fotos!"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addAnuncio(@RequestPart("anuncio") Anuncio anuncio, @RequestPart("fotos") MultipartFile[] fotos) {
        Anuncio novo = anuncioService.save(anuncio, fotos);
        if (novo != null)
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar anúncio!"));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delAnuncio(@PathVariable Long id) {
        if (anuncioService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao apagar categoria!"));
    }
}
