package com.jcs.helpdesk.domain.enums;

public enum CategoriaRO {

    ACESSOS (1, "Acessos"),
    APLICATIVO_DE_VENDAS (2, "Aplicativo de Vendas"),
    CENTRAL_DE_PAGAMENTOS (3, "Central de Pagamentos"),
    DUVIDAS_INFORMACOES_OUTROS (4, "Dúvidas, Informações, Outros"),
    ATENDIMENTO_GESTORES (5, "Atendimento Gestores"),
    LOGÍSTICA (6, "Logística"),
    IMPLANTAÇÃO (7, "Implantação"),
    ATENDIMENTO_A_CLIENTES (8, "Atendimento a Clientes"),
    DEBITOS_DE_PECAS_ACESSORIOS_E_EQUIPAMENTOS(9, "Débitos de Peças, Acessórios e Equipamentos"),
    CELULAR (10, "Celular"),
    NOTEBOOK (11, "Notebook"),
    TABLET (12, "Tablet"),
    SISTEMA_OPERACIONAL(13, "Sistema Operacional"),
    CONFIGURACAO_VPN(14, "Configuração VPN"),
    NAVEGADOR (15, "Navegador"),
    FUNCIONALIDADES (16, "Funcionalidades"),
    REDES (17, "Redes"),
    PACOTE_OFFICE (18, "Pacote Office"),
    INSTALACAO_DE_APLICATIVOS (19, "Instalação de Aplicativos");

    private final Integer codigo;
    private final String descricao;

    CategoriaRO (Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CategoriaRO toEnum (Integer cod) {
        if (cod == null) {
            return null;
        }
        for(CategoriaRO x : CategoriaRO.values()){
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Categoria Inválida");
    }
}


