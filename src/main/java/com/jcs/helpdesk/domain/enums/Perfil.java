package com.jcs.helpdesk.domain.enums;

public enum Perfil {

    ADMIN(0, "PROFILE_ADMIN"), ASSOCIADO(1, "PROFILE_ASSOCIADO"), ANALISTA(2, "PROFILE_ANALISTA");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for(Perfil x : Perfil.values()){
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}