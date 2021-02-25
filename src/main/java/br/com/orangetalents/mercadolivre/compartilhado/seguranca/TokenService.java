package br.com.orangetalents.mercadolivre.compartilhado.seguranca;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private Long expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + expiration);

        return Jwts.builder().
                setIssuer("API do Mercado Livre").
                setSubject(logado.getId().toString()).
                setIssuedAt(hoje).
                setExpiration(expiracao).signWith(SignatureAlgorithm.HS256, secret).
                compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }

    public Long getIdUsuario(Optional<String> token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token.get()).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
