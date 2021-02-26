package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.view.NovaCaracteristicaResponse;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne
    private Produto produto;

    public Caracteristica(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }


    /*
     * Retorno para o usuário após a criação de uma nova característica
     * */
    public NovaCaracteristicaResponse fromModelToResponse() {
        return new NovaCaracteristicaResponse(this.nome, this.descricao);
    }

    public Produto getProduto() {
        return produto;
    }

    /*
     * Criado apenas por conta do jackson
     */
    public Caracteristica() {
    }
}
