package br.com.orangetalents.mercadolivre.fechamentocompra;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.Transacao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface RetornoGateway {

    Transacao toTransacao(@Valid @NotNull Compra compra);
}
