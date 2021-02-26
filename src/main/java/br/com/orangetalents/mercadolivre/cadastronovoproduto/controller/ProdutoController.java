package br.com.orangetalents.mercadolivre.cadastronovoproduto.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.repository.UsuarioRepository;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.NovoProdutoRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.validacao.CaracteristicaComNomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new CaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public String criar(@AuthenticationPrincipal Usuario userDetails, @RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {

        Usuario responsavel = userDetails.get();
        System.out.println(responsavel);

        Produto produto = novoProdutoRequest.toModel(em, responsavel);


        return produto.toString();
    }
}
