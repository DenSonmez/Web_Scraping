package WebScraping.Jsoup.Del7html;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicContentScraper {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver","C:\\Users\\denso\\OneDrive\\Skrivebord\\CPH\\IdeaProjects\\Web_Scraping\\chromedriver.exe");

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true); // Run Chrome in headless mode (without GUI)

        // Create a new ChromeDriver instance
        WebDriver driver = new ChromeDriver(options);

        try {
            String url = "https://www.amazon.com/";
            driver.get(url);

            // Use Selenium to interact with the dynamic content

            // For example, if the content is loaded by scrolling down the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // If content is loaded after clicking a button
            WebElement button = driver.findElement(By.id("buttonId"));
            button.click();

            // Wait for an element to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementId")));

            // Continue with extracting data after the element is visible
            // Once the dynamic content is loaded, you can use regular Selenium
            // methods to find and extract elements.

            // For example, you can find elements and extract data like this:
            WebElement someElement = driver.findElement(By.id("someElementId"));
            String elementText = someElement.getText();
            System.out.println("Element Text: " + elementText);

            // You can continue extracting more data as needed.
        } finally {
            // Close the browser when done
            driver.quit();
        }
    }
}
