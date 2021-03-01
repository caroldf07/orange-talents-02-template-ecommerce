package br.com.orangetalents.mercadolivre.compartilhado.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrosHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErro> handle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        List<ValidationErro> erros = new ArrayList<>();

        fieldError.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ValidationErro erroConstruido = new ValidationErro(erro.getField(), mensagem);
            erros.add(erroConstruido);
        });

        return erros;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErro> handle(BindException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        List<ValidationErro> erros = new ArrayList<>();

        fieldError.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ValidationErro erroConstruido = new ValidationErro(erro.getField(), mensagem);
            erros.add(erroConstruido);
        });

        return erros;

    }
}
