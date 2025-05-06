package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping(value = "apis/categoria")
public class CategoriaRestController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Categoria> categorias = categoriaService.getAll();
        if (categorias != null && !categorias.isEmpty())
            return ResponseEntity.ok(categorias);
        return ResponseEntity.badRequest().body(new Erro("Categorias não encontradas"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoriaId(@PathVariable(name = "id") Long id) {
        Categoria categoria = categoriaService.getById(id);
        if (categoria != null)
            return ResponseEntity.ok(categoria);
        return ResponseEntity.badRequest().body(new Erro("Categoria não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Object> addCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.save(categoria);
        if (novaCategoria != null)
            return ResponseEntity.ok(novaCategoria);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar categoria"));
    }

    @PutMapping
    public ResponseEntity<Object> updCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.save(categoria);
        if (novaCategoria != null)
            return ResponseEntity.ok(novaCategoria);
        return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar categoria"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delCategoria(@PathVariable Long id) {
        if (categoriaService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao apagar categoria"));
    }
}
