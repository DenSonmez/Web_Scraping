package WebScraping.TrustPilot.DAO;
import WebScraping.TrustPilot.Model.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

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

    public long getCountOfReviews() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(r) FROM Review r");
            return (long) query.getSingleResult();
        }
    }

    public long getCountOfPositiveReviews() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(r) FROM Review r WHERE r.starRating > 3");
            return (long) query.getSingleResult();
        }
    }
    public long getCountOfNeutralReviews() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(r) FROM Review r WHERE r.starRating = 3");
            return (long) query.getSingleResult();
        }
    }

    public long getCountOfNegativeReviews() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(r) FROM Review r WHERE r.starRating < 3");
            return (long) query.getSingleResult();
        }
    }
    public double getAverageRating() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT AVG(r.starRating) FROM Review r");
            Object result = query.getSingleResult();
            if (result == null) {
                return -1.0; // Returner en standardværdi, f.eks. -1, når der ikke er nogen anmeldelser.
            }
            return (double) result;
        }
    }

    }


