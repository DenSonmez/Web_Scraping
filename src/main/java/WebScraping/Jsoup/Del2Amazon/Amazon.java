package WebScraping.Jsoup.Del2Amazon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Amazon {

    public static void main(String[] args) throws IOException {


        // Set the path to your ChromeDriver executable (download from Selenium website)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\denso\\OneDrive\\Skrivebord\\CPH\\IdeaProjects\\Web_Scraping\\chromedriver.exe");

        // Initialize Chrome WebDriver
        WebDriver driver = new ChromeDriver();

        // Define the URL of the Amazon product listing page
        String url = "https://www.amazon.com/s?k=laptop Nulaxy Telescopic 360 Rotating Laptop Stand for Desk Adjustable Height Swivel Pull Out Design Ergonomic Laptop Riser Fits All MacBook, Laptops - LS18";

        // Create an empty list to store product data
        List<Product> productList = new ArrayList<>();

        //her prøver jeg gemme en csv fil.
        saveProductDataToCSV(productList);


        try {
            // Load the Amazon page
            driver.get(url);

            // Scraping and Pagination Loop
            while (true) {
                // Parse the page content with Jsoup
                Document doc = Jsoup.parse(driver.getPageSource());

                // Extract product information from the current page
                Elements productElements = doc.select(".s-result-item");

                for (Element productElement : productElements) {
                    String name = productElement.select(".a-text-normal").text();
                    String price = productElement.select(".a-price .a-offscreen").text();
                    String description = productElement.select(".a-size-base").text();

                    // Add the product to the list
                    productList.add(new Product(name, price, description));
                }

                // Navigate to the next page
                WebElement nextPageButton = driver.findElement(By.xpath("//li[@class='a-last']/a"));
                if (nextPageButton.getAttribute("aria-disabled").equals("true")) {
                    break; // Exit the loop if there are no more pages
                } else {
                    nextPageButton.click();
                }
            }

            // Close the WebDriver
            driver.quit();

            // Data Cleaning (optional): Implement data cleaning functions here

            // Data Storage: Store the product data (e.g., write to a CSV file)
            saveProductDataToCSV(productList);
            cleanProductList(productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveProductDataToCSV(List<Product> productList) throws IOException {
        try (FileWriter writer = new FileWriter("product_data.csv")) {
            // Write CSV header
            writer.write("Name,Price,Description\n");

            // Write product data
            for (Product product : productList) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getDescription() + "\n");
            }
        }
    }

    private static void cleanProductList(List<Product> productList) {
        for (Product product : productList) {
            // Clean name, price, and description fields as needed
            product.setName(cleanText(product.getName()));
            product.setPrice(cleanText(product.getPrice()));
            product.setDescription(cleanText(product.getDescription()));
        }
    }

    private static String cleanText(String text) {
        // Implement your data cleaning logic here
        // For example, to remove all non-alphanumeric characters, you can use a regular expression
        text = text.replaceAll("[^a-zA-Z0-9\\s]", "");

        // If you need additional cleaning, you can add it here

        return text.trim(); // Remove any extra whitespace from the beginning and end
    }
}


