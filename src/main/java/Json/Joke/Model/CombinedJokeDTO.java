package Json.Joke.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CombinedJokeDTO {

  private ChuckNorrisJoke chuckNorrisJoke;
  private DadJoke dadJoke;


      @Override
    public String toString() {
        return "ChuckJoke " + chuckNorrisJoke + " DadJoke " + dadJoke;
    }
}
