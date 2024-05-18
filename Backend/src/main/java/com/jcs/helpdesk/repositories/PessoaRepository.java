package com.jcs.helpdesk.repositories;

import com.jcs.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository <Pessoa, Integer> {

    Optional<Pessoa> findByMatricula (String matricula);
    Optional<Pessoa> findByEmail(String email);
}
