package br.com.orangetalents.mercadolivre.fechamentocompra.service;

import br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao.Emails;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


//Carga de 6
@Service
public class EventosFinalizacaoCompra {

    @Autowired
    private Emails email;

    @Autowired
    private NotaFiscal notaFiscal;

    @Autowired
    private Ranking ranking;

    public ResponseEntity<?> retornoEventos(@NotNull @Valid Compra compra) {
        if (compra.isFinalizadaComSucesso()) {
            Assertions.assertTrue(compra.isFinalizadaComSucesso(), "Deu falha da transação");

            notaFiscal.executa(compra);

            ranking.executa(compra);

            email.compraFinalizadaSucesso(compra);
            return ResponseEntity.ok().build();
        } else {
            email.compraFinalizadaErro(compra);
            return ResponseEntity.badRequest().build();
        }
    }
}
