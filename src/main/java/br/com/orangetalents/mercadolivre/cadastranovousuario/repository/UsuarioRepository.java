package br.com.orangetalents.mercadolivre.cadastranovousuario.repository;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(@Email String email);
}
