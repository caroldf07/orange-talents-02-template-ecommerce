package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.view;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.CaracteristicaProduto;

public class CaracteristicaDetalheResponse {
    private String nome;
    private String descricao;

    public CaracteristicaDetalheResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    /*
     * Criado por conta do jackson
     * */
    @Deprecated
    public CaracteristicaDetalheResponse() {
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
