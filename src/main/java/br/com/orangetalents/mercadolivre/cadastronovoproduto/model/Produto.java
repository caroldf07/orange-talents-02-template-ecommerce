package br.com.orangetalents.mercadolivre.cadastronovoproduto.model;

import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.Caracteristica;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    @DecimalMin("0.01")
    private BigDecimal preco;

    @PositiveOrZero
    @NotNull
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    @Size(min = 3)
    private List<Caracteristica> características;

    @NotBlank
    @Column(length = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate instante = LocalDate.now();

    public Produto(@NotBlank String nome,
                   @NotNull @Digits(integer = 6, fraction = 2) @DecimalMin("0.01") BigDecimal preco,
                   @PositiveOrZero @NotNull Integer quantidade,
                   @Size(min = 3) List<Caracteristica> características,
                   @NotBlank String descricao, @NotNull Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.características = características;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", características=" + características +
                ", descricao='" + descricao + '\'' +
                    categoria +
                ", instante=" + instante +
                '}';
    }
}
