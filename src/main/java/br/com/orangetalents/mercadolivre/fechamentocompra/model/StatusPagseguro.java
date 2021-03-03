package br.com.orangetalents.mercadolivre.fechamentocompra.model;

import br.com.orangetalents.mercadolivre.fechamentocompra.model.transacao.StatusTransacao;

public enum StatusPagseguro {
    SUCESSO,
    ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }

}
