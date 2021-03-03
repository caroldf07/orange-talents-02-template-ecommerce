package br.com.orangetalents.mercadolivre.fechamentocompra;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.StatusPagseguro;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.Transacao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguro implements RetornoGateway {

    @NotBlank
    private String idTransacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagseguro statusPagseguro;

    @JsonCreator
    public RetornoPagseguro(@NotBlank String idTransacao, @NotNull StatusPagseguro statusPagseguro) {
        this.idTransacao = idTransacao;
        this.statusPagseguro = statusPagseguro;
    }

    @Override
    public Transacao toTransacao(@NotNull @Valid Compra compra) {
        return new Transacao(this.statusPagseguro.normaliza(), this.idTransacao, compra);
    }

    @Override
    public String toString() {
        return "RetornoPagseguro{" +
                "idTransacao='" + idTransacao + '\'' +
                ", statusPagseguro=" + statusPagseguro +
                '}';
    }
}
