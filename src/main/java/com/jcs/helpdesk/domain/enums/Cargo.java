package com.jcs.helpdesk.domain.enums;

public enum Cargo {

    REPRESENTANTE(0, "CARGO_REPRESENTANTE"),
    GERENTE(1, "CARGO_GERENTE"),
    OPERADOR_TELEVENDAS(2, "CARGO_OPERADOR_TELEVENDAS");

    private Integer codigo;
    private String descricao;

    Cargo(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Cargo toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for(Cargo x : Cargo.values()){
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Cargo Inválido");
    }
}