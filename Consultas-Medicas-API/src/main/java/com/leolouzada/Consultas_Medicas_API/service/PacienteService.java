package com.leolouzada.Consultas_Medicas_API.service;

import java.util.List;

import com.leolouzada.Consultas_Medicas_API.dto.PacienteDto;

public interface PacienteService {
	List<PacienteDto> findAll();

	PacienteDto findById(Long id);

	PacienteDto create(PacienteDto pacienteDto);

	PacienteDto update(Long id, PacienteDto pacienteDto);

	void delete(Long id);
}