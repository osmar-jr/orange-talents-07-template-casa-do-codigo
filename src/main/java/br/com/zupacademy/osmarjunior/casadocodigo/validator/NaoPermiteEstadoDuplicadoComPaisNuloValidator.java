package br.com.zupacademy.osmarjunior.casadocodigo.validator;

import br.com.zupacademy.osmarjunior.casadocodigo.form.EstadoFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class NaoPermiteEstadoDuplicadoComPaisNuloValidator implements Validator {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()){
            return;
        }

        EstadoFormRequest request = (EstadoFormRequest) target;
        List<Estado> estados = estadoRepository.findByNomeAndPaisIdIsNull(request.getNome());

        if (estados.size() >= 1){
            errors.rejectValue("nome", null, "Já existem estados sem países com o nome: " + request.getNome());
        }


    }
}
