package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.controller;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.Caracteristica;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.view.NovaCaracteristicaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//Carga cognitiva de 4
@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<NovaCaracteristicaResponse> criar(@RequestBody @Valid NovaCaracteristicaRequest novaCaracteristicaRequest) {
        Caracteristica caracteristica = novaCaracteristicaRequest.toModel();
        em.persist(caracteristica);
        return ResponseEntity.ok(caracteristica.fromModelToResponse());
    }
}
