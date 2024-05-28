package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.entity.Produto;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	
	public List<ProdutoRequestDTO> listar() {

        List<Produto> produtos = repository.findAll();

        return produtos.stream().map((produto) -> new ProdutoRequestDTO(produto)).collect(Collectors.toList());
    }

	public Produto inserir(@Valid @RequestBody Produto produto) {
		return repository.save(produto);
	}
		
		public List<Produto> inserirVarios(@Valid @RequestBody List<Produto> produtos) {
		    List<Produto> produtosInseridos = new ArrayList<>();
		    for (Produto produto : produtos) {
		        produtosInseridos.add(repository.save(produto));
		    }
		    return produtosInseridos;
		}

	

	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		if (repository.existsById(id)) {
			produto.setId(id);
			return ResponseEntity.ok(repository.save(produto));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<String> deletar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok("Produto com id " + id + " foi excluído com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com id " + id + " não encontrado.");
	}

}
