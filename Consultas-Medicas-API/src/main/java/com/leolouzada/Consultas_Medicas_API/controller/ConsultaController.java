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

import com.leolouzada.Consultas_Medicas_API.dto.ConsultaDto;
import com.leolouzada.Consultas_Medicas_API.service.ConsultaService;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@GetMapping
	public ResponseEntity<List<ConsultaDto>> getAllConsultas() {
		List<ConsultaDto> consultas = consultaService.findAll();
		return ResponseEntity.ok(consultas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Long id) {
		ConsultaDto consulta = consultaService.findById(id);
		return consulta != null ? ResponseEntity.ok(consulta) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<ConsultaDto> createConsulta(@RequestBody ConsultaDto consultaDto) {
		ConsultaDto createdConsulta = consultaService.save(consultaDto);
		return ResponseEntity.ok(createdConsulta);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConsultaDto> updateConsulta(@PathVariable Long id, @RequestBody ConsultaDto consultaDto) {
		consultaDto.setId(id);
		ConsultaDto updatedConsulta = consultaService.save(consultaDto);
		return ResponseEntity.ok(updatedConsulta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
		consultaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
