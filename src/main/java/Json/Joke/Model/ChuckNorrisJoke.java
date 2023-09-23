package Json.Joke.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@ToString
public class ChuckNorrisJoke {

    private String value;
    private String url;

    public ChuckNorrisJoke(String value) {
        this.value = value;
    }
}
