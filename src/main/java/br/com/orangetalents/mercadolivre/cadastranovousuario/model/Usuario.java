package br.com.orangetalents.mercadolivre.cadastranovousuario.model;

import br.com.orangetalents.mercadolivre.cadastranovousuario.validacao.FormatoDataCriacao;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {


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


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }

    /*
    * Construtor vazio criado por causa do jackson
    * */
    @Deprecated
    public Usuario() {
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
}
