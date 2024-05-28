package br.com.serratec.dto;

import java.time.LocalDate;

import br.com.serratec.entity.Categoria;
import br.com.serratec.entity.Produto;

public class ProdutoRequestDTO {

	private String nome;
	private String descricao;
	private Double quantidadeEstoque;
	private LocalDate dataCadastro;
	private Double valor;
	private Categoria categoria;

	public ProdutoRequestDTO(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidadeEstoque = produto.getQuantidadeEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valor = produto.getValor();
		this.categoria = produto.getCategoria();
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
