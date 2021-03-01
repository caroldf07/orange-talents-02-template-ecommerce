package br.com.orangetalents.mercadolivre.cadastronovoproduto;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class NovoProdutoRequestTest {

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(0,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"),
                                new NovaCaracteristicaRequest("carac3", "blablabla"))),

                Arguments.of(1,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"))),

                Arguments.of(1,
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac1", "blablabla")))
        );
    }

    @ParameterizedTest
    @DisplayName("Deveria retornar se tem características com nomes iguais")
    @MethodSource("geradorTeste1")
    void testeNovoProduto1(int esperado, List<NovaCaracteristicaRequest> caracteristicas) throws Exception {

        NovoProdutoRequest novoProdutoRequest = new NovoProdutoRequest("outro nome", BigDecimal.TEN, 10, caracteristicas, "descricao", 1L);

        Assertions.assertEquals(esperado, novoProdutoRequest.temCaracteristicasIguais().size());
    }
}