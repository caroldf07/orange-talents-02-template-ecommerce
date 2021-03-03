package br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao;

import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.FormatoDataCriacao;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacao;

    @FormatoDataCriacao
    private LocalDate instante;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @JsonCreator
    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @NotNull Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.instante = LocalDate.now();
        this.compra = compra;
    }

    /*
     * Criado por conta do jackson
     * */
    @Deprecated
    public Transacao() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacao, transacao.idTransacao) && Objects.equals(instante, transacao.instante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao, instante);
    }

    public StatusTransacao getStatus() {
        return status;
    }
}
