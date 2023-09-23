package WebScraping.WebScrapingAndAnalysis.DAO;

import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Country;
import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CountryDAOImpl implements CountryDAO {
    private static EntityManagerFactory emf;
    private static CountryDAO instance;

    public static CountryDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CountryDAOImpl();
        }
        return instance;
    }


    @Override
    public void createCountry(Country country) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(country);
            em.getTransaction().commit();
        }
    }
    }

