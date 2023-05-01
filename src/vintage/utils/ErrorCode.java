package vintage.utils;

public enum ErrorCode {
    NO_ERRORS,
    PARAMETRO_ERRADO,
    CODIGO_INVALIDO,
    DATA_INVALIDA,

    // Artigos
    ARTIGO_EXPEDIDO,

    // Encomendas
    SEM_ESPACO,
    ENCOMENDA_VAZIA,
    EM_ENCOMENDA,
    EM_EXPEDICAO,
    SEM_REEMBOLSO,
    ARTIGO_DO_COMPRADOR,

    // Transportadoras
    TRANSPORTADORA_INVALIDA
}
