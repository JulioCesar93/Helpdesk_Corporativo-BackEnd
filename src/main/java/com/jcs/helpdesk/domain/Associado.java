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
    private List<RegistroDeOcorrencia> registroDeOcorrencia = new ArrayList<>();

    public Associado() {
        super();
        addPerfil(Perfil.ASSOCIADO);
    }

    public Associado(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Associado(AssociadoDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<RegistroDeOcorrencia> getTickets() {
        return registroDeOcorrencias;
    }

    public void setTickets(List<RegistroDeOcorrencia> registroDeOcorrencia) {
        this.registroDeOcorrencia = registroDeOcorrencias;
    }
}
