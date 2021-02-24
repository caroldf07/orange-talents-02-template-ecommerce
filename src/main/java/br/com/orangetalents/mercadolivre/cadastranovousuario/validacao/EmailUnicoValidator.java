package br.com.orangetalents.mercadolivre.cadastranovousuario.validacao;

import br.com.orangetalents.mercadolivre.cadastranovousuario.NovoUsuarioRequest;
import br.com.orangetalents.mercadolivre.cadastranovousuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailUnicoValidator implements Validator {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoUsuarioRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NovoUsuarioRequest novoUsuarioRequest= (NovoUsuarioRequest) o;

        if(usuarioRepository.findByEmail(novoUsuarioRequest.getEmail()).isPresent()){
            errors.rejectValue("email",null,"E-mail já cadastrado, tente outro");
        }

    }
}
