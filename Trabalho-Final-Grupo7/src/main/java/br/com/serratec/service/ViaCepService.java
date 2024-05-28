package br.com.serratec.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.serratec.entity.Endereco;

@Service
public class ViaCepService {

	private final RestTemplate restTemplate;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Endereco buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Endereco.class);
    }
}
