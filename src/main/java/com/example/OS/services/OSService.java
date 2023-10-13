package com.example.OS.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OS.domain.Cliente;
import com.example.OS.domain.OS;
import com.example.OS.domain.Tecnico;
import com.example.OS.domain.enuns.Prioridade;
import com.example.OS.domain.enuns.Status;
import com.example.OS.dto.OSDTO;
import com.example.OS.repository.OSRepository;
import com.example.OS.services.exception.ObjectNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OS findById(Long id) {
		Optional<OS> os = osRepository.findById(id);
		return os.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " ,Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return osRepository.findAll();
	}

	public OS create(@Valid OSDTO osdto) {
		return fromDTO(osdto);
	}
	
	public OS udate(@Valid OSDTO osdto) {
		findById(osdto.getId());
		return fromDTO(osdto);
	}

	private OS fromDTO(OSDTO osdto) {
		OS newOS = new OS();
		newOS.setId(osdto.getId());
		newOS.setObservacao(osdto.getObservacao());
		newOS.setPrioridade(Prioridade.toEnum(osdto.getPrioridade()));
		newOS.setStatus(Status.toEnum(osdto.getStatus()));

		Tecnico tecnico = tecnicoService.findById(osdto.getTecnico());
		Cliente cliente = clienteService.findById(osdto.getCliente());

		if(newOS.getStatus().getCod().equals(2)) {
			newOS.setDataFechamento(LocalDateTime.now());
		}
		
		newOS.setTecnico(tecnico);
		newOS.setCliente(cliente);
		return osRepository.save(newOS);
	}
	
	
}
