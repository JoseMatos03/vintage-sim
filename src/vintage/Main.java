package vintage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vintage loja = new Vintage();
        Scanner scanner = new Scanner(System.in);

        // String teste = scanner.nextLine();
        // String[] teste2 = teste.split(" ");
        // System.out.println(teste2[0]);

        String[] utilizadorNovo = scanner.nextLine().split(",");
        for (String string : utilizadorNovo) {
            System.out.println(string.trim());
        }
        // loja.criaUtilizador(utilizadorNovo);
        // System.out.println(loja.getUtilizadores().toString());

        // String[] transporatodaNova = scanner.nextLine().split(" ");
        // loja.criaTransportadora(transporatodaNova);
        // System.out.println(loja.getTransportadoras().toString());

        // String[] artigoNovo = scanner.nextLine().split(" ");
        // loja.criaArtigo(artigoNovo);
        // System.out.println(loja.getArtigos().toString());

        scanner.close();
    }
}
