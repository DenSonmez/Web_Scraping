package MovieDAOTest;

import WebScraping.MoiveScraper.config.HibernateConfig;
import WebScraping.MoiveScraper.dao.MovieDAO;
import WebScraping.MoiveScraper.model.Movie;
import WebScraping.MoiveScraper.utils.Scraper;
import jakarta.persistence.EntityManagerFactory;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.List;

import static WebScraping.MoiveScraper.utils.Scraper.fetchMovieData;

public class MovieDAOTest {

    private static EntityManagerFactory emf;
    private static MovieDAO movieDAO;


    @BeforeEach
    public void setUp() {
        HibernateConfig.addAnnotatedClasses(Movie.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("imdb_moviestest");
        movieDAO = MovieDAO.getInstance(emf);
    }


    @AfterAll
    public static void tearDown() {
        emf.close();
    }

    @Test
    public void testFetchAndSaveMovieData() throws IOException, InterruptedException {
        // Hent filmdata fra IMDb
        List<Movie> movies = Scraper.fetchMovieData();
        for (Movie m : movies) {
            movieDAO.saveMovie(m);
        }

    }


    @Test
    public void testSaveMovie() {
        Movie movie = Movie.builder()
                .title("Test Movie")
                .thumbnailImageURL("test")
                .rating(10.0)
                .numberOfRatings(100)
                .releaseYear(2021)
                .MPAArating("PG-13")
                .duration("2h 30min")
                .imdbId("tt0000000")
                .build();

        // Gem filmen i databasen
        movieDAO.saveMovie(movie);

        // Hent filmen igen fra databasen
        Movie savedMovie = movieDAO.getMovieByImdbId("tt0000000");
        Assertions.assertNotNull(savedMovie);
        Assertions.assertEquals("Test Movie", savedMovie.getTitle());
    }
    @Test
    public void testGetMovieById() {
        Movie movie = movieDAO.getMovieByImdbId("1");
        Assertions.assertNotNull(movie);
        Assertions.assertEquals("En verden udenfor", movie.getTitle());
    }

    @Test

    public void testGetMovieByTitleWithSubstringMatch() {
           // Søg efter en del af titlen, f.eks. "Godfather"
        Movie godfather = movieDAO.getMovieByTitle("Godfather");
        Assertions.assertNotNull(godfather);
        Assertions.assertTrue(godfather.getTitle().contains("Godfather"));
    }

    @Test
    public void testGetMovieByTitleWithSpecialCharacters() {
        // Søg efter filmen "Godfather del 2"
        Movie godfather2 = movieDAO.getMovieByTitle("Godfather del 2");
        Assertions.assertNotNull(godfather2);
        Assertions.assertEquals("Godfather del 2", godfather2.getTitle());
    }

    @Test
    public void testGetMovieByTitleNotFound() {
        // Søg efter en film, der ikke er i databasen (f.eks. "Non-existent Movie")
        Movie nonExistentMovie = movieDAO.getMovieByTitle("Non-existent Movie");
        Assertions.assertNull(nonExistentMovie);
    }
}
