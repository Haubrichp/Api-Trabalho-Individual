package br.com.serratec.dto;

import br.com.serratec.entity.Endereco;
import br.com.serratec.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioResponseDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message= "Telefone não pode ser nulo ou vazio.")
	@Column(length = 100)
	@Pattern(regexp= "^(\\([1-9]{2}\\))?\\s?(?:[2-8]|9[0-9])[0-9]{3}(?:-?[0-9]{4})$", message = "Telefone não está no padrão, tente no formato (xx) xxxxx-xxxx!")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
    message = "A senha deve conter pelo de 8 a 16 caracteres, incluindo letras maiúsculas, minúsculas e números.")
    private String senha;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @CPF(message = "CPF inválido")
    private String cpf;
    
    @NotBlank(message = "CEP é obrigatório")
	private String cep;
	
	@NotBlank(message = "Logradouro é obrigatório")
	private String logradouro;
	 
	@NotBlank(message = "Número é obrigatório")
	private String numero;
	
	@NotBlank(message = "Bairro é obrigatório")
	private String bairro;
	
	@NotBlank(message = "Localidade é obrigatória")
	private String localidade;
	
	@NotBlank(message = "UF é obrigatório")
	private String uf;
	
	private String complemento;
	
	@Autowired
	private Endereco endereco;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.cpf = usuario.getCpf();
        this.cep = usuario.getEndereco().getCep();
        this.logradouro = usuario.getEndereco().getLogradouro();
        this.numero = usuario.getEndereco().getNumero();
        this.bairro = usuario.getEndereco().getBairro();
        this.localidade = usuario.getEndereco().getLocalidade();
        this.uf = usuario.getEndereco().getUf();
        this.complemento = usuario.getEndereco().getComplemento(); 
    }

   
    public Long getId() {
        return id;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}

		