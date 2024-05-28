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

import br.com.serratec.entity.Pedido;
import br.com.serratec.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@GetMapping
	public List<Pedido> listar() {
		return service.listar();
	}	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido inserir(@Valid @RequestBody Pedido pedido) {
		return service.inserir(pedido);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pedido> atualizar(Long id ,@RequestBody Pedido pedido) {
		if (service.equals(id)) {
	        service.atualizar(id, pedido);
	        return service.atualizar(id, pedido);
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
