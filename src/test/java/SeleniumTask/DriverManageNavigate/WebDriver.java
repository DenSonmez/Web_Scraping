package SeleniumTask.DriverManageNavigate;


import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriver {

        public static void main(String[] args) {

            //WebDriverManager.chromedriver().setup();
            org.openqa.selenium.WebDriver driver = new ChromeDriver();
            driver.get("https://www.amazon.com");
           System.out.println(driver.getTitle());
            driver.navigate().to("https://www.facebook.com");
            System.out.println(driver.getTitle());
        }


    }