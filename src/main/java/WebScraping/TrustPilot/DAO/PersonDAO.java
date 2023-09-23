package WebScraping.TrustPilot.DAO;

import WebScraping.TrustPilot.Model.Person;

import java.util.List;

public interface PersonDAO {

    void createPerson(Person person);

    List<Object[]> getTopReviewers();

    List<Object[]> getReviewerReviewCounts();
}
