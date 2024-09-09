package com.leolouzada.Consultas_Medicas_API.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolouzada.Consultas_Medicas_API.dto.HistoricoMedicoDto;
import com.leolouzada.Consultas_Medicas_API.entity.HistoricoMedico;
import com.leolouzada.Consultas_Medicas_API.entity.Medico;
import com.leolouzada.Consultas_Medicas_API.entity.Paciente;
import com.leolouzada.Consultas_Medicas_API.repository.HistoricoMedicoRepository;
import com.leolouzada.Consultas_Medicas_API.repository.MedicoRepository;
import com.leolouzada.Consultas_Medicas_API.repository.PacienteRepository;
import com.leolouzada.Consultas_Medicas_API.service.HistoricoMedicoService;

@Service
public class HistoricoMedicoServiceImpl implements HistoricoMedicoService {

	@Autowired
	private HistoricoMedicoRepository historicoMedicoRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<HistoricoMedicoDto> findAll() {
		return historicoMedicoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public HistoricoMedicoDto findById(Long id) {
		Optional<HistoricoMedico> historicoMedico = historicoMedicoRepository.findById(id);
		return historicoMedico.map(this::convertToDTO).orElse(null);
	}

	@Override
	public HistoricoMedicoDto save(HistoricoMedicoDto historicoMedicoDto) {
		HistoricoMedico historicoMedico = convertToEntity(historicoMedicoDto);
		HistoricoMedico savedHistoricoMedico = historicoMedicoRepository.save(historicoMedico);
		return convertToDTO(savedHistoricoMedico);
	}

	@Override
	public void deleteById(Long id) {
		historicoMedicoRepository.deleteById(id);
	}

	private HistoricoMedicoDto convertToDTO(HistoricoMedico historicoMedico) {
		HistoricoMedicoDto dto = new HistoricoMedicoDto();
		dto.setId(historicoMedico.getId());
		dto.setPacienteId(historicoMedico.getPaciente().getId());
		dto.setMedicoId(historicoMedico.getMedico().getId());
		dto.setDataConsulta(historicoMedico.getDataConsulta());
		dto.setDescricao(historicoMedico.getDescricao());
		return dto;
	}

	private HistoricoMedico convertToEntity(HistoricoMedicoDto dto) {
		HistoricoMedico historicoMedico = new HistoricoMedico();
		historicoMedico.setId(dto.getId());

		Optional<Paciente> paciente = pacienteRepository.findById(dto.getPacienteId());
		paciente.ifPresent(historicoMedico::setPaciente);

		Optional<Medico> medico = medicoRepository.findById(dto.getMedicoId());
		medico.ifPresent(historicoMedico::setMedico);

		historicoMedico.setDataConsulta(dto.getDataConsulta());
		historicoMedico.setDescricao(dto.getDescricao());
		return historicoMedico;
	}
}
