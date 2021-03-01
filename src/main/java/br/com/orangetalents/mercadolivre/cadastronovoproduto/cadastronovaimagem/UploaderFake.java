package br.com.orangetalents.mercadolivre.cadastronovoproduto.cadastronovaimagem;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {

    /*
     * Transforma a imagem em uma url a partir de onde ela está hospedada
     * */
    public static Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem ->
                "http://bucket.io/" +
                        imagem.getOriginalFilename() + "-" +
                        UUID.randomUUID().toString()).collect(Collectors.toSet());
    }
}
