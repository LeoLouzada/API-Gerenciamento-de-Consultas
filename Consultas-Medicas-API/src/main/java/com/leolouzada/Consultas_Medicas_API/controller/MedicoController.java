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

import com.leolouzada.Consultas_Medicas_API.dto.MedicoDto;
import com.leolouzada.Consultas_Medicas_API.service.MedicoService;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@GetMapping
	public ResponseEntity<List<MedicoDto>> getAllMedicos() {
		List<MedicoDto> medicos = medicoService.findAll();
		return ResponseEntity.ok(medicos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MedicoDto> getMedicoById(@PathVariable Long id) {
		MedicoDto medico = medicoService.findById(id);
		return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<MedicoDto> createMedico(@RequestBody MedicoDto medicoDTO) {
		MedicoDto createdMedico = medicoService.save(medicoDTO);
		return ResponseEntity.ok(createdMedico);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MedicoDto> updateMedico(@PathVariable Long id, @RequestBody MedicoDto medicoDTO) {
		medicoDTO.setId(id);
		MedicoDto updatedMedico = medicoService.save(medicoDTO);
		return ResponseEntity.ok(updatedMedico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
		medicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
