package com.leolouzada.Consultas_Medicas_API.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolouzada.Consultas_Medicas_API.dto.MedicoDto;
import com.leolouzada.Consultas_Medicas_API.entity.Medico;
import com.leolouzada.Consultas_Medicas_API.repository.MedicoRepository;
import com.leolouzada.Consultas_Medicas_API.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Override
	public List<MedicoDto> findAll() {
		return medicoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public MedicoDto findById(Long id) {
		Optional<Medico> medico = medicoRepository.findById(id);
		return medico.map(this::convertToDTO).orElse(null);
	}

	@Override
	public MedicoDto save(MedicoDto medicoDto) {
		Medico medico = convertToEntity(medicoDto);
		Medico savedMedico = medicoRepository.save(medico);
		return convertToDTO(savedMedico);
	}

	@Override
	public void deleteById(Long id) {
		medicoRepository.deleteById(id);
	}

	private MedicoDto convertToDTO(Medico medico) {
		MedicoDto dto = new MedicoDto();
		dto.setId(medico.getId());
		dto.setNome(medico.getNome());
		dto.setEspecialidade(medico.getEspecialidade());
		dto.setCrm(medico.getCrm());
		return dto;
	}

	private Medico convertToEntity(MedicoDto dto) {
		Medico medico = new Medico();
		medico.setId(dto.getId());
		medico.setNome(dto.getNome());
		medico.setEspecialidade(dto.getEspecialidade());
		medico.setCrm(dto.getCrm());
		return medico;
	}
}
