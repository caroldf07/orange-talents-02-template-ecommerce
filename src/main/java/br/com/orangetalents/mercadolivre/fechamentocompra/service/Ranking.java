package br.com.orangetalents.mercadolivre.fechamentocompra.service;

import br.com.orangetalents.mercadolivre.fechamentocompra.controller.ChamadaExterna;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
public class Ranking implements ChamadaExterna {

    @Override
    public void executa(@NotNull @Valid Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> rankingRequest = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getResponsavel().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking", rankingRequest, String.class);
    }
}
