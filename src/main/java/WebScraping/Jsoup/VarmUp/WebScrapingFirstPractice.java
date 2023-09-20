package WebScraping.Jsoup.VarmUp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class WebScrapingFirstPractice {

        private static final Logger logger = Logger.getLogger(WebScrapingFirstPractice.class.getName());

        public static void main(String[] args) {
            File input = new File("index.html");

            try {
                Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
               // System.out.println(doc);
                // TODO: Extract and print the title of the HTML page.
                String title = doc.getElementsByTag("title").text();
                System.out.println(title);
                System.out.println("--------------------------------------------------");

                // TODO: Write code to find and print all the text within `<p>` tags on the page.
                Elements pTags = doc.getElementsByTag("p");
                for (Element pTag : pTags) {
                    System.out.println(pTag.text());
                }
                System.out.println("--------------------------------------------------");

                // TODO: Extract and print all the URLs (href attributes) of the `<a>` tags on the page.
                Elements aTags = doc.getElementsByTag("a");
                     aTags.stream()
                    .map(aTag -> aTag.attr("href"))
                    .forEach(System.out::println);
                     //her er anden måde gør det på
                     //aTags.forEach(aTag -> System.out.println(aTag.attr("href")));
                System.out.println("--------------------------------------------------");

                // TODO: Exercise 3: Extract All Links
                Elements allLinks  = doc.getElementsByAttribute("href");
                System.out.println(allLinks);


                System.out.println("--------------------------------------------------");
                // TODO: extract and print the data from the table as a structured format (e.g., as a list of name-age pairs).
               Map<String, Integer> dataFromTable = new HashMap<>();
                 Elements tr = doc.getElementsByTag("td");

             for(int i = 0; i <tr.size() ; i++){
                    String name = tr.get(i).text();
                    Integer age = Integer.parseInt(tr.get(i+1).text());
                    dataFromTable.put(name, age);
                    i++;
                }
                System.out.println(dataFromTable);

                System.out.println("--------------------------------------------------");
                // TODO: Search for and print any paragraphs that contain the phrase "Random text" in their text content.
                Elements randomText = doc.getElementsContainingOwnText("Random text");
                System.out.println(randomText.text());

                System.out.println("--------------------------------------------------");
                // TODO: Modify your code to find and print only the URLs that contain "example" in their href attribute.
                Elements example = doc.getElementsByAttributeValueContaining("href", "example");
                System.out.println(example.text());

                System.out.println("--------------------------------------------------");
                // TODO: Extract and print the content of the `<header>` tag.
                Elements header = doc.getElementsByTag("header");
                System.out.println(header.first().text());

                System.out.println("--------------------------------------------------");
                // TODO: Find and print the text content of the `<div>` with the id "content" and all its nested elements.
                Elements content = doc.getElementsByAttributeValue("id", "content");
                System.out.println(content.text());

                System.out.println("--------------------------------------------------");
                // TODO: Extract and print the text content of the `<footer>` tag.
                Elements footer = doc.getElementsByTag("footer");
                System.out.println(footer.get(0).text());

            } catch (IOException e) {
                logger.warning(e.getMessage());
            }

        }
    }

