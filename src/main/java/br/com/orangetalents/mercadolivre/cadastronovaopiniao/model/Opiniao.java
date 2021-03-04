package br.com.orangetalents.mercadolivre.cadastronovaopiniao.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    public Opiniao(@Min(value = 1) @Max(value = 5) @NotNull Integer nota,
                   @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
                   @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    /*
     * Criado apenas por conta do Jackson
     * */
    @Deprecated
    public Opiniao() {
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return Objects.equals(nota, opiniao.nota) && Objects.equals(titulo, opiniao.titulo) && Objects.equals(descricao, opiniao.descricao) && Objects.equals(usuario, opiniao.usuario) && Objects.equals(produto, opiniao.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, titulo, descricao, usuario, produto);
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
}
