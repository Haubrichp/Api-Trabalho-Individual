package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public List<Funcionario> listar() {

		return repository.findAll();
	}

	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
		return repository.save(funcionario);

	}

	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
		if (repository.existsById(id)) {
			funcionario.setId(id);
			return ResponseEntity.ok(repository.save(funcionario));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<String> deletar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok("Funcionario com id " + id + " foi excluído com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario com id " + id + " não encontrado.");
	}

}
