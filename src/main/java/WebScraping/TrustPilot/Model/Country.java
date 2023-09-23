package WebScraping.TrustPilot.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Person> people = new HashSet<>();

    public Country(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
