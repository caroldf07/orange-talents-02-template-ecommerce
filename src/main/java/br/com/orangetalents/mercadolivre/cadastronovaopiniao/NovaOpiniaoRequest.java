package br.com.orangetalents.mercadolivre.cadastronovaopiniao;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovaopiniao.model.Opiniao;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaOpiniaoRequest {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(@Min(value = 1) @Max(value = 5) Integer nota,
                              @NotBlank String titulo,
                              @NotBlank @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    /*
     * Criado apenas por conta do Jackson
     * */
    @Deprecated
    public NovaOpiniaoRequest() {
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuarioLogado) {
        return new Opiniao(this.nota, this.titulo, this.descricao, usuarioLogado, produto);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "NovaOpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
