package br.com.serratec.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message= "Nome não pode ser nulo ou vazio.")
	@Size(max = 150, message = "O Nome não pode ter mais de 150 caracteres")
	@Size(min= 2, message = "O Nome não pode ter menos de 2 caracteres" )
	private String nome;
	@NotBlank(message= "Descrição não pode ser nulo ou vazio.")
	@Size(max = 300, message = "A descrição não pode ter mais de 300 caracteres")
	private String descricao;
	
	
	
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
