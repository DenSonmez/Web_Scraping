package WebScraping.TrustPilot.DAO;
import WebScraping.TrustPilot.Model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

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

    public List<Object[]> getTopReviewers() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT p.name, COUNT(r) FROM Person p JOIN p.reviews r GROUP BY p.name ORDER BY COUNT(r) DESC");
            return query.getResultList();
        }
    }

    public List<Object[]> getReviewerReviewCounts() {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("SELECT p.name, COUNT(r) FROM Person p JOIN p.reviews r GROUP BY p.name");
            return query.getResultList();
        }

    }
}

