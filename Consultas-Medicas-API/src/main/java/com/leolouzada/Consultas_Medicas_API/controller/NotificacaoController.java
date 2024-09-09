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

import com.leolouzada.Consultas_Medicas_API.dto.NotificacaoDto;
import com.leolouzada.Consultas_Medicas_API.service.NotificacaoService;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

	@Autowired
	private NotificacaoService notificacaoService;

	@GetMapping
	public ResponseEntity<List<NotificacaoDto>> getAllNotificacoes() {
		List<NotificacaoDto> notificacoes = notificacaoService.findAll();
		return ResponseEntity.ok(notificacoes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NotificacaoDto> getNotificacaoById(@PathVariable Long id) {
		NotificacaoDto notificacao = notificacaoService.findById(id);
		return notificacao != null ? ResponseEntity.ok(notificacao) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<NotificacaoDto> createNotificacao(@RequestBody NotificacaoDto notificacaoDto) {
		NotificacaoDto createdNotificacao = notificacaoService.save(notificacaoDto);
		return ResponseEntity.ok(createdNotificacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NotificacaoDto> updateNotificacao(@PathVariable Long id,
			@RequestBody NotificacaoDto notificacaoDto) {
		notificacaoDto.setId(id);
		NotificacaoDto updatedNotificacao = notificacaoService.save(notificacaoDto);
		return ResponseEntity.ok(updatedNotificacao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotificacao(@PathVariable Long id) {
		notificacaoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/enviar-pendentes")
	public ResponseEntity<Void> enviarNotificacoesPendentes() {
		notificacaoService.enviarNotificacoesPendentes();
		return ResponseEntity.ok().build();
	}
}
