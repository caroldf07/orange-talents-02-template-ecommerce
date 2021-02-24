package br.com.orangetalents.mercadolivre.cadastranovousuario;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.NovaCategoriaRequest;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class NovoUsuarioRequestTest {

    @Test
    @DisplayName("Deve retornar uma categoria sem mãe")
    void toModel1() {

        EntityManager em = Mockito.mock(EntityManager.class);

        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Tecnologia");
        Categoria categoria = novaCategoriaRequest.toModel(em);

        Assertions.assertNull(categoria.getNomeCategoriaMae(),"A categoria veio com mãe");
    }

    @Test
    @DisplayName("Deve retornar uma categoria com mãe")
    void toModel2() {

        EntityManager em = Mockito.mock(EntityManager.class);

        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Tecnologia");
        novaCategoriaRequest.setIdCategoriaMae(1L);
        Categoria categoria = novaCategoriaRequest.toModel(em);

        Assertions.assertNotNull(novaCategoriaRequest.getIdCategoriaMae(),"A categoria veio sem mãe");
    }
}