package br.com.serratec.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.serratec.enums.RoleName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ElementCollection(targetClass = RoleName.class)
    @Enumerated(EnumType.STRING)
    private Set<RoleName> roles = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Nome não pode ser nulo ou vazio.")
	@Size(max = 150, message = "O Nome não pode ter mais de 150 caracteres")
	@Size(min= 2, message = "O Nome não pode ter menos de 2 caracteres" )
	private String nome;
	
	@NotBlank(message= "Telefone não pode ser nulo ou vazio.")
	@Column(length = 100)
	@Pattern(regexp= "^(\\([1-9]{2}\\))?\\s?(?:[2-8]|9[0-9])[0-9]{3}(?:-?[0-9]{4})$", message = "Telefone não está no padrão, tente no formato (xx) xxxxx-xxxx!")
	private String telefone;
	
	@NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
    message = "A senha deve conter pelo de 8 a 16 caracteres, incluindo letras maiúsculas, minúsculas e números.")
	private String senha;
	
	@NotBlank(message = "O email é obrigatório")
	@Email
	private String email;
	
	@CPF(message = "CPF inválido")
	private String cpf;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @JsonManagedReference
	private Endereco endereco;

/*
	@Override
	public String toString() {
		return "nome:" + nome +"email:" +email +"\n\n\n";
		
	*/
	
	
	public Long getId() {
		return id;
	}
	
	

	public Set<RoleName> getRoles() {
		return roles;
	}



	public void setRoles(Set<RoleName> roles) {
		this.roles = roles;
	}



	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	 

	@Override
	public String getPassword() {
		
		return this.senha;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}
	
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<>();
	    for (RoleName roleName : roles) {
	        authorities.add(new SimpleGrantedAuthority(roleName.name()));
	    }
	    return authorities;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}