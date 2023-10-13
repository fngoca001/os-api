package com.example.OS.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OS.domain.Pessoa;
import com.example.OS.domain.Cliente;
import com.example.OS.dto.ClienteDTO;
import com.example.OS.repository.PessoaRepository;
import com.example.OS.repository.ClienteRepository;
import com.example.OS.services.exception.DataIntegratyViolaionException;
import com.example.OS.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository tecnicoRepository;
	
	@Autowired PessoaRepository pessoaRepository;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO tecnicoDTO) {
		if (findByCPF(tecnicoDTO) != null) {
			throw new DataIntegratyViolaionException("CPE já cadastrado na base de dados!");
		}
		return tecnicoRepository
				.save(new Cliente(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}

	public Cliente update(Long id, @Valid ClienteDTO tecnicoDTO) {
		Cliente oldtecnico = findById(id);
		if (findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolaionException("CPE já cadastrado na base de dados!");
		}

		oldtecnico.setNome(tecnicoDTO.getNome());
		oldtecnico.setCpf(tecnicoDTO.getCpf());
		oldtecnico.setTelefone(tecnicoDTO.getTelefone());

		return tecnicoRepository.save(oldtecnico);
	}

	public void delete(Long id) {
		Cliente tecnico = findById(id);
		if (tecnico.getList().size() > 0) {
			throw new DataIntegratyViolaionException("Cliente possui Ordens de serviço, não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
	}

	private Pessoa findByCPF(ClienteDTO tecnicoDTO) {
		Pessoa tecnico = pessoaRepository.findByCPF(tecnicoDTO.getCpf());
		if (tecnico != null) {
			return tecnico;
		}
		return null;
	}

}
