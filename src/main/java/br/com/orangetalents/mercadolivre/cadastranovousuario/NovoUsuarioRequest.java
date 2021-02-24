package br.com.orangetalents.mercadolivre.cadastranovousuario;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.compartilhado.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class,fieldName = "email")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String email,
                              @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    /*
     * Construtor vazio criado por causa do Jackson
     * */
    @Deprecated
    public NovoUsuarioRequest() {
    }

    public Usuario toModel() {
        return new Usuario(this.email, this.senha);
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
