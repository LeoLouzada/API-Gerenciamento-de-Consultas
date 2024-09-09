package com.leolouzada.Consultas_Medicas_API.service;

import java.util.List;

import com.leolouzada.Consultas_Medicas_API.dto.ConsultaDto;

public interface ConsultaService {
	List<ConsultaDto> findAll();

	ConsultaDto findById(Long id);

	ConsultaDto save(ConsultaDto consultaDto);

	void deleteById(Long id);
}
