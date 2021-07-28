package br.com.zupacademy.osmarjunior.casadocodigo.validator;

import br.com.zupacademy.osmarjunior.casadocodigo.form.CategoriaFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NaoPermiteNomeDeCategoriaDuplicadoValidator implements Validator {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        CategoriaFormRequest categoriaFormRequest = (CategoriaFormRequest) object;
        Optional<Categoria> optionalCategoria = categoriaRepository.findByNome(categoriaFormRequest.getNome());

        if(optionalCategoria.isPresent()){
            errors.rejectValue("nome", null, "Nome da categoria já existe.");
        }
    }
}
