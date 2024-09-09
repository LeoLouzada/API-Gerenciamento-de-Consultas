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

import com.leolouzada.Consultas_Medicas_API.dto.PacienteDto;
import com.leolouzada.Consultas_Medicas_API.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@GetMapping
	public ResponseEntity<List<PacienteDto>> getAll() {
		return ResponseEntity.ok(pacienteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacienteDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(pacienteService.findById(id));
	}

	@PostMapping
	public ResponseEntity<PacienteDto> create(@RequestBody PacienteDto pacienteDto) {
		return ResponseEntity.ok(pacienteService.create(pacienteDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PacienteDto> update(@PathVariable Long id, @RequestBody PacienteDto pacienteDto) {
		return ResponseEntity.ok(pacienteService.update(id, pacienteDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pacienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
