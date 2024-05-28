package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.entity.Categoria;
import br.com.serratec.repository.CategoriaRepository;
import jakarta.validation.Valid;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> listar() {
		
		return repository.findAll();
	}
	
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return repository.save(categoria);
	
}
	
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id,@Valid @RequestBody Categoria categoria) {
	    if (repository.existsById(id)) {
	        categoria.setId(id);
	        return ResponseEntity.ok(repository.save(categoria));
	    }
	    return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<String> deletar(@PathVariable Long id){
	    if(repository.existsById(id)) {
	        repository.deleteById(id);
	        return ResponseEntity.ok("Categoria com id " + id + " foi excluído com sucesso.");
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria com id " + id + " não encontrado.");
	}
}