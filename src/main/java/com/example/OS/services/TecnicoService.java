package com.example.OS.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OS.domain.Pessoa;
import com.example.OS.domain.Tecnico;
import com.example.OS.dto.TecnicoDTO;
import com.example.OS.repository.PessoaRepository;
import com.example.OS.repository.TecnicoRepository;
import com.example.OS.services.exception.DataIntegratyViolaionException;
import com.example.OS.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired PessoaRepository pessoaRepository;

	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		if (findByCPF(tecnicoDTO) != null) {
			throw new DataIntegratyViolaionException("CPE já cadastrado na base de dados!");
		}
		return tecnicoRepository
				.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}

	public Tecnico update(Long id, @Valid TecnicoDTO tecnicoDTO) {
		Tecnico oldtecnico = findById(id);
		if (findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolaionException("CPE já cadastrado na base de dados!");
		}

		oldtecnico.setNome(tecnicoDTO.getNome());
		oldtecnico.setCpf(tecnicoDTO.getCpf());
		oldtecnico.setTelefone(tecnicoDTO.getTelefone());

		return tecnicoRepository.save(oldtecnico);
	}

	public void delete(Long id) {
		Tecnico tecnico = findById(id);
		if (tecnico.getList().size() > 0) {
			throw new DataIntegratyViolaionException("Técnico possui Ordens de serviço, não pode ser deletado!");
		}
		tecnicoRepository.deleteById(id);
	}

	private Pessoa findByCPF(TecnicoDTO tecnicoDTO) {
		Pessoa tecnico = pessoaRepository.findByCPF(tecnicoDTO.getCpf());
		if (tecnico != null) {
			return tecnico;
		}
		return null;
	}

}
