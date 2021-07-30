package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.ClienteDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.ClienteFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Cliente;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteFormRequest clienteFormRequest){
        Cliente cliente = clienteFormRequest.converterToModel(entityManager, estadoRepository);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(ClienteDto.toClienteDto(cliente));
    }
}
