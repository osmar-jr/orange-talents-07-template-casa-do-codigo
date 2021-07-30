package br.com.zupacademy.osmarjunior.casadocodigo.validator;

import br.com.zupacademy.osmarjunior.casadocodigo.form.EstadoFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NaoPermiteEstadoDuplicadoEmUmPaisValidator implements Validator {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PaisRepository paisRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()){
            return;
        }

        EstadoFormRequest request = (EstadoFormRequest) target;
        if(request.getPaisId() == null){
            return;
        }

        Optional<Pais> optionalPais = paisRepository.findById(request.getPaisId());

        if(optionalPais.isEmpty()){
            errors.rejectValue("paisId", null, "País com ID: "
                    + request.getPaisId() + " não existe no banco de dados.");
        }

        Optional<Estado> optionalEstado = estadoRepository.findByNomeAndPaisId(request.getNome(), request.getPaisId());

        if(optionalEstado.isPresent()){
            errors.rejectValue("nome", null,
                    "Estado informado já existe no país indicado.");
        }
    }
}
