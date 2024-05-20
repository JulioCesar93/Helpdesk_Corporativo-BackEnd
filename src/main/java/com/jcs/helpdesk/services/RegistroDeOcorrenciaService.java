package com.jcs.helpdesk.services;

import com.jcs.helpdesk.domain.Associado;
import com.jcs.helpdesk.domain.Analista;
import com.jcs.helpdesk.domain.RegistroDeOcorrencia;
import com.jcs.helpdesk.domain.dtos.RegistroDeOcorrenciaDTO;
import com.jcs.helpdesk.domain.enums.Prioridade;
import com.jcs.helpdesk.domain.enums.Status;
import com.jcs.helpdesk.repositories.RegistroDeOcorrenciaRepository;
import com.jcs.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroDeOcorrenciaService {

    @Autowired
    private RegistroDeOcorrenciaRepository repository;

    @Autowired
    private AnalistaService analistaService;

    @Autowired
    private AssociadoService associadoService;

    public RegistroDeOcorrencia findById(Integer id) {
        Optional<RegistroDeOcorrencia> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Registro De Ocorrência não encontrado! RO: " + id));
    }

    public List<RegistroDeOcorrencia> findAll() {
        return repository.findAll();
    }

    public RegistroDeOcorrencia create(@Valid RegistroDeOcorrenciaDTO objDTO) {
        return repository.save(newRegistroDeOcorrencia(objDTO));
    }

    public RegistroDeOcorrencia update(Integer id, @Valid RegistroDeOcorrenciaDTO objDto) {
        objDto.setId(id);
        RegistroDeOcorrencia oldObj = findById(id);
        oldObj = newRegistroDeOcorrencia(objDto);
        return repository.save(oldObj);
    }

    private RegistroDeOcorrencia newRegistroDeOcorrencia(RegistroDeOcorrenciaDTO obj) {
        Analista analista = analistaService.findById(obj.getAnalista());
        Associado associado = associadoService.findById(obj.getAssociado());

        RegistroDeOcorrencia registroDeOcorrencia = new RegistroDeOcorrencia();
        if(obj.getId() != null) {
            registroDeOcorrencia.setId(obj.getId());
        }
        if(obj.getStatus().equals(2)) {
            registroDeOcorrencia.setDataFechamento(LocalDate.now());
        }

        registroDeOcorrencia.setAnalista(analista);
        registroDeOcorrencia.setAssociado(associado);
        registroDeOcorrencia.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        registroDeOcorrencia.setStatus(Status.toEnum(obj.getStatus()));
        registroDeOcorrencia.setTitulo(obj.getTitulo());
        registroDeOcorrencia.setObservacoes(obj.getObservacoes());
        return registroDeOcorrencia;
    }
}