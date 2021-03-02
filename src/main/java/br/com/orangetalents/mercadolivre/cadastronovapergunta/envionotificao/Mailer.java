package br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    /*
     * @param body
     * @param subject
     * @param emailFrom
     * @param emailTo
     * */
    void send(@NotBlank String body,
              @NotBlank String subject,
              @NotBlank @Email String emailFrom,
              @NotBlank @Email String emailTo);
}
