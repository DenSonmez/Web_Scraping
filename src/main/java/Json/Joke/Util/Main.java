package Json.Joke.Util;

import Json.Joke.Model.ChuckNorrisJoke;
import Json.Joke.Model.CombinedJokeDTO;
import Json.Joke.Model.DadJoke;

import java.io.IOException;

import static Json.Main2.gson;

public class Main {
    private static final String chuck = "https://api.chucknorris.io/jokes/random";
    private  static  final String dad = "https://icanhazdadjoke.com/";

    public static void main(String[] args) throws IOException {


        try {
            DadJoke dadJoke = gson.fromJson(HttpUtils.fetchData(dad), DadJoke.class);
            ChuckNorrisJoke chuckNorrisJoke = gson.fromJson(HttpUtils.fetchData(chuck), ChuckNorrisJoke.class);
            CombinedJokeDTO combinedJokes = new CombinedJokeDTO(chuckNorrisJoke, dadJoke);

            System.out.println(combinedJokes);

            System.out.println(gson.toJson(combinedJokes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
