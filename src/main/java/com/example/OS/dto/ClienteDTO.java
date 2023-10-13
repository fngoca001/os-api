package com.example.OS.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.example.OS.domain.Cliente;

import lombok.Data;

@Data
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CPF é requerido!")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
	}

}
