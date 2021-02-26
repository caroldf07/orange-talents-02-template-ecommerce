package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.view;

public class NovaCaracteristicaResponse {
    private String nome;
    private String descricao;

    public NovaCaracteristicaResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
