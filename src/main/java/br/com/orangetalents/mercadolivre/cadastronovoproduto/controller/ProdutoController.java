package br.com.orangetalents.mercadolivre.cadastronovoproduto.controller;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.NovoProdutoRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {
        Produto produto = novoProdutoRequest.toModel(em);

        Assertions.assertNotNull(novoProdutoRequest.getNome(), "Nome está em branco");
        Assertions.assertNotNull(novoProdutoRequest.getPreco(), "O preço veio vazio");
        Assertions.assertTrue(novoProdutoRequest.getPreco().doubleValue() > 0.00, "O preço passado é menor ou igual a zero");
        Assertions.assertNotNull(novoProdutoRequest.getQuantidade(), "A quantidade veio vazio");
        Assertions.assertTrue(novoProdutoRequest.getPreco().intValue() >= 0, "A quantidade é menor que zero");
        Assertions.assertTrue(novoProdutoRequest.getIdCaracteristicas().size() >= 3, "Recebeu menos de 3 características");
        Assertions.assertNotNull(novoProdutoRequest.getDescricao(), "A descrição veio vazia");
        Assertions.assertNotNull(novoProdutoRequest.getIdCategoria(), "A categoria veio vazia");


        return produto.toString();
    }
}
