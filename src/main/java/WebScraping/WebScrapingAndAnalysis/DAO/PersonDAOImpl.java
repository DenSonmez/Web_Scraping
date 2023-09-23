package WebScraping.WebScrapingAndAnalysis.DAO;

import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Person;
import WebScraping.WebScrapingAndAnalysis.TrustpilotModel.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PersonDAOImpl implements PersonDAO {

    private static EntityManagerFactory emf;
    private static PersonDAO instance;

    public static PersonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDAOImpl();
        }
        return instance;
    }

    @Override
    public void createPerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }
    }

