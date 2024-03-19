package com.jcs.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcs.helpdesk.domain.enums.Perfil;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(unique = true)
    protected String matricula;
    protected String nome;

    @CPF
    @Column(unique = true)
    protected String cpf;

    protected String gerenteMercado;
    protected String gerenteVendas;

    @Column(unique = true)
    protected String email;
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CARGOS")
    protected Set<Integer> cargos = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
        addPerfil(Perfil.ASSOCIADO);
    }

    public Pessoa(Integer id, String nome, String matricula, String cpf, String email, String senha,
                  String gerenteMercado, String gerenteVendas) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.gerenteMercado = gerenteMercado;
        this.gerenteVendas = gerenteVendas;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGerenteMercado() {
        return gerenteMercado;
    }

    public void setGerenteMercado(String gerenteMercado) { this.gerenteMercado = gerenteMercado;}

    public String getGerenteVendas() {
        return gerenteVendas;
    }

    public void setGerenteVendas(String gerenteVendas) { this.gerenteVendas = gerenteVendas;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.id) && matricula.equals(pessoa.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula);
    }
}
