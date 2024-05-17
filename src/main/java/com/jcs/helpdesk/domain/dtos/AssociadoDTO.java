package com.jcs.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcs.helpdesk.domain.Associado;
import com.jcs.helpdesk.domain.enums.Cargo;
import com.jcs.helpdesk.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AssociadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "Campo NOME obrigatório")
    protected String nome;

    @NotNull(message = "Campo Matricula obrigatório")
    protected String matricula;

    protected String gerenteMercado;
    protected String gerenteVendas;

    @NotNull(message = "Campo E-MAIL obrigatório")
    protected String email;

    @NotNull(message = "Campo SENHA obrigatório")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();
    protected Set<Integer> cargos = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public AssociadoDTO() {
        super();
        addPerfil(Perfil.ASSOCIADO);
        addCargo(Cargo.REPRESENTANTE);
    }

    public AssociadoDTO(Associado obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.matricula = obj.getMatricula();
        this.gerenteMercado = obj.getGerenteMercado();
        this.gerenteVendas = obj.getGerenteVendas();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.cargos = obj.getCargos().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.ASSOCIADO);
        addCargo(Cargo.REPRESENTANTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public Set<Cargo> getCargos() {
        return cargos.stream().map(x -> Cargo.toEnum(x)).collect(Collectors.toSet());
    }
    public void addCargo(Cargo cargo) {
        this.cargos.add(cargo.getCodigo());
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGerenteMercado() {
        return gerenteMercado;
    }

    public void setGerenteMercado(String gerenteMercado) {
        this.gerenteMercado = gerenteMercado;
    }

    public String getGerenteVendas() {
        return gerenteVendas;
    }

    public void setGerenteVendas(String gerenteVendas) {
        this.gerenteVendas = gerenteVendas;
    }
}