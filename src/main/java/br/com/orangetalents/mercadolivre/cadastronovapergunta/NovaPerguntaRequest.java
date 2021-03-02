package br.com.orangetalents.mercadolivre.cadastronovapergunta;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public NovaPerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    /*
     * Criado por conta do jackson
     * */
    @Deprecated
    public NovaPerguntaRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(this.titulo, usuario, produto);
    }
}
