package br.com.orangetalents.mercadolivre.compartilhado.validacao;

import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistByIdValidator implements ConstraintValidator<ExistById, Object> {

    @PersistenceContext
    EntityManager em;
    private String fieldName;
    private Class<?> klass;

    @Override
    public void initialize(ExistById existById) {
        fieldName = existById.fieldName();
        klass = existById.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + fieldName + " =:value")
                .setParameter("value", value);

        Assertions.assertTrue((query.getResultList().size() >= 1) ,"Não existe o id indicado");

        return !query.getResultList().isEmpty();
    }
}
