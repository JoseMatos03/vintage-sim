package vintage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vintage loja = new Vintage();
        Scanner scanner = new Scanner(System.in);

        // String teste = scanner.nextLine();
        // String[] teste2 = teste.split(" ");
        // System.out.println(teste2[0]);

        String[] utilizadorNovo = scanner.nextLine().split(" ");
        loja.criaUtilizador(utilizadorNovo);
        System.out.println(loja.getUtilizadores().toString());

        scanner.close();
    }
}
