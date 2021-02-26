package br.com.orangetalents.mercadolivre.cadastronovoproduto;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.ExistById;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<@Valid NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistById(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public NovoProdutoRequest(@NotBlank String nome,
                              @NotNull @Digits(integer = 6, fraction = 2) @Positive BigDecimal preco,
                              @PositiveOrZero @NotNull Integer quantidade, @Size(min = 3)
                              @NotNull List<@Valid NovaCaracteristicaRequest> caracteristicas,
                              @NotBlank @Length(max = 1000) String descricao, @NotNull Long idCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
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
                ", caracteristicas=" + caracteristicas +
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

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    /*
     * Verifica se a característica do Produto já não foi passada,
     * mas não verifica se ela já consta no banco para aquele produto
     * */
    public Set<String> temCaracteristicasIguais() {
        HashSet<String> nomes = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (NovaCaracteristicaRequest novaCaracteristicaRequest :
                caracteristicas) {
            if (!nomes.add(novaCaracteristicaRequest.getNome())) {
                resultados.add(nome);
            }
        }

        return resultados;
    }

    public Produto toModel(EntityManager em, Usuario responsavel) {
        Categoria categoria = em.find(Categoria.class, idCategoria);

        return new Produto(this.nome,
                this.preco,
                this.quantidade,
                this.caracteristicas,
                this.descricao,
                categoria, responsavel);
    }
}
