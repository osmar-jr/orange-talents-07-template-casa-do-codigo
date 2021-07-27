package br.com.zupacademy.osmarjunior.casadocodigo.validation;

import br.com.zupacademy.osmarjunior.casadocodigo.form.AutorFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NaoPermiteEmailDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorFormRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        if(errors.hasErrors()){
            return;
        }

        AutorFormRequest autorFormRequest = (AutorFormRequest) object;
        Optional<Autor> optionalAutor = autorRepository.findByEmail(autorFormRequest.getEmail());

        if (optionalAutor.isPresent()){
            errors.rejectValue("email", "E-mail informado já está cadastrado no sistema.");
        }

    }
}
