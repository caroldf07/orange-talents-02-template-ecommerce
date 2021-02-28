package br.com.orangetalents.mercadolivre.compartilhado.seguranca;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastranovousuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {


    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = Optional.ofNullable(recuperarToken(httpServletRequest));
        if (token.isPresent() && tokenService.isTokenValido(token.get())) {
            Long idUsuario = tokenService.getIdUsuario(token);
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            Assertions.assertNotNull(idUsuario, "O id do usuário veio nulo");

            if (usuario != null) {
                UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario,
                        null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(autenticacao);
                Assertions.assertNotNull(usuario, "Usuário não existe");
            }
            Assertions.assertTrue(token.isPresent(), "O token veio vazio");
            Assertions.assertTrue(tokenService.isTokenValido(token.get()), "O token veio inválido");

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        Assertions.assertNotNull(token, "O token veio nulo");
        Assertions.assertTrue(!token.isEmpty(), "O token veio vazio");
        Assertions.assertTrue(token.startsWith("Bearer"), "O token não começa com Bearer");

        return token.substring(7);
    }
}
