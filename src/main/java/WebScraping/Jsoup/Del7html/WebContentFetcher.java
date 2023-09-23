package WebScraping.Jsoup.Del7html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
public class WebContentFetcher {
    public static void main(String[] args) {
        String url = "https://facebook.com";
        String htmlContent = "<html><body><div class='my-class'>Hello, <span>World!</span></div></body></html>";

        try {
            // Fetch the webpage content
            Document document = Jsoup.connect(url).get();
            // Parse the HTML content
            Document document2 = Jsoup.parse(htmlContent);

            // Select and print the text of the <span> element
            Element spanElement = document.select("span").first();
            if (spanElement != null) {
                System.out.println("Text inside <span>: " + spanElement.text());
            }

            // Print the fetched HTML content
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}