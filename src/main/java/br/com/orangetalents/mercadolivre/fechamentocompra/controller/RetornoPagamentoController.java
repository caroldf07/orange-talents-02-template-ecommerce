package br.com.orangetalents.mercadolivre.fechamentocompra.controller;

import br.com.orangetalents.mercadolivre.fechamentocompra.RetornoGateway;
import br.com.orangetalents.mercadolivre.fechamentocompra.RetornoPagseguro;
import br.com.orangetalents.mercadolivre.fechamentocompra.RetornoPaypal;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.service.EventosFinalizacaoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


//Carga de 5
@RestController
public class RetornoPagamentoController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EventosFinalizacaoCompra eventosFinalizacaoCompra;

    @PostMapping("/retorno-paypal/{id}") //Uri criada no momento do envio da compra
    @Transactional
    public ResponseEntity<?> retornoCompraPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypal retornoPaypal) {
        return procesamento(idCompra, retornoPaypal);
    }

    @PostMapping("/retorno-pagseguro/{id}") //Uri criada no momento do envio da compra
    @Transactional
    public ResponseEntity<?> retornoCompraPagseguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguro retornoPagseguro) {


        return procesamento(idCompra, retornoPagseguro);

    }

    public ResponseEntity<?> procesamento(Long idCompra, @Valid RetornoGateway retornoGateway) {
        Compra compra = em.find(Compra.class, idCompra);

        compra.tentativaPagamento(retornoGateway);
        em.merge(compra);

        return eventosFinalizacaoCompra.retornoEventos(compra);
    }

}
