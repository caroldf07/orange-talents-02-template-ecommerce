package br.com.orangetalents.mercadolivre.compartilhado.seguranca.usuariologado;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioLogadoMapper implements UserDetailMapper {
    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }
}
