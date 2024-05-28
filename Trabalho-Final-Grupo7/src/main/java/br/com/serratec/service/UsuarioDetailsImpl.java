package br.com.serratec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Usuario;
import br.com.serratec.repository.UsuarioRepository;

@Service
public class UsuarioDetailsImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busque o usuário pelo nome de usuário no seu repositório
        Usuario usuario = repository.findByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new org.springframework.security.core.userdetails.User(
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getAuthorities()
        );
    }

	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

}
