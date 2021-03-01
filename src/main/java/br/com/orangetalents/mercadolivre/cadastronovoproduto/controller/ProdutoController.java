package br.com.orangetalents.mercadolivre.cadastronovoproduto.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.NovoProdutoRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem.NovaImagemRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem.UploaderFake;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.validacao.CaracteristicaComNomeIgualValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;


//Carga de 6
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager em;

    UploaderFake uploaderFake;

    @InitBinder(value = "NovoProdutoRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(new CaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> criar(@AuthenticationPrincipal Usuario usuarioLogado, @RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {

        Produto produto = novoProdutoRequest.toModel(em, usuarioLogado);
        em.persist(produto);

        return ResponseEntity.ok(produto.toString());
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<String> adicionarImagem(@PathVariable("id") Long id, @Valid NovaImagemRequest novaImagemRequest, @AuthenticationPrincipal Usuario usuarioLogado) {

        Produto produto = em.find(Produto.class, id);
        Usuario responsavel = em.find(Usuario.class, usuarioLogado.getId());

        /*
         * Se o usuário logado não for o responsável pelo produto, retornar 403
         * */
        if (!produto.getResponsavel().equals(responsavel)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        /*
         * Armazenando as imagens
         * */
        Set<String> links = uploaderFake.envia(novaImagemRequest.getImagens());

        /*
         * Busco o produto selecionado e faço o vínculo da imagem recebida com o produto
         * */
        produto.associaImagem(links);

        /*
         * Faço a persistência do produto atulizado já com as imagens
         * */
        em.merge(produto);

        return ResponseEntity.ok(produto.toString());
    }
}
