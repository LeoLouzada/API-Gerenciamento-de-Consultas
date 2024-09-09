package com.leolouzada.Consultas_Medicas_API.service;

import java.util.List;

import com.leolouzada.Consultas_Medicas_API.dto.HistoricoMedicoDto;

public interface HistoricoMedicoService {
	List<HistoricoMedicoDto> findAll();

	HistoricoMedicoDto findById(Long id);

	HistoricoMedicoDto save(HistoricoMedicoDto historicoMedicoDto);

	void deleteById(Long id);
}
