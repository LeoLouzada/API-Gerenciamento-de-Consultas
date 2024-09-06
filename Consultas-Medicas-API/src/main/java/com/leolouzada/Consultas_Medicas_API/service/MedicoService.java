package com.leolouzada.Consultas_Medicas_API.service;

import java.util.List;

import com.leolouzada.Consultas_Medicas_API.dto.MedicoDto;

public interface MedicoService {
	List<MedicoDto> findAll();

	MedicoDto findById(Long id);

	MedicoDto save(MedicoDto medicoDto);

	void deleteById(Long id);
}