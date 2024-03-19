package com.jcs.helpdesk.services;

import com.jcs.helpdesk.domain.Pessoa;
import com.jcs.helpdesk.domain.Analista;
import com.jcs.helpdesk.domain.dtos.AnalistaDTO;
import com.jcs.helpdesk.repositories.PessoaRepository;
import com.jcs.helpdesk.repositories.AnalistaRepository;
import com.jcs.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AnalistaService {

    @Autowired
    private AnalistaRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Analista findById (Integer id) {
        Optional<Analista> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não foi encontrado! Id: " + id));
    }

    public List<Analista> findAll() {
        return repository.findAll();
    }

    public Analista create(AnalistaDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorMatriculaEEmail(objDTO);
        Analista newObj = new Analista(objDTO);
        return repository.save(newObj);
    }

    public Analista update(Integer id, @Valid AnalistaDTO objDTO) {
        objDTO.setId(id);
        Analista oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha())) {
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        }

        validaPorMatriculaEEmail(objDTO);
        oldObj = new Analista(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Analista obj = findById(id);
        if (obj.getRegistroDeOcorrencias().size() > 0) {
            throw new DataIntegrityViolationException("Analista possui RO's atreladas e não poderá ser apagado");
        } else {
            repository.deleteById(id);
        }
    }

    private void validaPorMatriculaEEmail(AnalistaDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getMatricula());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! Favor verificar.");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema! Favor verificar.");
        }
    }
}


