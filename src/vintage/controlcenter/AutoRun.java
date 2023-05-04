package vintage.controlcenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

import vintage.Vintage;
import vintage.utils.ErrorCode;

public class AutoRun {
    // Exemplos de cada comando:
    // (Mala):
    // criaArtigo,TIPO,CODVENDEDOR,ESTADO,NDONOS,DESCRICAO,MARCA,PRECO,TRANSPORTADORA,C,L,A,TMATERIAL,ANO,PREMIUM
    // (Sapatilhas):
    // criaArtigo,TIPO,CODVENDEDOR,ESTADO,NDONOS,DESCRICAO,MARCA,PRECO,TRANSPORTADORA,TAMANHO,TATACADORES,COR,ANO,PREMIUM
    // (TShirt):
    // criaArtigo,TIPO,CODVENDEDOR,ESTADO,NDONOS,DESCRICAO,MARCA,PRECO,TRANSPORTADORA,TTAMANHO,TPADRAO
    // removeArtigo,CODIGO
    // criaEncomenda,CODCOMPRADOR,DIMENSAO
    // adicionarArtigo,CODENCOMENDA,CODARTIGO
    // removerArtigo,CODENCOMENDA,CODARTIGO
    // expedirEncomenda,CODIGO
    // entregarEncomendas
    // cancelaEncomenda,CODIGO
    // criaUtilizador,EMAIL,NOME,MORADA,NIF
    // apagaUtilizador,CODIGO
    // criaTransportadora,NOME,LUCRO,EXTRA(só em PREMIUM),PREMIUM
    // apagaTransportadora,NOME
    // travelTo,DATAHORA

    public static final String RUNNER_FILEPATH = System.getProperty("user.dir") + File.separator + "runner"
            + File.separator
            + "autorun.csv";

    private HashMap<String, Method> opCodeMap;

    public AutoRun() {
        this.opCodeMap = new HashMap<String, Method>();
        this.mapCodesToMethods();
    }

    public ErrorCode readAndExecute(Vintage loja) {
        String line = "";
        String splitBy = ",";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(RUNNER_FILEPATH));
            while ((line = reader.readLine()) != null) {
                String[] command = line.split(splitBy);
                String code = command[0];

                if (command.length > 2) {
                    String[] info = Arrays.copyOfRange(command, 1, command.length);
                    runMethod(loja, code, info);
                } else if (command.length > 1) {
                    String info = command[1];
                    runMethod(loja, code, info);
                } else {
                    runMethod(loja, code);
                }

            }
            reader.close();
        } catch (Exception e) {
            return ErrorCode.AUTORUN_ERROR;
        }

        return ErrorCode.AUTORUN_SUCCESS;
    }

    public void runMethod(Vintage loja, String code, String[] info)
            throws IllegalAccessException, InvocationTargetException {
        opCodeMap.get(code).invoke(loja, new Object[] { info });
    }

    public void runMethod(Vintage loja, String code, String info)
            throws IllegalAccessException, InvocationTargetException {
        opCodeMap.get(code).invoke(loja, info);
    }

    public void runMethod(Vintage loja, String code)
            throws IllegalAccessException, InvocationTargetException {
        opCodeMap.get(code).invoke(loja);
    }

    public void mapCodesToMethods() {
        try {
            opCodeMap.put("criaArtigo", Vintage.class.getMethod("criaArtigo", String[].class));
            opCodeMap.put("removeArtigo", Vintage.class.getMethod("removeArtigo", String.class));
            opCodeMap.put("criaEncomenda", Vintage.class.getMethod("criaEncomenda", String[].class));
            opCodeMap.put("adicionarArtigo", Vintage.class.getMethod("adicionarArtigoEmEncomenda", String[].class));
            opCodeMap.put("removerArtigo", Vintage.class.getMethod("removerArtigoEmEncomenda", String[].class));
            opCodeMap.put("expedirEncomenda", Vintage.class.getMethod("expedirEncomenda", String.class));
            opCodeMap.put("entregarEncomendas", Vintage.class.getMethod("entregarEncomendas"));
            opCodeMap.put("cancelaEncomenda", Vintage.class.getMethod("cancelaEncomenda", String.class));
            opCodeMap.put("criaUtilizador", Vintage.class.getMethod("criaUtilizador", String[].class));
            opCodeMap.put("apagaUtilizador", Vintage.class.getMethod("apagaUtilizador", String.class));
            opCodeMap.put("criaTransportadora", Vintage.class.getMethod("criaTransportadora", String[].class));
            opCodeMap.put("apagaTransportadora", Vintage.class.getMethod("apagaTransportadora", String.class));
            opCodeMap.put("travelTo", Vintage.class.getMethod("timeTravel", String.class));
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
