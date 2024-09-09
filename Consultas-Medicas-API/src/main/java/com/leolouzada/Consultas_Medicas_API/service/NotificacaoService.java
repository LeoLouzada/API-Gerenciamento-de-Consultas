package com.leolouzada.Consultas_Medicas_API.service;

import java.util.List;

import com.leolouzada.Consultas_Medicas_API.dto.NotificacaoDto;

public interface NotificacaoService {
	List<NotificacaoDto> findAll();

	NotificacaoDto findById(Long id);

	NotificacaoDto save(NotificacaoDto notificacaoDto);

	void deleteById(Long id);

	void enviarNotificacoesPendentes();
}
