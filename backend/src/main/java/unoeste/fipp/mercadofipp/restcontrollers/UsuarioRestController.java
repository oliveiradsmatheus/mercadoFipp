package unoeste.fipp.mercadofipp.restcontrollers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "apis/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios != null && !usuarios.isEmpty())
            return ResponseEntity.ok(usuarios);
        return ResponseEntity.badRequest().body(new Erro("Usuários não encontrados!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario != null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("Usuário não encontrado!"));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "nome") String nome) {
        Usuario usuario = usuarioService.getByNome(nome);
        if (usuario != null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("Não foi possível recuperar o Usuário com esse nome!!"));
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar usuário!"));
    }

    @PostMapping("/logar")
    public ResponseEntity<Object> logar(@RequestParam String nome, @RequestParam String senha) {
        String token = usuarioService.autenticar(nome, senha);
        Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
        Map<String, Object> json = new HashMap<>();
        json.put("token", token);
        json.put("nivel", claims.get("nivel"));
        if (token != null) {
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.badRequest().body(new Erro("Não foi possível logar no sistema!!"));
    }

    @PutMapping
    public ResponseEntity<Object> updUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(usuario);
        return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar usuário!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id) {
        if (usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao apagar usuário!"));
    }
}
