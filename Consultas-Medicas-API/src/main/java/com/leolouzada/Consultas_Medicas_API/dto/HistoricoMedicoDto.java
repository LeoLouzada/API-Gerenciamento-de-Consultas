package com.leolouzada.Consultas_Medicas_API.dto;

import java.time.LocalDateTime;

public class HistoricoMedicoDto {

	private Long id;
	private Long pacienteId;
	private Long medicoId;
	private LocalDateTime dataConsulta;
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
