package br.com.orangetalents.mercadolivre.fechamentocompra.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @NotNull
    private GatewayEnum gatewayEnum;

    @NotNull
    @Positive
    private Integer quantidadeAComprar;

    public Compra(@NotNull @Valid Produto produto,
                  @NotNull @Valid Usuario comprador,
                  @NotNull GatewayEnum gatewayEnum,
                  @NotNull @Positive Integer quantidadeAComprar) {
        this.produto = produto;
        this.comprador = comprador;
        this.gatewayEnum = gatewayEnum;
        this.quantidadeAComprar = quantidadeAComprar;
    }

    /*
     * Criado por conta do jackson
     * */
    @Deprecated
    public Compra() {
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", comprador=" + comprador +
                ", quantidadeAComprar=" + quantidadeAComprar +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public GatewayEnum getGatewayEnum() {
        return gatewayEnum;
    }

    public Integer getQuantidadeAComprar() {
        return quantidadeAComprar;
    }
}
