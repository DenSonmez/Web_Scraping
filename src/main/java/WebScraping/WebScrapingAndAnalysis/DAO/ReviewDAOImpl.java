package WebScraping.WebScrapingAndAnalysis.DAO;

import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ReviewDAOImpl implements ReviewDAO {
    private static EntityManagerFactory emf;
    private static ReviewDAO instance;

    public static ReviewDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ReviewDAOImpl();
        }
        return instance;
    }


    @Override
    public void createReview(Review review) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
        }
    }

    }
