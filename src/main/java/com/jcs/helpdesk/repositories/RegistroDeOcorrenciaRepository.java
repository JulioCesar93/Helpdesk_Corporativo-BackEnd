package com.jcs.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jcs.helpdesk.domain.RegistroDeOcorrencia;

public interface RegistroDeOcorrenciaRepository extends JpaRepository <RegistroDeOcorrencia, Integer> {

}