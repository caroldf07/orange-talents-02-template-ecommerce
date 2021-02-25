package br.com.orangetalents.mercadolivre.compartilhado.seguranca;

public class TokenResponse {

    private String token;
    private String tipo;

    public TokenResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    /*
     * Criado apenas por conta do jackson
     * */
    @Deprecated
    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
