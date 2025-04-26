import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QATest {

    private final WebDriver driver = new FirefoxDriver();

    @AfterEach
    void afterEach() {
        driver.close();
    }

    @Test
    void shouldTurnOnTheLight() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        container.findElements(By.tagName("button")).getFirst().click();

        // Verify
        String url = container.findElement(By.tagName("img")).getAttribute("src");
        Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", url);
    }

    @Test
    void shouldTurnOffTheLight() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        container.findElements(By.tagName("button")).getFirst().click();
        container.findElements(By.tagName("button")).getLast().click();

        // Verify
        String url = container.findElement(By.tagName("img")).getAttribute("src");
        Assertions.assertEquals("https://www.w3schools.com/js/pic_bulboff.gif", url);
    }

    @Test
    void shouldNotChangeBehaviorAfterMultipleTurnOnClicks() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        for (int i = 0; i < 10; i++) {
            container.findElements(By.tagName("button")).getFirst().click();

            // Verify
            String url = container.findElement(By.tagName("img")).getAttribute("src");
            Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", url);
        }
    }

    @Test
    void shouldNotChangeBehaviorAfterMultipleTurnOffClicks() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        container.findElements(By.tagName("button")).getFirst().click();
        for (int i = 0; i < 10; i++) {
            container.findElements(By.tagName("button")).getLast().click();
            String url = container.findElement(By.tagName("img")).getAttribute("src");
            Assertions.assertEquals("https://www.w3schools.com/js/pic_bulboff.gif", url);
        }
    }

    @Test
    void shouldShowGrayBulbOnFirstVisit() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        // Verify
        String url = container.findElement(By.tagName("img")).getAttribute("src");
        Assertions.assertEquals("https://www.w3schools.com/js/pic_bulboff.gif", url);
    }

    @Test
    void bulbStatusShouldPersistAfterReload() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        container.findElements(By.tagName("button")).getFirst().click();

        // Verify
        String urlBeforeReload = container.findElement(By.tagName("img")).getAttribute("src");
        Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", urlBeforeReload);

        // Exercise
        driver.navigate().refresh();
        WebElement containerAfterReload = driver.findElement(By.className("elementor-widget-container"));

        // Verify
        String urlAfterReload =
                containerAfterReload.findElement(By.tagName("img")).getAttribute("src");
        Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", urlAfterReload);
    }

    @Test
    void shouldSwitchLightOnAndOffRepeatedly() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                // Exercise
                container.findElements(By.tagName("button")).getFirst().click();

                // Verify
                String url = container.findElement(By.tagName("img")).getAttribute("src");
                Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", url);

            } else {
                // Exercise
                container.findElements(By.tagName("button")).getLast().click();

                // Verify
                String url = container.findElement(By.tagName("img")).getAttribute("src");
                Assertions.assertEquals("https://www.w3schools.com/js/pic_bulboff.gif", url);
            }
        }
    }

    @Test
    void shouldNotTurnOnLightWhenClickingGrayBulb() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));

        for (int i = 0; i < 10; i++) {
            // Exercise
            String url = container.findElement(By.tagName("img")).getAttribute("src");
            container.findElement(By.tagName("img")).click();
            // Verify
            Assertions.assertEquals("https://www.w3schools.com/js/pic_bulboff.gif", url);
        }
    }

    @Test
    void shouldNotTurnOffLightWhenClickingYellowBulb() {
        driver.get("https://qacamp.online/?page_id=574");

        // Exercise
        WebElement container = driver.findElement(By.className("elementor-widget-container"));
        container.findElements(By.tagName("button")).getFirst().click();

        for (int i = 0; i < 10; i++) {
            // Exercise
            String url = container.findElement(By.tagName("img")).getAttribute("src");
            container.findElement(By.tagName("img")).click();
            // Verify
            Assertions.assertEquals("https://www.w3schools.com/js/pic_bulbon.gif", url);
        }
    }
}
