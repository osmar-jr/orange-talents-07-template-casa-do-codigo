package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.form.CategoriaFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrarCategoria(@RequestBody @Valid CategoriaFormRequest categoriaFormRequest) {
        Categoria categoria = categoriaFormRequest.converterToModel();

        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria.toString());

    }
}
