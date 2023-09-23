package WebScraping.TrustPilot.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int reviewCount;

    @OneToMany(mappedBy = "person")
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


    public Person(String name, int reviewCount) {
        this.name = name;
        this.reviewCount = reviewCount;
    }

}
