package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem.model;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @URL
    @NotBlank
    private String linkImagem;

    public ImagemProduto(@NotNull @Valid Produto produto, @URL @NotBlank String linkImagem) {
        this.produto = produto;
        this.linkImagem = linkImagem;
    }

    /*
     * Criado somente por conta do Jackson
     * */
    @Deprecated
    public ImagemProduto() {
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", linkImagem='" + linkImagem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return Objects.equals(linkImagem, that.linkImagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkImagem);
    }

    public String getLinkImagem() {
        return linkImagem;
    }
}
