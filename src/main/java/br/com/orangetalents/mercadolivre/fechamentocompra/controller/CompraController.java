package br.com.orangetalents.mercadolivre.fechamentocompra.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao.Emails;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.fechamentocompra.NovaCompraRequest;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.GatewayEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


//Carga de 7
@RestController
@RequestMapping("/compras")
public class CompraController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private Emails emails;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<String> fecharCompra(@PathVariable Long id,
                                               @RequestBody @Valid NovaCompraRequest novaCompraRequest,
                                               @AuthenticationPrincipal Usuario comprador) throws BindException {

        Produto produto = em.find(Produto.class, id);
        GatewayEnum gatewayEnum = novaCompraRequest.getGatewayEnum();

        /*
         * Aqui pensei em fazer a Exception dentro do método, mas o indicado é não fazer Exception sem
         * checagem e isso vai atravessar o sistema inteiro
         * */
        if (produto.abateDoEstoque(novaCompraRequest.getQuantidadeAComprar())) {
            Compra compra = new Compra(produto, comprador, novaCompraRequest.getGatewayEnum(), novaCompraRequest.getQuantidadeAComprar());
            em.persist(compra);

            emails.novaCompra(compra);

            if (gatewayEnum.equals(GatewayEnum.Paypal)) {
                String urlRetornoPaypal = UriComponentsBuilder.fromPath("/retorno-paypal/{id}").
                        buildAndExpand(compra.getId()).toString();

                return ResponseEntity.ok("paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal);
            } else if (gatewayEnum.equals(GatewayEnum.Pagseguro)) {
                String urlRetornoPagseguro = UriComponentsBuilder.fromPath("/retorno-pagseguro/{id}").
                        buildAndExpand(compra.getId()).toString();

                return ResponseEntity.ok("pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro);
            }

        }

        BindException problemaComEstoque = new BindException(novaCompraRequest,
                "novaCompraRequest");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por conta do estoque");

        throw problemaComEstoque;
    }
}
