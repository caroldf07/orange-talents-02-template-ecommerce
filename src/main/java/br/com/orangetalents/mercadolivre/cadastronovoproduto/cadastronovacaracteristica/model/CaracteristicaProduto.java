package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }
}
