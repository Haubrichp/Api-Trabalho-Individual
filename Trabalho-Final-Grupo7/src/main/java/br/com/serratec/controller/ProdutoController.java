package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.entity.Produto;
import br.com.serratec.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public List<ProdutoRequestDTO> listar() {
		return service.listar();
	}	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@Valid @RequestBody Produto produto) {
		return service.inserir(produto);
	}
	@PostMapping("/inserirVarios")
	public List<Produto> inserirVarios(@Valid @RequestBody List<Produto> produtos) {
	    return service.inserirVarios(produtos);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Produto> atualizar(Long id ,@RequestBody Produto produto) {
		if (service.equals(id)) {
	        service.atualizar(id, produto);
	        return service.atualizar(id, produto);
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
	}
}
