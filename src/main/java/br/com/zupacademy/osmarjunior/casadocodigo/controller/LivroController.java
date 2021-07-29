package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.LivroDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.LivroFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroFormRequest livroFormRequest){

        Livro livro = livroFormRequest.convertToModel(autorRepository, categoriaRepository);
        livroRepository.save(livro);
        return ResponseEntity.ok(new LivroDto(livro));
    }
}
