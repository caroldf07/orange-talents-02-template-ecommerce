package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.Caracteristica;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovaCaracteristicaRequest {

    @NotBlank
    @UniqueValue(domainClass = Caracteristica.class,fieldName = "nome")
    private String nome;

    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public NovaCaracteristicaRequest(@NotBlank String nome,
                                     @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NovaCaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Caracteristica toModel() {
        return new Caracteristica(this.nome,this.descricao);
    }
}
