package com.jcs.helpdesk.services;

import com.jcs.helpdesk.domain.Associado;
import com.jcs.helpdesk.domain.Analista;
import com.jcs.helpdesk.domain.RegistroDeOcorrencia;
import com.jcs.helpdesk.domain.enums.Perfil;
import com.jcs.helpdesk.domain.enums.Prioridade;
import com.jcs.helpdesk.domain.enums.Status;
import com.jcs.helpdesk.repositories.AssociadoRepository;
import com.jcs.helpdesk.repositories.AnalistaRepository;
import com.jcs.helpdesk.repositories.RegistroDeOcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        Analista tec1 = new Analista(null, "Lemes Gustavo", "112233", "Lemesgus@mail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Associado cli1 = new Associado(null, "Amanda Fernandes", "261102", "amandafer261102@mail.com", encoder.encode("123"));

        RegistroDeOcorrencia sr1 = new RegistroDeOcorrencia(null, Prioridade.MEDIA, Status.ANDAMENTO, "SR-01", "Primeira RegistroDeOcorrencia", tec1, cli1);

        analistaRepository.saveAll(Arrays.asList(tec1));
        associadoRepository.saveAll(Arrays.asList(cli1));
        registroDeOcorrenciaRepository.saveAll(Arrays.asList(sr1));
    }
}
