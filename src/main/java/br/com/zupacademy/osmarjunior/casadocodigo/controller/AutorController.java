package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.AutorDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.AutorFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.validator.NaoPermiteEmailDuplicadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private NaoPermiteEmailDuplicadoValidator naoPermiteEmailDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermiteEmailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorFormRequest autorFormRequest){
        Autor autor = autorFormRequest.converterToModel();
        autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDto(autor));
    }

}
