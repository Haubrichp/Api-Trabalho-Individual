package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.entity.Pedido;
import br.com.serratec.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	
	public List<Pedido> listar() {
		
		return repository.findAll();
	}
	
	public Pedido inserir(@Valid @RequestBody Pedido pedido) {
		return repository.save(pedido);
	
}
	
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id,@Valid @RequestBody Pedido pedido) {
	    if (repository.existsById(id)) {
	        pedido.setId(id);
	        return ResponseEntity.ok(repository.save(pedido));
	    }
	    return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<String> deletar(@PathVariable Long id){
	    if(repository.existsById(id)) {
	        repository.deleteById(id);
	        return ResponseEntity.ok("Pedido com id " + id + " foi excluído com sucesso.");
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido com id " + id + " não encontrado.");
	}

	
}
