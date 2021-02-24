package br.com.orangetalents.mercadolivre.cadastranovousuario.validacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@DateTimeFormat(pattern = "dd/MM/yyyy")
@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
@Target(ElementType.FIELD)
@ConstraintComposition(value = CompositionType.OR)
public @interface FormatoDataCriacao {
    String message() default "Formato de data deve ser dd/mm/yyyy em números";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
