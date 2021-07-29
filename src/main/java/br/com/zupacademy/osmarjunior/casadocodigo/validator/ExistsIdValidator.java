package br.com.zupacademy.osmarjunior.casadocodigo.validator;

import br.com.zupacademy.osmarjunior.casadocodigo.constraint.ExistsId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private Class<?> classDomain;
    private String attributeName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId existsId) {
        this.classDomain = existsId.classDomain();
        this.attributeName = existsId.attributeName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + classDomain.getName() + " where " + attributeName + "=:value");
        query.setParameter("value", value);

        List<?> resultList = query.getResultList();

        Assert.state(resultList.size() >= 1 , "Não existem registros com o valor de ID: " + value);

        return resultList.size() >= 1;
    }
}
