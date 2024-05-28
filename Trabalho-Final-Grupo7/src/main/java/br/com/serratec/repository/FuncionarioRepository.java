package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	

}


