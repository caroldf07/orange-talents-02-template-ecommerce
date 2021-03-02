package br.com.orangetalents.mercadolivre.cadastranovousuario.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.FormatoDataCriacao;
import br.com.orangetalents.mercadolivre.cadastronovaopiniao.model.Opiniao;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    @FormatoDataCriacao //formata a data que vem tanto por JSON como por form
    @PastOrPresent
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "responsavel", orphanRemoval = true) //Se usuário for deletado, os produtos dele também serão
    @Valid
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario", orphanRemoval = false) //Se usuário for deletado, as opiniões dele permanecem
    @Valid
    private List<Opiniao> opinioes;

    @OneToMany(mappedBy = "pessoaInteressada", orphanRemoval = false)
    @Valid
    private List<Pergunta> perguntas;

    public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    /*
     * Construtor vazio criado por causa do jackson e não tem o deprecated por conta do UsuarioLogado para fins de
     * capturar as informações do usuário durante a sessão
     * */
    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", email='" + email + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
