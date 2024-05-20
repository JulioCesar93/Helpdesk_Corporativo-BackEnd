package com.jcs.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcs.helpdesk.domain.enums.CategoriaRO;
import com.jcs.helpdesk.domain.enums.Prioridade;
import com.jcs.helpdesk.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RegistroDeOcorrencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    private CategoriaRO categoriaRO;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name= "analista_id")
    private Analista analista;

    @ManyToOne
    @JoinColumn(name= "associado_id")
    private Associado associado;

    public RegistroDeOcorrencia(){
        super();
    }

    public RegistroDeOcorrencia(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Analista analista, Associado associado) {
        this.id = id;
        this.categoriaRO = categoriaRO;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.analista = analista;
        this.associado = associado;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public CategoriaRO getCategoriaRO() {
        return categoriaRO;
    }

    public void setCategoriaRO(CategoriaRO categoriaRO) {
        this.categoriaRO = categoriaRO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Analista getAnalista() {
        return analista;
    }

    public void setAnalista(Analista analista) {
        this.analista = analista;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroDeOcorrencia registroDeOcorrencia = (RegistroDeOcorrencia) o;
        return Objects.equals(id, registroDeOcorrencia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}