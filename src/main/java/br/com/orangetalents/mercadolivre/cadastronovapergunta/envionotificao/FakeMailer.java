package br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer{
    @Override
    public void send(String body, String subject, String emailFrom, String emailTo) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(emailFrom);
        System.out.println(emailTo);
    }
}
