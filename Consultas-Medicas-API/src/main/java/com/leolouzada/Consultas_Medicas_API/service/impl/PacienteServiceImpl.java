package com.leolouzada.Consultas_Medicas_API.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolouzada.Consultas_Medicas_API.dto.PacienteDto;
import com.leolouzada.Consultas_Medicas_API.entity.Paciente;
import com.leolouzada.Consultas_Medicas_API.repository.PacienteRepository;
import com.leolouzada.Consultas_Medicas_API.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<PacienteDto> findAll() {
		return pacienteRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public PacienteDto findById(Long id) {
		return pacienteRepository.findById(id).map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
	}

	@Override
	public PacienteDto create(PacienteDto pacienteDto) {
		Paciente paciente = convertToEntity(pacienteDto);
		Paciente savedPaciente = pacienteRepository.save(paciente);
		return convertToDTO(savedPaciente);
	}

	@Override
	public PacienteDto update(Long id, PacienteDto pacienteDto) {
		Paciente paciente = convertToEntity(pacienteDto);
		paciente.setId(id);
		Paciente updatedPaciente = pacienteRepository.save(paciente);
		return convertToDTO(updatedPaciente);
	}

	@Override
	public void delete(Long id) {
		pacienteRepository.deleteById(id);
	}

	private PacienteDto convertToDTO(Paciente paciente) {
		PacienteDto dto = new PacienteDto();
		dto.setId(paciente.getId());
		dto.setNome(paciente.getNome());
		dto.setCpf(paciente.getCpf());
		dto.setTelefone(paciente.getTelefone());
		return dto;
	}

	private Paciente convertToEntity(PacienteDto dto) {
		Paciente paciente = new Paciente();
		paciente.setNome(dto.getNome());
		paciente.setCpf(dto.getCpf());
		paciente.setTelefone(dto.getTelefone());
		return paciente;
	}
}
