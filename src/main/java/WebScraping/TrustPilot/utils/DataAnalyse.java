package WebScraping.TrustPilot.utils;

import WebScraping.TrustPilot.Config.HibernateConfig;
import WebScraping.TrustPilot.DAO.*;
import WebScraping.TrustPilot.Model.Country;
import WebScraping.TrustPilot.Model.Person;
import WebScraping.TrustPilot.Model.Review;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAnalyse {

    private static EntityManagerFactory emf;
    private final ReviewDAO reviewDao;
    private final PersonDAO personDao;

    public DataAnalyse(EntityManagerFactory emf) {
        this.emf = emf;
        this.reviewDao = ReviewDAOImpl.getInstance(emf);
        this.personDao = PersonDAOImpl.getInstance(emf);
    }
    public static void main(String[] args) {
        HibernateConfig.addAnnotatedClasses(Review.class, Person.class, Country.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("trustp");
        DataAnalyse dataAnalysis = new DataAnalyse(emf);
        dataAnalysis.performDataAnalysis();
        System.out.println(dataAnalysis.toString());
        emf.close();
    }

    public void performDataAnalysis() {
        // Beregn den samlede procentdel af positive, neutrale og negative anmeldelser baseret på stjernebedømmelser
        long totalReviews = reviewDao.getCountOfReviews();
        long positiveReviews = reviewDao.getCountOfPositiveReviews();
        long neutralReviews = reviewDao.getCountOfNeutralReviews();
        long negativeReviews = reviewDao.getCountOfNegativeReviews();

        double positivePercentage = (double) positiveReviews / totalReviews * 100;
        double neutralPercentage = (double) neutralReviews / totalReviews * 100;
        double negativePercentage = (double) negativeReviews / totalReviews * 100;

        System.out.println("Positive anmeldelser: " + positivePercentage + "%");
        System.out.println("Neutrale anmeldelser: " + neutralPercentage + "%");
        System.out.println("Negative anmeldelser: " + negativePercentage + "%");

        // Bestem den gennemsnitlige stjernebedømmelse for alle anmeldelser
        double averageRating = reviewDao.getAverageRating();
        System.out.println("Gennemsnitlig stjernebedømmelse: " + averageRating);


        // Identificer de bedste anmeldere (registranter med flest anmeldelser)
        List<Object[]> topReviewers = personDao.getTopReviewers();
        Map<String, Long> topReviewersMap = topReviewers.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],     // anmelderens navn
                        row -> (Long) row[1]       // antallet af anmeldelser
                ));

        topReviewersMap.forEach((reviewerName, numberOfReviews) ->
                System.out.println(reviewerName + " har " + numberOfReviews + " anmeldelser."));

        // Beregn antallet af anmeldelser indsendt af hver registrant
        List<Object[]> reviewerReviewCounts = personDao.getReviewerReviewCounts();
        Map<String, Long> reviewerReviewCountsMap = reviewerReviewCounts.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],     // anmelderens navn
                        row -> (Long) row[1]       // antallet af anmeldelser
                ));

        reviewerReviewCountsMap.forEach((reviewerName, numberOfReviews) ->
                System.out.println(reviewerName + " har indsendt " + numberOfReviews + " anmeldelser."));
    }



}

