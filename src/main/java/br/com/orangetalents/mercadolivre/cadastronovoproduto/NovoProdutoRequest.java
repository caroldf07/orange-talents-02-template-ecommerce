package br.com.orangetalents.mercadolivre.cadastronovoproduto;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.Caracteristica;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.ExistById;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    @Positive
    private BigDecimal preco;

    @PositiveOrZero
    @NotNull
    private Integer quantidade;

    @Size(min = 3)
    @NotNull
    private List<Long> idCaracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    private Long idCategoria;

    public NovoProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal preco,
                              @Positive @NotNull Integer quantidade, @Size(min = 3)
                              @NotNull List<Long> idCaracteristicas, @NotBlank @Size(max = 1000) String descricao,
                              @NotNull Long idCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.idCaracteristicas = idCaracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    /*
     * Criado para o jackson
     * */
    @Deprecated
    public NovoProdutoRequest() {
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", idCaracteristicas=" + idCaracteristicas +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Long> getIdCaracteristicas() {
        return idCaracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Produto toModel(EntityManager em) {
        Categoria categoria = em.find(Categoria.class, idCategoria);

        List<Caracteristica> caracteristicas = new ArrayList<>();

        idCaracteristicas.forEach(caracteristica -> {
            Caracteristica caracConvertida = em.find(Caracteristica.class, caracteristica);

            caracteristicas.add(caracConvertida);

        });

        return new Produto(this.nome,
                this.preco,
                this.quantidade,
                caracteristicas,
                this.descricao,
                categoria);
    }
}
