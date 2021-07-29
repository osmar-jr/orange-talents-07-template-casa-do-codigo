package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.DetalheLivroDto;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/detalhar-livro")
public class DetalheLivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharLivro(@PathVariable Long id){
        Optional<Livro> optionalLivro = livroRepository.findById(id);

        if (optionalLivro.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new DetalheLivroDto(optionalLivro.get()));
    }
}
