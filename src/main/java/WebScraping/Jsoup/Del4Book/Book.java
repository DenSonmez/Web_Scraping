package WebScraping.Jsoup.Del4Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class Book {

    public static void main(String[] args) throws IOException {
        String url = "https://books.toscrape.com/";

        try{
            Document dokument = Jsoup.connect(url).get();
            Elements books = dokument.select(".product_pod");


            System.out.println("Books - Web Scrapper");
            System.out.println("***************************************");
            for (Element bk : books) {
                String title = bk.select("h3 > a ").text();
                String price = bk.select(".price_color").text();
                String actual_price = price.substring(1);
                //get book with 20euro
                if(Double.parseDouble(actual_price) < 20){
                    System.out.println("Title: " + title + " - " + "Price: " + price);
                }

            }
            System.out.println("****************************************");
        }catch (IOException e){
            e.printStackTrace();
        }









    }

}
