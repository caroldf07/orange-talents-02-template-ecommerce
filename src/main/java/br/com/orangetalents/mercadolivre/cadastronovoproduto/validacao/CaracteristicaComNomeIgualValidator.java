package br.com.orangetalents.mercadolivre.cadastronovoproduto.validacao;

import br.com.orangetalents.mercadolivre.cadastronovoproduto.NovoProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class CaracteristicaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovoProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NovoProdutoRequest produtoRequest = (NovoProdutoRequest) o;
        Set<String> temCaracteristicasIguais = produtoRequest.temCaracteristicasIguais();

        if (!temCaracteristicasIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Essa característica já está cadastrada");
        }
    }
}
