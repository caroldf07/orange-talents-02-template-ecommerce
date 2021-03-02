package br.com.orangetalents.mercadolivre.cadastronovapergunta.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.FormatoDataCriacao;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @FormatoDataCriacao
    private LocalDate instante;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario pessoaInteressada;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario pessoaInteressada,
                    @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.pessoaInteressada = pessoaInteressada;
        this.produto = produto;
        this.instante = LocalDate.now();
    }

    /*
     * Criado por conta do Jackson
     * */
    public Pergunta() {
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo='" + titulo + '\'' +
                ", instante=" + instante +
                '}';
    }

    public Usuario getPessoaInteressada() {
        return pessoaInteressada;
    }

    public Produto getProduto() {
        return produto;
    }
}
