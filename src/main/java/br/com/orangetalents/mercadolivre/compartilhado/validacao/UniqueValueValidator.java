package br.com.orangetalents.mercadolivre.compartilhado.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    EntityManager em;
    private String fieldName;
    private Class<?> klass;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        fieldName = uniqueValue.fieldName();
        klass = uniqueValue.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = em.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + fieldName + " =:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();

        Assert.isTrue(list.size() <= 1, "Foi encontrado mais de 1" + klass);

        return query.getResultList().isEmpty();
    }
}
