package com.example.OS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OS.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query("SELECT tecnico FROM Pessoa tecnico WHERE tecnico.cpf  = :cpf")
	Pessoa findByCPF(String cpf);
}
