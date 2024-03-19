package com.jcs.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcs.helpdesk.domain.Analista;
import com.jcs.helpdesk.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalistaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "Campo NOME obrigatório")
    protected String nome;

    @NotNull(message = "Campo Matricula obrigatório")
    protected String matricula;

    @NotNull(message = "Campo E-MAIL obrigatório")
    protected String email;

    @NotNull(message = "Campo SENHA obrigatório")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public AnalistaDTO() {
        super();
        addPerfil(Perfil.ASSOCIADO);
    }

    public AnalistaDTO(Analista obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.matricula = obj.getMatricula();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.ASSOCIADO);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) { this.matricula = matricula;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
