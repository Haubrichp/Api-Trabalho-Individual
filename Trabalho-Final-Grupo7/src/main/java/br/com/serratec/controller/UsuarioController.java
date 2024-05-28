package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.entity.Usuario;
import br.com.serratec.service.UsuarioService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public List<Usuario> listar() {
		return service.listar();
	}
	
	@PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) throws MessagingException {
        Usuario usuarioSalvo = service.inserir(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

//  Feito primeiramente pelo Felipe para comportar o envio do e-mail.
//	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public UsuarioResponseDTO inserir(@Valid @RequestBody UsuarioResponseDTO usuario) throws MessagingException {
//		return service.inserir(usuario);
//	}
	
	@PutMapping("{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id ,@RequestBody Usuario usuario) {
	  
	    return ResponseEntity.ok(service.atualizar(id, usuario));
	}
	
	@DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
