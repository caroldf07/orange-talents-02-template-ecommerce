package br.com.orangetalents.mercadolivre.fechamentocompra.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.fechamentocompra.NovaCompraRequest;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.GatewayEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

class CompraControllerTest {

    @Mock
    private EntityManager em;

    private List<NovaCaracteristicaRequest> caracteristicaRequests = List.of(new NovaCaracteristicaRequest("carac1", "blablabla"),
            new NovaCaracteristicaRequest("carac2", "blablabla"),
            new NovaCaracteristicaRequest("carac3", "blablabla"));

    private Categoria categoria = new Categoria("categoria");
    private Usuario responsavel = new Usuario("email@email.com.br", "123456");
    private UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");

    @Test
    @DisplayName("Redireciona para o gateway quando abate do estoque")
    void fecharCompra() throws Exception {

        Produto produto = new Produto("nome", BigDecimal.TEN, 10,
                caracteristicaRequests, "descricao", categoria, responsavel);



        /*
         * Mockei que o produto foi persistido
         * */
        Mockito.when(em.find(Produto.class, 1l)).thenReturn(produto);


        Mockito.doAnswer(invocation -> {
            /*
             * Mockei que o produto gerou uma compra
             * */
            Compra compra = invocation.<Compra>getArgument(0);

            /*
             * Com o refletion conseguimos fingir que alteramos algum atributo que é inacessível
             * */
            ReflectionTestUtils.setField(compra, "id", 1l);
            return null;
        }).when(em).persist(Mockito.any(Compra.class));

        NovaCompraRequest novaCompraRequest = new NovaCompraRequest(1, GatewayEnum.Paypal);
        //pendente de terminar
    }
}