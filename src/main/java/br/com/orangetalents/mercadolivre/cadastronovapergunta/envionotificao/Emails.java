package br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao;

import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.fechamentocompra.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private FakeMailer mailer;


    /*
     * Para envio de e-mail podemos utilizar APIs como o do Mandrill, mas para esse caso apenas criamos um mailer fake
     * para deixar a estrutura pronta e desacoplada, para quando precisar é só instanciar e configurar o serviço.
     * */
    public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
        mailer.send("<html>Teste</html>", "Título da pergunta",
                pergunta.getPessoaInteressada().getEmail(), pergunta.getProduto().getResponsavel().getEmail());
    }

    public void novaCompra(Compra compra) {
        mailer.send("<html>Teste</html>", "Nova compra",
                compra.getComprador().getEmail(), compra.getProduto().getResponsavel().getEmail());
    }

    public void compraFinalizadaSucesso(Compra compra) {
        mailer.send("<html>Teste</html>", "Compra finalizada com sucesso",
                compra.getProduto().getResponsavel().getEmail(),
                compra.getComprador().getEmail());
    }

    public void compraFinalizadaErro(Compra compra) {
        mailer.send("<html>Venda não finalizada</html>", "Compra deu erro, tente novamente",
                compra.getProduto().getResponsavel().getEmail(),
                compra.getComprador().getEmail());
    }
}
