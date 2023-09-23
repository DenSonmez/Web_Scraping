package WebScraping.TrustPilot.Model;
import WebScraping.TrustPilot.Config.HibernateConfig;
import WebScraping.TrustPilot.DAO.*;
import WebScraping.TrustPilot.utils.DataAnalyse;
import WebScraping.TrustPilot.utils.Scraper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {

    protected static EntityManagerFactory emf;

    public static void main(String[] args) throws IOException, InterruptedException {

        HibernateConfig.addAnnotatedClasses(Review.class, Person.class, Country.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("trustp");



        ReviewDAO reviewDao = ReviewDAOImpl.getInstance(emf);
        CountryDAO countryDao = CountryDAOImpl.getInstance(emf);
        PersonDAO personDao = PersonDAOImpl.getInstance(emf);

        String baseUrl = "https://dk.trustpilot.com/review/www.apple.com";
        EntityManager em = emf.createEntityManager();

        try {
            Document document = Jsoup.connect(baseUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
                    .get();
            Thread.sleep(1000);


            String nummerOfPagesString = document.getElementsByAttributeValueMatching("data-pagination-button-last-link", "true").get(0).child(0).text();
            int nummerOfPages = Integer.parseInt(nummerOfPagesString);
            //System.out.println(nummerOfPagesString);

            Future<List<Rcp>>[] futures = new Future[nummerOfPages];
            ExecutorService es = Executors.newCachedThreadPool();

            int currentPage = 1;
            for (int i = 0; i < nummerOfPages; i++) {
                String url = baseUrl + "?page=" + currentPage;
                futures[i] = es.submit(() -> Scraper.fetchData(url));
                currentPage++;
            }
            for (Future<List<Rcp>> f : futures) {
                List<Rcp> result = f.get();
                for (Rcp rs : result){
                   reviewDao.createReview(rs.getReview());
                    personDao.createPerson(rs.getPerson());
                    countryDao.createCountry(rs.getCountry());

                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

}
