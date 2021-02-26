package br.com.orangetalents.mercadolivre.cadastronovacategoria.controller;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.NovaCategoriaRequest;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.view.NovaCategoriaResponse;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public NovaCategoriaResponse criar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria categoria = novaCategoriaRequest.toModel(em);
        em.persist(categoria);
        return categoria.fromModelToResponse();
    }
}
