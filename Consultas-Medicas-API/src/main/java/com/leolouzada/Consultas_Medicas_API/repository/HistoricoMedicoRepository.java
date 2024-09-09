package com.leolouzada.Consultas_Medicas_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leolouzada.Consultas_Medicas_API.entity.HistoricoMedico;

@Repository
public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedico, Long> {

}
