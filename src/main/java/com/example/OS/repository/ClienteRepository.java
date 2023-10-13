package com.example.OS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OS.domain.Cliente;
import com.example.OS.domain.Tecnico;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.cpf  = :cpf")
	Tecnico findByCPF(String cpf);

}
