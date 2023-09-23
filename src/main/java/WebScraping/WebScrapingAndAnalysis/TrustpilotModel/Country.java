package WebScraping.WebScrapingAndAnalysis.TrustpilotModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString


@Entity
public class Country {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Person> people;

    public Country(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
