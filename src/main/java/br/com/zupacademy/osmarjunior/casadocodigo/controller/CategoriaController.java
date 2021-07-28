package br.com.zupacademy.osmarjunior.casadocodigo.controller;

import br.com.zupacademy.osmarjunior.casadocodigo.dto.CategoriaDto;
import br.com.zupacademy.osmarjunior.casadocodigo.form.CategoriaFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.validator.NaoPermiteNomeDeCategoriaDuplicadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private NaoPermiteNomeDeCategoriaDuplicadoValidator naoPermiteNomeDeCategoriaDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(naoPermiteNomeDeCategoriaDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrarCategoria(@RequestBody @Valid CategoriaFormRequest categoriaFormRequest){
        Categoria categoria = categoriaFormRequest.converterToModel();

        categoriaRepository.save(categoria);
        CategoriaDto categoriaDto = new CategoriaDto(categoriaFormRequest.getNome());

        return ResponseEntity.ok(categoriaDto);

    }
}
