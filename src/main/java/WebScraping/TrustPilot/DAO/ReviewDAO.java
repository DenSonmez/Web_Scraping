package WebScraping.TrustPilot.DAO;

import WebScraping.TrustPilot.Model.Review;

public interface ReviewDAO {
    void createReview(Review review);


    long getCountOfReviews();

    long getCountOfPositiveReviews();

    long getCountOfNeutralReviews();

    long getCountOfNegativeReviews();

    double getAverageRating();
}
