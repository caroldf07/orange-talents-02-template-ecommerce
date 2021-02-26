package br.com.orangetalents.mercadolivre.cadastronovacategoria.view;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;

public class NovaCategoriaResponse {

    private String nome;
    private Categoria categoriaMae;

    public NovaCategoriaResponse(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
