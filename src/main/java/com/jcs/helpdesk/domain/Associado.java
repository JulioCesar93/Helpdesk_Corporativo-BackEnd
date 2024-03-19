package com.jcs.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jcs.helpdesk.domain.dtos.AssociadoDTO;
import com.jcs.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Associado extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "associado")
    private List<RegistroDeOcorrencia> registroDeOcorrencias = new ArrayList<>();

    public Associado() {
        super();
        addPerfil(Perfil.ASSOCIADO);
    }

    public Associado(Integer id, String nome, String matricula, String email, String senha) {
        super();
        addPerfil(Perfil.ASSOCIADO);
    }

    public Associado(AssociadoDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.cargos = obj.getCargos().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<RegistroDeOcorrencia> getRegistroDeOcorrencias() {
        return registroDeOcorrencias;
    }

    public void setRegistroDeOcorrencias(List<RegistroDeOcorrencia> registroDeOcorrencias) {
        this.registroDeOcorrencias = registroDeOcorrencias;
    }
}
