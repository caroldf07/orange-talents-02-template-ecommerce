package br.com.orangetalents.mercadolivre.cadastranovousuario.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.NovoUsuarioRequest;
import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

//Carga de 3
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest) {
        Usuario usuario = novoUsuarioRequest.toModel();
        em.persist(usuario);

        return ResponseEntity.ok("Usuário criado com sucesso");

    }
}
