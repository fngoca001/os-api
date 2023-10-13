package com.example.OS.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.example.OS.domain.Tecnico;

import lombok.Data;

@Data
public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CPF é requerido!")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;

	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();
	}

}
