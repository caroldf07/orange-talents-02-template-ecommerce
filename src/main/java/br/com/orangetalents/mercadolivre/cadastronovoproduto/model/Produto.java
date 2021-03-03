package br.com.orangetalents.mercadolivre.cadastronovoproduto.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovacategoria.model.Categoria;
import br.com.orangetalents.mercadolivre.cadastronovaopiniao.model.Opiniao;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.NovaCaracteristicaRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovacaracteristica.model.CaracteristicaProduto;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem.model.ImagemProduto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
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
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Column(length = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    @Valid
    private Categoria categoria;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate instante;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario responsavel;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    // A imagem só é persistida quando o produto é atualizado
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Pergunta> perguntas = new HashSet<>();

    public Produto(@NotBlank String nome,
                   @NotNull @Digits(integer = 6, fraction = 2) @DecimalMin("0.01") BigDecimal preco,
                   @PositiveOrZero @NotNull Integer quantidade,
                   @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas,
                   @NotBlank String descricao, @NotNull Categoria categoria, @NotNull @Valid Usuario responsavel) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.responsavel = responsavel;
        this.instante = LocalDate.now();

        Assert.isTrue(this.caracteristicas.size() >= 3,
                "Todo produto precisa ter no mínimo 3 ou mais características");
    }

    /*
     * Criado apenas por conta do Jackson
     * */
    @Deprecated
    public Produto() {
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instante=" + instante +
                ", responsavel=" + responsavel +
                ", imagens=" + imagens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public void associaImagem(Set<String> links) {

        /*
         * Passo por toda a lista de links (que já foram transformados pelo uploaderFake,
         * Pelo controller, já sei que é o produto para o qual vou vincular a imagem,
         * então, pelo map, instancio a imagem no produto e vou enchendo a listagem de imagens com as URL de cada uma
         * das imagens.
         * */
        Set<ImagemProduto> imagem = links.stream().
                map(link -> new ImagemProduto(this, link)).
                collect(Collectors.toSet());

        this.imagens.addAll(imagem);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpinioes(Function<Opiniao, T> funcaoMapeador) {
        return this.opinioes.stream().map(funcaoMapeador).collect(Collectors.toSet());
    }

    public boolean abateDoEstoque(@Positive Integer quantidadeAComprar) {
        if (quantidadeAComprar <= this.quantidade) {
            quantidade -= quantidadeAComprar;
            return true;
        }

        return false;
    }
}
