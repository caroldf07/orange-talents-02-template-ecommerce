package br.com.orangetalents.mercadolivre.cadastronovaopiniao.view;

import br.com.orangetalents.mercadolivre.cadastronovaopiniao.model.Opiniao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OpiniaoResponse {
    private String titulo;
    private String descricao;


    public OpiniaoResponse(@Valid @NotNull Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
