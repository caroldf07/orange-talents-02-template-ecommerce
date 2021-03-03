package br.com.orangetalents.mercadolivre.fechamentocompra.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.fechamentocompra.RetornoGateway;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.StatusTransacao;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.Transacao;
import org.junit.jupiter.api.Assertions;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    // Sempre que for atualizar uma compra, ele já atualiza as transações vinculadas
    private Set<Transacao> transacoes;

    @Transient //Ignorando o campo para o JPA
    private boolean finalizadaComSucesso = false;

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

    /*
     * Aqui implementamos um polimorfimos do gateway, porque se não teria que duplicar o método
     * */
    public void tentativaPagamento(@Valid RetornoGateway retornoGateway) {
        Transacao transacao = retornoGateway.toTransacao(this);

        Assertions.assertTrue(!this.transacoes.contains(transacao), "Essa transação já existe");

        Set<Transacao> transacaosConcluidasSucesso = this.transacoes.stream().
                filter(transacaoFilter -> transacao.getStatus() == StatusTransacao.sucesso).
                collect(Collectors.toSet());

        Assertions.assertTrue(transacaosConcluidasSucesso.isEmpty(), "Essa transação já foi finalizada");
        Assertions.assertTrue(transacaosConcluidasSucesso.size() <= 1, "Falha no sistema");

        this.transacoes.add(transacao);
        finalizadaComSucesso = true;
    }

    public boolean isFinalizadaComSucesso() {
        return finalizadaComSucesso;
    }
}
