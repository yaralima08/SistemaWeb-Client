package br.ufscar.dc.compraevenda.client.service;

import br.ufscar.dc.compraevenda.client.model.Produto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProdutoService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Produto> listarTodos() {
        ResponseEntity<List<Produto>> response = restTemplate.exchange(
            apiBaseUrl + "/produtos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Produto>>() {}
        );
        return response.getBody();
    }

    public Produto buscarPorId(Long id) {
        return restTemplate.getForObject(
            apiBaseUrl + "/produtos/{id}", 
            Produto.class, 
            id
        );
    }

    public Produto criar(Produto produto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
        
        return restTemplate.postForObject(
            apiBaseUrl + "/produtos",
            request,
            Produto.class
        );
    }

    public Produto atualizar(Long id, Produto produto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
        
        restTemplate.exchange(
            apiBaseUrl + "/produtos/{id}",
            HttpMethod.PUT,
            request,
            Produto.class,
            id
        );
        return produto;
    }

    public void deletar(Long id) {
        restTemplate.delete(apiBaseUrl + "/produtos/{id}", id);
    }
}
