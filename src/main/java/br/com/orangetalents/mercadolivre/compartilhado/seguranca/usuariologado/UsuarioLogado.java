package br.com.orangetalents.mercadolivre.compartilhado.seguranca.usuariologado;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class UsuarioLogado extends Usuario {

    private User springUserDetails;

    private Usuario usuario;

    public UsuarioLogado(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
        springUserDetails = new User(usuario.getEmail(), usuario.getSenha(), List.of());
    }

    public Usuario get() {
        return usuario;
    }
}
