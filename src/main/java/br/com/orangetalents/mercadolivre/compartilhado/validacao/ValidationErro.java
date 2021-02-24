package br.com.orangetalents.mercadolivre.compartilhado.validacao;

public class ValidationErro {
    private String campo;
    private String mensagem;

    public ValidationErro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    /*
     * Getters para o jackson
     * */
    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
