package com.example.OS.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OS.domain.Cliente;
import com.example.OS.domain.OS;
import com.example.OS.domain.Tecnico;
import com.example.OS.domain.enuns.Prioridade;
import com.example.OS.domain.enuns.Status;
import com.example.OS.repository.ClienteRepository;
import com.example.OS.repository.OSRepository;
import com.example.OS.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private OSRepository osRepository;

	public void intanciaDB() {

		Tecnico t1 = new Tecnico(null, "Valdir Ngoca", "144.785.300-84", "(88) 98888-8888");

		Cliente c1 = new Cliente(null, "Necha Ngoca", "598.508.200-80", "(88) 98888-8898");
		
		Tecnico t2 = new Tecnico(null, "Necha Ngoca", "598.508.200-80", "(88) 98888-8898");
		
		Tecnico t3 = new Tecnico(null, "nando Ngoca", "598.508.200-80", "(88) 98888-8898");
		
		Cliente c2 = new Cliente(null, "terra Ngoca", "598.508.200-80", "(88) 98888-8898");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste creiar OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
		tecnicoRepository.save(t2);
		clienteRepository.saveAll(Arrays.asList(c2));
		tecnicoRepository.saveAll(Arrays.asList(t3));
	}
}
