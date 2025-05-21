package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.services.FotoService;

@CrossOrigin
@RestController
@RequestMapping(value = "apis/foto")
public class FotoRestController {
    @Autowired
    private FotoService fotoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getImagem(@PathVariable(value = "id") int id) {
        Foto foto = fotoService.getById(id);
        byte[] imgBytes = foto.getArquivo();

        if (imgBytes != null && imgBytes.length > 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDisposition(ContentDisposition.inline()
                    .filename("imagem_" + id + ".jpg")
                    .build());
            return new ResponseEntity<>(imgBytes, headers, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new Erro("Erro ao recuperar imagem!"));
    }
}
