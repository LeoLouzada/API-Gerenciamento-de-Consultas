package com.leolouzada.Consultas_Medicas_API.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolouzada.Consultas_Medicas_API.dto.ConsultaDto;
import com.leolouzada.Consultas_Medicas_API.entity.Consulta;
import com.leolouzada.Consultas_Medicas_API.entity.Medico;
import com.leolouzada.Consultas_Medicas_API.entity.Paciente;
import com.leolouzada.Consultas_Medicas_API.repository.ConsultaRepository;
import com.leolouzada.Consultas_Medicas_API.repository.MedicoRepository;
import com.leolouzada.Consultas_Medicas_API.repository.PacienteRepository;
import com.leolouzada.Consultas_Medicas_API.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<ConsultaDto> findAll() {
		return consultaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ConsultaDto findById(Long id) {
		Optional<Consulta> consulta = consultaRepository.findById(id);
		return consulta.map(this::convertToDTO).orElse(null);
	}

	@Override
	public ConsultaDto save(ConsultaDto consultaDto) {
		Consulta consulta = convertToEntity(consultaDto);
		Consulta savedConsulta = consultaRepository.save(consulta);
		return convertToDTO(savedConsulta);
	}

	@Override
	public void deleteById(Long id) {
		consultaRepository.deleteById(id);
	}

	private ConsultaDto convertToDTO(Consulta consulta) {
		ConsultaDto dto = new ConsultaDto();
		dto.setId(consulta.getId());
		dto.setMedicoId(consulta.getMedico().getId());
		dto.setPacienteId(consulta.getPaciente().getId());
		dto.setDataHora(consulta.getDataHora());
		dto.setDescricao(consulta.getDescricao());
		return dto;
	}

	private Consulta convertToEntity(ConsultaDto dto) {
		Consulta consulta = new Consulta();
		consulta.setId(dto.getId());

		Optional<Medico> medico = medicoRepository.findById(dto.getMedicoId());
		medico.ifPresent(consulta::setMedico);

		Optional<Paciente> paciente = pacienteRepository.findById(dto.getPacienteId());
		paciente.ifPresent(consulta::setPaciente);

		consulta.setDataHora(dto.getDataHora());
		consulta.setDescricao(dto.getDescricao());
		return consulta;
	}
}
