package com.example.OS.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.OS.domain.Cliente;
import com.example.OS.dto.ClienteDTO;
import com.example.OS.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {

		ClienteDTO objDTO = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDTO = clienteService.findAll().stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);

	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente newTecnico = clienteService.create(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTecnico.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO tecnicoDTO) {
		ClienteDTO newtecnicoDTO = new ClienteDTO(clienteService.update(id, tecnicoDTO));
		return ResponseEntity.ok().body(newtecnicoDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
