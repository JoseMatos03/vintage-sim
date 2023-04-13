package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ArtigoTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                test.artigos.mala.MalaTest.class,
                test.artigos.sapatilhas.SapatilhasTest.class,
                test.artigos.tshirt.TShirtTest.class);

        System.out.println("Testes realizados: " + result.getRunCount());
        System.out.println("Testes falhados: " + result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Testes ignorados: " + result.getIgnoreCount());

        System.out.println("Operação com sucesso: " + result.wasSuccessful());
    }

}
