package br.com.orangetalents.mercadolivre.paginadedetalhe.controller;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.paginadedetalhe.view.DetalhesProdutoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//Carga de 3
@RestController
@RequestMapping("/produtos")
public class PaginaDetalheController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProdutoResponse> mostrarDetalhesProduto(@PathVariable("id") Long id) {
        Produto produto = em.find(Produto.class, id);

        return ResponseEntity.ok(new DetalhesProdutoResponse(produto));
    }
}
