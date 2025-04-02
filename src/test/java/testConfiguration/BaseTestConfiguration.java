package testConfiguration;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Objects;

public class BaseTestConfiguration {

    @BeforeSuite
    public static void cleanAllureResults() {
        File resultsDir = new File("allure-results");
        if (resultsDir.exists()) {
            for (File file : Objects.requireNonNull(resultsDir.listFiles())) {
                file.delete();
            }
        }
    }
}
