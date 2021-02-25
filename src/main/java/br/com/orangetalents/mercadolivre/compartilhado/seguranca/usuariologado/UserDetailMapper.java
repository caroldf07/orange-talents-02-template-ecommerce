package br.com.orangetalents.mercadolivre.compartilhado.seguranca.usuariologado;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailMapper {

    /**
     * @param shouldBeASystemUser um objeto que deveria representar seu usuário logado
     * @return
     */
    UserDetails map(Object shouldBeASystemUser);
}
