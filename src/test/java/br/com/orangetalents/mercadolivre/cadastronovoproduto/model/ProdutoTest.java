package br.com.orangetalents.mercadolivre.cadastronovoproduto.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class ProdutoTest {

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"),
                                new NovaCaracteristicaRequest("carac3", "blablabla"))),

                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla"),
                                new NovaCaracteristicaRequest("carac3", "blablabla"),
                                new NovaCaracteristicaRequest("carac3", "blablabla"))
                )
        );
    }

    static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                                new NovaCaracteristicaRequest("carac2", "blablabla")
                        )),

                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("carac1", "blablabla"))
                )
        );
    }

    @DisplayName("Deve aceitar 3 ou mais características")
    @MethodSource("geradorTeste1")
    @ParameterizedTest
    void testeProduto1(Collection<NovaCaracteristicaRequest> caracteristicaRequests) throws Exception {
        Categoria categoria = new Categoria("categoria");
        Usuario responsavel = new Usuario("email@email.com.br", "123456");

        new Produto("nome", BigDecimal.TEN, 10,
                caracteristicaRequests, "descricao", categoria, responsavel);

    }

    @DisplayName("Deve recusar menos de 3 argumentos")
    @MethodSource("geradorTeste2")
    @ParameterizedTest
    void testeProduto2(Collection<NovaCaracteristicaRequest> caracteristicaRequests) throws Exception {
        Categoria categoria = new Categoria("categoria");
        Usuario responsavel = new Usuario("email@email.com.br", "123456");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Produto("nome", BigDecimal.TEN, 10,
                    caracteristicaRequests, "descricao", categoria, responsavel);
        });

    }

    @DisplayName("Verifica estoque")
    @ParameterizedTest
    @CsvSource({"0,1,false", "1,1,true", "5,3,true"})
    void abateDoEstoque1(int estoque, int quantidadeCompra, boolean retornoEsperado) {
        List<NovaCaracteristicaRequest> caracteristicaRequests = List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                new NovaCaracteristicaRequest("carac2", "blablabla"),
                new NovaCaracteristicaRequest("carac3", "blablabla"));

        Categoria categoria = new Categoria("categoria");
        Usuario responsavel = new Usuario("email@email.com.br", "123456");
        Produto produto = new Produto("nome", BigDecimal.TEN, estoque,
                caracteristicaRequests, "descricao", categoria, responsavel);

        boolean retornoReal = produto.abateDoEstoque(quantidadeCompra);

        Assertions.assertEquals(retornoEsperado, retornoReal);

    }

    @DisplayName("Não avança se o estoque for 0")
    @ParameterizedTest
    @CsvSource({"-1,1,false", "0,1,false", "-100,3,false"})
    void abateDoEstoque2(int estoque, int quantidadeCompra, boolean retornoEsperado) {
        List<NovaCaracteristicaRequest> caracteristicaRequests = List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
                new NovaCaracteristicaRequest("carac2", "blablabla"),
                new NovaCaracteristicaRequest("carac3", "blablabla"));

        Categoria categoria = new Categoria("categoria");
        Usuario responsavel = new Usuario("email@email.com.br", "123456");
        Produto produto = new Produto("nome", BigDecimal.TEN, estoque,
                caracteristicaRequests, "descricao", categoria, responsavel);

        boolean retornoReal = produto.abateDoEstoque(quantidadeCompra);

        Assertions.assertEquals(retornoEsperado, retornoReal);

    }

}