package br.com.orangetalents.mercadolivre.cadastranovousuario.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.FormatoDataCriacao;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario {


    @PersistenceContext
    EntityManager em;
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

    public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    /*
     * Construtor vazio criado por causa do jackson
     * */
    @Deprecated
    public Usuario() {
    }

    @Autowired
    public EntityManager getEm() {
        return em;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(instanteCriacao, usuario.instanteCriacao) && Objects.equals(em, usuario.em);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, senha, instanteCriacao, em);
    }
}
