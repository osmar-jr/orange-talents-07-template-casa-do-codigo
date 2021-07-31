package br.com.zupacademy.osmarjunior.casadocodigo.validator;

import br.com.zupacademy.osmarjunior.casadocodigo.annotation.EstadoIsInPais;
import br.com.zupacademy.osmarjunior.casadocodigo.form.ClienteFormRequest;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EstadoPertenceAoPaisValidator implements ConstraintValidator<EstadoIsInPais, ClienteFormRequest> {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PaisRepository paisRepository;

    @Override
    public void initialize(EstadoIsInPais constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteFormRequest clienteFormRequest, ConstraintValidatorContext context) {

        if (clienteFormRequest.getEstadoId() == null){
            return true;
        }

        Optional<Pais> optionalPais = paisRepository.findById(clienteFormRequest.getPaisId());
        Assert.state(optionalPais.isPresent(), "País informado não existe.");

        Pais pais = optionalPais.get();
        Optional<Estado> optionalEstado = estadoRepository.findByIdAndPais(clienteFormRequest.getEstadoId(), pais);

        Assert.state(optionalEstado.isPresent(), "Estado ID: "
                + clienteFormRequest.getEstadoId() + " não pertence ao país ID: " + pais.getId() );

        return false;
    }
}
