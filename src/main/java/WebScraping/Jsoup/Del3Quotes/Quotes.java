package WebScraping.Jsoup.Del3Quotes;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class Quotes {

    public static void main(String[] args) throws IOException {

        String url = "https://quotes.toscrape.com/";
        Document doc = Jsoup.connect(url).get();

        Element nextButton = doc.selectFirst("li.next > a");

        while (nextButton != null) {
            // Udtræk data fra den aktuelle side
            Elements quotes = doc.select("div.quote");
            for (Element quote : quotes) {
                String quoteText = quote.selectFirst("span.text").text();
                String author = quote.selectFirst("small.author").text();
                String tags = quote.selectFirst("div.tags").text();
                System.out.println("QuoteText: " + quoteText);
                System.out.println("Author: " + author);
                System.out.println("Tags: " + tags);
            }

            // Naviger til næste side, hvis den er tilgængelig
            if (nextButton != null) {
                String nextPageUrl = nextButton.attr("abs:href");
                doc = Jsoup.connect(nextPageUrl).get();
                nextButton = doc.selectFirst("li.next > a");
            }
        }


        }



    }

