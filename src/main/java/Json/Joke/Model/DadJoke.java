package Json.Joke.Model;

import lombok.Getter;

@Getter
public class DadJoke {
    private String id;
    private String joke;

    public DadJoke(String id, String joke) {
        this.id = id;
        this.joke = joke;
    }
}
