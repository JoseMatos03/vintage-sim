package vintage.utils;

public enum ErrorCode {
    // Loja
    NO_ERRORS,
    PARAMETRO_ERRADO,
    CODIGO_INVALIDO,
    DATA_INVALIDA,

    // Artigos
    ARTIGO_EXPEDIDO,
    DIMENSOES_INVALIDAS,

    // Utilizadores,
    EMAIL_INVALIDO,
    NIF_INVALIDO,

    // Encomendas
    SEM_ESPACO,
    ENCOMENDA_VAZIA,
    EM_ENCOMENDA,
    EM_EXPEDICAO,
    SEM_REEMBOLSO,
    ARTIGO_DO_COMPRADOR,
    ARTIGO_INVALIDO,

    // Transportadoras
    TRANSPORTADORA_INVALIDA,
    PREMIUM_REQUIRED,

    // Runner
    AUTORUN_SUCCESS,
    AUTORUN_ERROR,

    UTILIZADOR_INATIVO,

    DATA_PASSADA
}
