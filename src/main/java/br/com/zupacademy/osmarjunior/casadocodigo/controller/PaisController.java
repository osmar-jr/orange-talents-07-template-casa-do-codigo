package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.PaisDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.PaisFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PaisDto> cadastrarPais(@RequestBody @Valid PaisFormRequest paisFormRequest){
        Pais pais = paisFormRequest.converterToModel();

        paisRepository.save(pais);
        return ResponseEntity.ok(new PaisDto(pais));
    }
}
