package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRun {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AccountTest.class);

        // Verificați rezultatul testelor și afișați informații despre eșecuri (dacă există)
        if (result.wasSuccessful()) {
            System.out.println("Toate testele au fost executate cu succes!");
        } else {
            System.out.println("Testele au eșuat. Detalii despre eșecuri:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}