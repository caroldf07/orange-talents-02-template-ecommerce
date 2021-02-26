package br.com.orangetalents.mercadolivre.cadastronovacategoria;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.ExistById;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.UniqueValue;
import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistById(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;


    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    /*
     * Criado apenas por conta do jackson
     * */
    @Deprecated
    public NovaCategoriaRequest() {
    }

    /*
     * Gerado para teste do toModel()
     * */
    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    @Override
    public String toString() {
        return "NovaCategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", idCategoriaMae=" + idCategoriaMae +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public Categoria toModel(EntityManager em) {
        Categoria categoria = new Categoria(this.nome);
        Categoria categoriaMae;

        if (this.idCategoriaMae != null) {
            categoriaMae = em.find(Categoria.class, idCategoriaMae);
            categoria.setNomeCategoriaMae(categoriaMae);

            Assertions.assertNotNull(idCategoriaMae, "O id categoria mãe está nulo");
        }

        return categoria;
    }
}
