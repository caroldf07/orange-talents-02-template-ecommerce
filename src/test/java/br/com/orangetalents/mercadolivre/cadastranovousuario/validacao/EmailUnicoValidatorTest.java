package br.com.orangetalents.mercadolivre.cadastranovousuario.validacao;

import br.com.orangetalents.mercadolivre.cadastranovousuario.NovoUsuarioRequest;
import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;
import java.util.stream.Stream;

class EmailUnicoValidatorTest {


    static Stream<Arguments> geradorTestValidate() {
        Optional<Usuario> usuario = Optional.of(new Usuario("email@email.com.br", "123456"));
        return Stream.of(Arguments.of(usuario, true), Arguments.of(Optional.empty(), false));
    }

    /**
     * Method: validate(Object o, Errors errors)
     */
    @DisplayName("Deveria lidar com e-mail duplicado")
    @ParameterizedTest
    @MethodSource(value = "geradorTestValidate")
    void testValidate(Optional<Usuario> possivelUsuario, boolean esperado) throws Exception {
        UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);

        EmailUnicoValidator emailUnicoValidator = new EmailUnicoValidator(usuarioRepository);

        Object o = new NovoUsuarioRequest("email@email.com.br", "123456");
        Errors errors = new BeanPropertyBindingResult(o, "teste");

        Mockito.when(usuarioRepository.findByEmail("email@email.com.br")).thenReturn(possivelUsuario);


        emailUnicoValidator.validate(o, errors);
    }


}
