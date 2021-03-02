package br.com.orangetalents.mercadolivre.paginadedetalhe.view;

import br.com.orangetalents.mercadolivre.cadastronovaopiniao.view.OpiniaoResponse;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.PerguntaDetalheResponse;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.view.CaracteristicaDetalheResponse;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class DetalhesProdutoResponse {

    private String nomeProduto;
    private BigDecimal preco;
    private String descricao;
    private int mediaNota;
    private Set<CaracteristicaDetalheResponse> caracteristicas;
    private Set<String> links;
    private Set<OpiniaoResponse> opinioes;
    private Set<PerguntaDetalheResponse> perguntas;

    public DetalhesProdutoResponse(@Valid @NotNull Produto produto) {
        this.nomeProduto = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();

        /*
         * Limpando as informações e mantendo a segurança do que será enviado para o Response
         * */
        this.caracteristicas = produto.mapCaracteristicas(caracteristica ->
                new CaracteristicaDetalheResponse(caracteristica));
        this.links = produto.mapImagens(imagem -> imagem.getLinkImagem());
        this.opinioes = produto.mapOpinioes(opiniao -> new OpiniaoResponse(opiniao));
        this.perguntas = produto.mapPerguntas(pergunta ->
                new PerguntaDetalheResponse(pergunta));
        /*
         * Percorreu a lista de opiniões, pegou as notas e calculou a média
         * */
        this.mediaNota = (int) produto.mapOpinioes(opiniao -> opiniao.getNota()).
                stream().
                mapToInt(nota -> nota).
                average().orElse(0.0);

    }

    @Deprecated
    public DetalhesProdutoResponse() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getMediaNota() {
        return mediaNota;
    }

    public Set<CaracteristicaDetalheResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinks() {
        return links;
    }

    public Set<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaDetalheResponse> getPerguntas() {
        return perguntas;
    }
}
