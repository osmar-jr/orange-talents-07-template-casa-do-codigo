package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.EstadoDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.EstadoFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.validator.NaoPermiteEstadoDuplicadoComPaisNuloValidator;
import br.com.zupacademy.osmarjunior.casadocodigo.validator.NaoPermiteEstadoDuplicadoEmUmPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    private NaoPermiteEstadoDuplicadoEmUmPaisValidator naoPermiteEstadoDuplicadoEmUmPais;

    @Autowired
    private NaoPermiteEstadoDuplicadoComPaisNuloValidator naoPermiteEstadoDuplicadoComPaisNuloValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermiteEstadoDuplicadoEmUmPais);
        webDataBinder.addValidators(naoPermiteEstadoDuplicadoComPaisNuloValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> cadastrarEstado(@RequestBody @Valid EstadoFormRequest estadoFormRequest){
        Estado estado = estadoFormRequest.converterToModel(estadoRepository, paisRepository);

        estadoRepository.save(estado);
        return ResponseEntity.ok(EstadoDto.converterToDto(estado));
    }
}
