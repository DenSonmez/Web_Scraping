package WebScraping.WebScrapingAndAnalysis.TrustpilotModel;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString


@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(length = 10000)
    private String content;

    private int starRating;

    private String date;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Review(int id, String title, String content, int starRating) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.starRating = starRating;
    }

}

