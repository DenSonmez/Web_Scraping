package Json.Joke.Util;

import Json.Joke.Model.ChuckNorrisJoke;
import Json.Joke.Model.CombinedJokeDTO;
import Json.Joke.Model.DadJoke;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.*;

import static Json.Main2.gson;

public class JokeFetcher {
    private final String chuck = "https://api.chucknorris.io/jokes/random";
    private final String dad = "https://icanhazdadjoke.com/";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public String fetchData(String url) throws IOException {
        return HttpUtils.fetchData(url);
    }


    public String getJokesSequential() throws IOException, ExecutionException, InterruptedException {

        String c = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        String d = HttpUtils.fetchData("https://icanhazdadjoke.com");
                ChuckNorrisJoke chuck = gson.fromJson(c, ChuckNorrisJoke.class);
                     DadJoke dad = gson.fromJson(d, DadJoke.class);




        CombinedJokeDTO dto = new CombinedJokeDTO(chuck, dad);

        return gson.toJson(dto);

    }


    public String getJokePralle () throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<ChuckNorrisJoke> chuckNorrisFuture = es.submit(
                () -> gson.fromJson(fetchData(chuck), ChuckNorrisJoke.class)
        );
        Future<DadJoke> dadJokeFutureFuture = es.submit(
                () -> gson.fromJson(fetchData(dad), DadJoke.class)
        );

        ChuckNorrisJoke chuckNorrisJoke = chuckNorrisFuture.get();
        DadJoke dadJoke = dadJokeFutureFuture.get();

        CombinedJokeDTO dto = new CombinedJokeDTO(chuckNorrisJoke, dadJoke);
        es.shutdown();

        return gson.toJson(dto);
    }
}
