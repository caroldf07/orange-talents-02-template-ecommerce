package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
