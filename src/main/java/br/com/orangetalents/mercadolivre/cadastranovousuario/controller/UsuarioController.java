package br.com.orangetalents.mercadolivre.cadastranovousuario.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.NovoUsuarioRequest;
import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.EmailUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//Carga de 4
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    EmailUnicoValidator emailUnicoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(emailUnicoValidator);
    }

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.toModel();
        em.persist(usuario);

        return usuario.toString();

    }
}
