package com.leolouzada.Consultas_Medicas_API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leolouzada.Consultas_Medicas_API.dto.HistoricoMedicoDto;
import com.leolouzada.Consultas_Medicas_API.service.HistoricoMedicoService;

@RestController
@RequestMapping("/api/historicos-medicos")
public class HistoricoMedicoController {

	@Autowired
	private HistoricoMedicoService historicoMedicoService;

	@GetMapping
	public ResponseEntity<List<HistoricoMedicoDto>> getAllHistoricosMedicos() {
		List<HistoricoMedicoDto> historicosMedicos = historicoMedicoService.findAll();
		return ResponseEntity.ok(historicosMedicos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HistoricoMedicoDto> getHistoricoMedicoById(@PathVariable Long id) {
		HistoricoMedicoDto historicoMedico = historicoMedicoService.findById(id);
		return historicoMedico != null ? ResponseEntity.ok(historicoMedico) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<HistoricoMedicoDto> createHistoricoMedico(
			@RequestBody HistoricoMedicoDto historicoMedicoDto) {
		HistoricoMedicoDto createdHistoricoMedico = historicoMedicoService.save(historicoMedicoDto);
		return ResponseEntity.ok(createdHistoricoMedico);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HistoricoMedicoDto> updateHistoricoMedico(@PathVariable Long id,
			@RequestBody HistoricoMedicoDto historicoMedicoDto) {
		historicoMedicoDto.setId(id);
		HistoricoMedicoDto updatedHistoricoMedico = historicoMedicoService.save(historicoMedicoDto);
		return ResponseEntity.ok(updatedHistoricoMedico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHistoricoMedico(@PathVariable Long id) {
		historicoMedicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
