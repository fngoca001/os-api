package com.example.OS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OS.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

	@Query("SELECT tecnico FROM Tecnico tecnico WHERE tecnico.cpf  = :cpf")
	Tecnico findByCPF(String cpf);

}
