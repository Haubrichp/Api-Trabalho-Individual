package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	
}


