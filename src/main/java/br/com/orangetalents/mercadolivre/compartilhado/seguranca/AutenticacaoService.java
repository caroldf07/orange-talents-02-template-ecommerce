package br.com.orangetalents.mercadolivre.compartilhado.seguranca;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(login).get();

        if (usuario != null) {
            Assertions.assertNotNull(usuario, "Usuário veio nulo");
            return usuario;
        }

        throw new UsernameNotFoundException("Dados inválidos");
    }
}
