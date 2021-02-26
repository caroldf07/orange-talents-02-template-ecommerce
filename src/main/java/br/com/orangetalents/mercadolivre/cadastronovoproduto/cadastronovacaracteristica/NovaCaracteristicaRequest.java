package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.Caracteristica;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.CaracteristicaProduto;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.UniqueValue;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCaracteristicaRequest {

    @NotBlank
    @UniqueValue(domainClass = Caracteristica.class, fieldName = "nome")
    private String nome;

    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome,
                                     @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "NovaCaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {

        return new CaracteristicaProduto(this.nome, this.descricao, produto);
    }

    public Caracteristica toModel() {
        return new Caracteristica(this.nome, this.descricao);
    }

}
