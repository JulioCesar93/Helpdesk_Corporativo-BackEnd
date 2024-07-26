package com.jcs.helpdesk.services;

import com.jcs.helpdesk.repositories.AnalistaRepository;
import com.jcs.helpdesk.repositories.AssociadoRepository;
import com.jcs.helpdesk.repositories.RegistroDeOcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private AnalistaRepository analistaRepository;
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private RegistroDeOcorrenciaRepository registroDeOcorrenciaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {

    }
}
