package br.com.orangetalents.mercadolivre.cadastronovoproduto.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.CaracteristicaProduto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Column(length = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    @Valid
    private Categoria categoria;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate instante = LocalDate.now();

    @NotNull
    @Valid
    @ManyToOne
    private Usuario responsavel;

    public Produto(@NotBlank String nome,
                   @NotNull @Digits(integer = 6, fraction = 2) @DecimalMin("0.01") BigDecimal preco,
                   @PositiveOrZero @NotNull Integer quantidade,
                   @Size(min = 3) Collection<NovaCaracteristicaRequest> caracteristicas,
                   @NotBlank String descricao, @NotNull Categoria categoria, @NotNull @Valid Usuario responsavel) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", características=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instante=" + instante +
                ", responsavel=" + responsavel +
                '}';
    }
}
