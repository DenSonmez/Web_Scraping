package WebScraping.WebScrapingAndAnalysis.TrustpilotModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    protected static List<Rcp> fetchData(String url) throws IOException, InterruptedException {
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
                .get();
        Thread.sleep(1000);

        Elements reviewsEl = document.select("article.paper_paper__1PY90");
        List<Rcp> result = new ArrayList<>();

        for (Element reviewElement : reviewsEl) {
            Review review = new Review();
            Person person = new Person();
            Country countrys = new Country();

            String name = reviewElement.select(".typography_heading-xxs__QKBS8").text();
            String reviewCountText = reviewElement.select("div.styles_consumerExtraDetails__fxS4S").attr("data-consumer-reviews-count");
            int reviewCount = Integer.parseInt(reviewCountText);
            String country = reviewElement.select("div[data-consumer-country-typography=true] span").text();
            String date = reviewElement.select("p[data-service-review-date-of-experience-typography=true]").text();
            String title = reviewElement.select("a[data-review-title-typography=true] h2[data-service-review-title-typography=true]").text();
            String content = reviewElement.select("p[data-service-review-text-typography=true]").text();
            String starRatingText = reviewElement.select("div.styles_reviewHeader__iU9Px").attr("data-service-review-rating");
           int starRating = Integer.parseInt(starRatingText);

            person.setName(name);
            person.setReviewCount(reviewCount);
            countrys.setName(country);
            review.setStarRating((int) starRating);
            review.setTitle(title);
            review.setContent(content);
            review.setDate(date);

            result.add(new Rcp(person, countrys, review));

        }
        return result;
    }



    // Hjælpefunktion til at udtrække heltal fra tekst
    /*private static int extractInteger(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Håndter ugyldig tekst, hvis det er nødvendigt
            return 0;
        }*/
    }




  /*double starRating = 0.0;
            Pattern pattern = Pattern.compile("Bedømt til (\\d+(\\.\\d+)?) ud af (\\d+) stjerner");
            Matcher matcher = pattern.matcher(starRatingText);
            if (matcher.find()) {
                starRating = Double.parseDouble(matcher.group(1));
                int maxRating = Integer.parseInt(matcher.group(3));
                // Konverter stjernerangeringen til en skala fra 1 til 5 (hvis nødvendigt)
                double scaledRating = (starRating / maxRating) * 5.0;
                // Nu har du stjernerangeringen som en decimal (f.eks., 4.0 eller 4.5)
                // Konverter det til en integer, hvis det er nødvendigt
                int starRatingInteger = (int) Math.round(scaledRating);
                System.out.println("Stjernerangering: " + starRatingInteger);
            } else {
                System.out.println("Stjernerangering ikke fundet.");
            }*/