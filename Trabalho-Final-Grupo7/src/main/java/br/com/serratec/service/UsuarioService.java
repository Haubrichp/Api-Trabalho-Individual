package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;
import br.com.serratec.configuration.MailConfig;
import br.com.serratec.entity.Endereco;
import br.com.serratec.entity.Usuario;
import br.com.serratec.exception.EmailException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ViaCepService viaCepService;

	@Autowired
	private MailConfig mailConfig;

	public UsuarioService(UsuarioRepository usuarioRepository, ViaCepService viaCepService) {
		this.repository = usuarioRepository;
		this.viaCepService = viaCepService;
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}
	
	@Transactional
    public Usuario inserir(Usuario usuario) throws MessagingException {

		if (repository.findByEmail(usuario.getEmail()) != null) {
			throw new EmailException("Email Já Existe na Base");
		}
		
        Endereco enderecoCompleto = viaCepService.buscarEnderecoPorCep(usuario.getEndereco().getCep());
        enderecoCompleto.setNumero(usuario.getEndereco().getNumero());
        enderecoCompleto.setComplemento(usuario.getEndereco().getComplemento());
        usuario.setEndereco(enderecoCompleto);
        
        Context context = new Context();
		context.setVariable("nome", usuario.getNome());

		try {
			mailConfig.sendMail(usuario.getEmail(), "Cadastro de Usuário no Sistema", "emailTemplate", context);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		 return repository.save(usuario);
    }

//  Feito primeiramente pelo Felipe para comportar o envio do e-mail.		
//	
//	@Transactional
//	public UsuarioResponseDTO inserir(UsuarioResponseDTO usuario) throws MessagingException {
//		if (repository.findByEmail(usuario.getEmail()) != null) {
//			throw new EmailException("Email Já Existe na Base");
//		}
//
//		Usuario u = new Usuario();
//		u.setNome(usuario.getNome());
//		u.setTelefone(usuario.getTelefone());
//		u.setEmail(usuario.getEmail());
//		u.setCpf(usuario.getCpf());
//
//		u.setSenha(usuario.getSenha());
//	
//		repository.save(u);
//
//		Context context = new Context();
//		context.setVariable("nome", u.getNome());
//
//		try {
//			mailConfig.sendMail(u.getEmail(), "Cadastro de Usuário no Sistema", "emailTemplate", context);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//
//		return new UsuarioResponseDTO(u);
//	}
	
	public Usuario atualizar(Long id, @Valid @RequestBody Usuario usuario) {
		if (repository.existsById(id)) {
			usuario.setId(id);
			return repository.save(usuario);
		}
		throw new ResourceNotFoundException("Cliente não encontrado");
	}

	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok("Usuario com id " + id + " foi excluído com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com id " + id + " não encontrado.");
	}
	
}
