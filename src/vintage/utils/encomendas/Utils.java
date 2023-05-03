package vintage.utils.encomendas;

import vintage.encomendas.Encomenda;

public class Utils {
    public static int parseTempoExpedicao(Encomenda encomenda) {
        switch (encomenda.getDimensaoEncomenda()) {
            case 10:
                return 14;
            case 5:
                return 7;
            case 1:
                return 3;
            default:
                return 0;
        }
    }
}
