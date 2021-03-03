package br.com.orangetalents.mercadolivre.fechamentocompra;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.GatewayEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    @NotNull
    private Integer quantidadeAComprar;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private GatewayEnum gatewayEnum;

    public NovaCompraRequest(@Positive @NotNull Integer quantidadeAComprar, @NotNull GatewayEnum gatewayEnum) {
        this.quantidadeAComprar = quantidadeAComprar;
        this.gatewayEnum = gatewayEnum;
    }

    /*
     * Criado apenas por conta do jackson
     * */
    @Deprecated
    public NovaCompraRequest() {
    }

    public Integer getQuantidadeAComprar() {
        return quantidadeAComprar;
    }

    public GatewayEnum getGatewayEnum() {
        return gatewayEnum;
    }
}
