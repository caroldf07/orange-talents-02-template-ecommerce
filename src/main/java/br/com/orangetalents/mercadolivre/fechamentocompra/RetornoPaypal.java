package br.com.orangetalents.mercadolivre.fechamentocompra;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.StatusTransacao;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.Transacao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPaypal implements RetornoGateway {

    @NotBlank
    private String idTransacao;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private int statusPaypal;

    @JsonCreator //Indicando para o Jackson qual é o constructor, assim não precisa mais criar o constructor vazio
    public RetornoPaypal(@NotBlank String idTransacao, @NotNull int statusPaypal) {
        this.idTransacao = idTransacao;
        this.statusPaypal = statusPaypal;
    }


    public String getIdTransacao() {
        return idTransacao;
    }

    public int getStatusPaypal() {
        return statusPaypal;
    }

    @Override
    public String toString() {
        return "RetornoPaypal{" +
                "idTransacao='" + idTransacao + '\'' +
                ", statusPaypal=" + statusPaypal +
                '}';
    }


    @Override
    public Transacao toTransacao(@Valid @NotNull Compra compra) {

        /*
         * Normalizando o status do Paypal para a aplicação
         * */
        StatusTransacao statusTransacao = statusPaypal == 1 ? StatusTransacao.sucesso : StatusTransacao.erro;

        return new Transacao(statusTransacao, idTransacao, compra);

    }
}
