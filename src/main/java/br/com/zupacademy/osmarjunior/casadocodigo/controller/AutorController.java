package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.AutorDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.AutorFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorFormRequest autorFormRequest){
        Autor autor = autorFormRequest.converterToModel();
        autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDto(autor));
    }

}
