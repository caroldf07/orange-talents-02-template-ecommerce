package br.com.orangetalents.mercadolivre.cadastronovapergunta;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public NovaPerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
