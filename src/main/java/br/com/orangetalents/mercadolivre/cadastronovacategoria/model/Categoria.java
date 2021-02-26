package br.com.orangetalents.mercadolivre.cadastronovacategoria.model;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.view.NovaCategoriaResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Categoria nomeCategoriaMae;

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    /*
     * Criado para o jackson
     * */
    @Deprecated
    public Categoria() {
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public Categoria getNomeCategoriaMae() {
        return nomeCategoriaMae;
    }

    /*
     * Caso a categoria tenha uma mãe, setaremos pelo Id e retorna o nome da mãe
     * */
    public void setNomeCategoriaMae(Categoria nomeCategoriaMae) {
        this.nomeCategoriaMae = nomeCategoriaMae;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nomeCategoriaMae='" + nomeCategoriaMae + '\'' +
                '}';
    }

    /*
     * Retorno para o usuário após a criação de uma nova categoria
     * */
    public NovaCategoriaResponse fromModelToResponse() {
        return new NovaCategoriaResponse(this.nome, this.nomeCategoriaMae);
    }

}
