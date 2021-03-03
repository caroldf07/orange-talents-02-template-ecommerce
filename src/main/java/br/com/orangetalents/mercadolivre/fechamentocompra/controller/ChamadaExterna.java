package br.com.orangetalents.mercadolivre.fechamentocompra.controller;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ChamadaExterna {
    void executa(@NotNull @Valid Compra compra);
}
