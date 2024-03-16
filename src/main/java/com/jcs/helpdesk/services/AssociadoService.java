package com.jcs.helpdesk.services;

import com.jcs.helpdesk.domain.Associado;
import com.jcs.helpdesk.domain.Pessoa;
import com.jcs.helpdesk.domain.dtos.AssociadoDTO;
import com.jcs.helpdesk.repositories.AssociadoRepository;
import com.jcs.helpdesk.repositories.PessoaRepository;
import com.jcs.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Associado findById (Integer id) {
        Optional<Associado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não foi encontrado! Id: " + id));
    }

    public List<Associado> findAll() {
        return repository.findAll();
    }

    public Associado create(AssociadoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Associado newObj = new Associado(objDTO);
        return repository.save(newObj);
    }

    public Associado update(Integer id, @Valid AssociadoDTO objDTO) {
        objDTO.setId(id);
        Associado oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha())) {
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        }

        validaPorCpfEEmail(objDTO);
        oldObj = new Associado(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Associado obj = findById(id);
        if (obj.getRegistroDeOcorrencias().size() > 0) {
            throw new DataIntegrityViolationException("Associado possui RO's atreladas e não poderá ser apagado");
        } else {
            repository.deleteById(id);
        }
    }

    private void validaPorCpfEEmail(AssociadoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! Favor verificar.");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema! Favor verificar.");
        }
    }
}


