package br.com.orangetalents.mercadolivre.cadastronovapergunta;

import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PerguntaDetalheResponse {

    private String titulo;

    public PerguntaDetalheResponse(@Valid @NotNull Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    /*
     * Criado por conta do Jackson
     * */
    @Deprecated
    public PerguntaDetalheResponse() {
    }

    public String getTitulo() {
        return titulo;
    }
}
