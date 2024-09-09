package com.leolouzada.Consultas_Medicas_API.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolouzada.Consultas_Medicas_API.dto.NotificacaoDto;
import com.leolouzada.Consultas_Medicas_API.entity.Consulta;
import com.leolouzada.Consultas_Medicas_API.entity.Notificacao;
import com.leolouzada.Consultas_Medicas_API.entity.Paciente;
import com.leolouzada.Consultas_Medicas_API.repository.ConsultaRepository;
import com.leolouzada.Consultas_Medicas_API.repository.NotificacaoRepository;
import com.leolouzada.Consultas_Medicas_API.repository.PacienteRepository;
import com.leolouzada.Consultas_Medicas_API.service.NotificacaoService;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

	@Autowired
	private NotificacaoRepository notificacaoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	@Override
	public List<NotificacaoDto> findAll() {
		return notificacaoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public NotificacaoDto findById(Long id) {
		Optional<Notificacao> notificacao = notificacaoRepository.findById(id);
		return notificacao.map(this::convertToDTO).orElse(null);
	}

	@Override
	public NotificacaoDto save(NotificacaoDto notificacaoDto) {
		Notificacao notificacao = convertToEntity(notificacaoDto);
		Notificacao savedNotificacao = notificacaoRepository.save(notificacao);
		return convertToDTO(savedNotificacao);
	}

	@Override
	public void deleteById(Long id) {
		notificacaoRepository.deleteById(id);
	}

	@Override
	public void enviarNotificacoesPendentes() {
		List<Notificacao> pendentes = notificacaoRepository.findAll().stream().filter(
				notificacao -> !notificacao.isEnviada() && notificacao.getDataEnvio().isBefore(LocalDateTime.now()))
				.collect(Collectors.toList());

		pendentes.forEach(notificacao -> {
			// Lógica para envio da notificação
			notificacao.setEnviada(true);
			notificacaoRepository.save(notificacao);
		});
	}

	private NotificacaoDto convertToDTO(Notificacao notificacao) {
		NotificacaoDto dto = new NotificacaoDto();
		dto.setId(notificacao.getId());
		dto.setPacienteId(notificacao.getPaciente().getId());
		dto.setConsultaId(notificacao.getConsulta().getId());
		dto.setMensagem(notificacao.getMensagem());
		dto.setDataEnvio(notificacao.getDataEnvio());
		dto.setEnviada(notificacao.isEnviada());
		return dto;
	}

	private Notificacao convertToEntity(NotificacaoDto dto) {
		Notificacao notificacao = new Notificacao();
		notificacao.setId(dto.getId());

		Optional<Paciente> paciente = pacienteRepository.findById(dto.getPacienteId());
		paciente.ifPresent(notificacao::setPaciente);

		Optional<Consulta> consulta = consultaRepository.findById(dto.getConsultaId());
		consulta.ifPresent(notificacao::setConsulta);

		notificacao.setMensagem(dto.getMensagem());
		notificacao.setDataEnvio(dto.getDataEnvio());
		notificacao.setEnviada(dto.isEnviada());
		return notificacao;
	}
}
