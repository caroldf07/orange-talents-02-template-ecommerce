package br.com.orangetalents.mercadolivre.sistemasexternos.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.sistemasexternos.NotaFiscalRequest;
import br.com.orangetalents.mercadolivre.sistemasexternos.RankingRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemasExternosController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest, @AuthenticationPrincipal Usuario comprador) throws InterruptedException {
        System.out.println("criando nota " + notaFiscalRequest);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingRequest rankingRequest, @AuthenticationPrincipal Usuario comprador) throws InterruptedException {
        System.out.println("criando ranking" + rankingRequest);
        Thread.sleep(150);
    }
}
