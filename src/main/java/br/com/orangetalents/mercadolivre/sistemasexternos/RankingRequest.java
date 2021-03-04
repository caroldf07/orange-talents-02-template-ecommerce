package br.com.orangetalents.mercadolivre.sistemasexternos;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
