package com.example.OS.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.example.OS.domain.OS;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OSDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "dd/mm/yyyy HH:mm")
	private LocalDateTime dataAbertura;

	@JsonFormat(pattern = "dd/mm/yyyy HH:mm")
	private LocalDateTime dataFechamento;

	private Integer prioridade;

	@NotEmpty(message = "O campo Observações é requerido.")
	private String observacao;

	private Integer status;
	private Long tecnico;
	private Long cliente;

	public OSDTO() {
		super();
	}

	public OSDTO(OS os) {
		super();
		this.id = os.getId();
		this.dataAbertura = os.getDataAbertura();
		this.dataFechamento = os.getDataFechamento();
		this.prioridade = os.getPrioridade().getCod();
		this.observacao = os.getObservacao();
		this.status = os.getStatus().getCod();
		this.tecnico = os.getTecnico().getId();
		this.cliente = os.getCliente().getId();
	}

}
