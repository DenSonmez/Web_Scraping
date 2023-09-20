package WebScraping.WebScrapingAndAnalysis;

import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAnalysis {


}


















    /*public static void performDataAnalysis(List<Review> reviews) {
        int totalReviews = reviews.size();
        if (totalReviews == 0) {
            System.out.println("Ingen anmeldelser at analysere.");
            return;
        }

        // Beregn den samlede procentdel af positive, neutrale og negative anmeldelser baseret på stjernebedømmelser
        int positiveCount = 0;
        int neutralCount = 0;
        int negativeCount = 0;

        int totalStarRating = 0;
        Map<String, Integer> reviewerCounts = new HashMap<>();

        // Gennemgå hver anmeldelse i listen
        for (Review review : reviews) {
            int starRating = (int) review.getStarRating(); // Hent stjernebedømmelsen
            totalStarRating += starRating; // Læg stjernebedømmelsen til den samlede stjernebedømmelse

            // Kategoriser anmeldelsen som positiv, neutral eller negativ baseret på stjernebedømmelsen
            if (starRating >= 4) {
                positiveCount++;
            } else if (starRating == 3) {
                neutralCount++;
            } else if (starRating >= 1) {
                negativeCount++;
            }

            String reviewerName = review.getName(); // Hent anmelderens navn
            reviewerCounts.put(reviewerName, reviewerCounts.getOrDefault(reviewerName, 0) + 1); // Tæl anmelderens anmeldelser
        }

        // Beregn procentdelen af positive, neutrale og negative anmeldelser
        double positivePercentage = (double) positiveCount / totalReviews * 100.0;
        double neutralPercentage = (double) neutralCount / totalReviews * 100.0;
        double negativePercentage = (double) negativeCount / totalReviews * 100.0;

        // Udskriv resultaterne for procentdelen af anmeldelser i hver kategori
        System.out.println("Positive anmeldelser: " + positivePercentage + "%");
        System.out.println("Neutrale anmeldelser: " + neutralPercentage + "%");
        System.out.println("Negative anmeldelser: " + negativePercentage + "%");

        // Beregn den gennemsnitlige stjernebedømmelse for alle anmeldelser
        double averageStarRating = (double) totalStarRating / totalReviews;

        // Udskriv den gennemsnitlige stjernebedømmelse
        System.out.println("Gennemsnitlig stjernebedømmelse: " + averageStarRating);

        // Identificer de bedste anmeldere (registranter med flest anmeldelser)
        int maxReviewCount = 0;
        List<String> bestReviewers = new ArrayList<>();

        // Find anmelderne med det højeste antal anmeldelser
        for (Map.Entry<String, Integer> entry : reviewerCounts.entrySet()) {
            String reviewerName = entry.getKey();
            int reviewCount = entry.getValue();

            if (reviewCount > maxReviewCount) {
                maxReviewCount = reviewCount;
                bestReviewers.clear();
                bestReviewers.add(reviewerName);
            } else if (reviewCount == maxReviewCount) {
                bestReviewers.add(reviewerName);
            }
        }

        // Udskriv de bedste anmeldere og antallet af anmeldelser, de har indsendt
        System.out.println("Bedste anmeldere: " + bestReviewers);
        System.out.println("Antal anmeldelser af de bedste anmeldere: " + maxReviewCount);

        // Beregn antallet af anmeldelser indsendt af hver anmelder og udskriv dem
        for (Map.Entry<String, Integer> entry : reviewerCounts.entrySet()) {
            String reviewerName = entry.getKey();
            int reviewCount = entry.getValue();
            System.out.println("Antal anmeldelser for " + reviewerName + ": " + reviewCount);
        }
    }*/
