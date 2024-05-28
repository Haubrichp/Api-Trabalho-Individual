package br.com.serratec.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Produto {

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
	@NotNull(message= "Quantidade de estoque não pode ser nulo ou vazio.")
	@Min(value = 0, message = "A quantidade não pode ser negativa")
    @Max(value = 1000, message = "A quantidade máxima permitida é 1000")
	private Double quantidadeEstoque;
	@PastOrPresent(message = "Data do pedido é uma data futura, atualizar para data atual.")
	private LocalDate dataCadastro;
	@NotNull(message= "Valor não pode ser nulo ou vazio.")
	@Min(value = 0, message = "O valor não pode ser negativo" )
	@Max(value = 10000, message = "O Valor máximo de um produto é de 10000")
	private Double valor;

	@JoinColumn(name = "id_categoria")
	@ManyToOne
	@JsonBackReference
	private Categoria categoria;
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
