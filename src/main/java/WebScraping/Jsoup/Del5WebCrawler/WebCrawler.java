package WebScraping.Jsoup.Del5WebCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

    public class WebCrawler {
        public static void main(String[] args) {
            // List of URLs to crawl
            List<String> urlsToCrawl = new ArrayList<>();
            urlsToCrawl.add("https://beginnersbook.com/page1");
            urlsToCrawl.add("https://beginnersbook.com/java-tutorial-for-beginners-with-examples/page2");
            urlsToCrawl.add("https://beginnersbook.com/2014/01/c-tutorial-for-beginners-with-examples/page3");
            // Add more URLs as needed

            // Number of concurrent threads to use
            int numThreads = urlsToCrawl.size();

            // Create an ExecutorService with a thread pool
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

            // Create a list to hold future results
            List<Future<String>> futures = new ArrayList<>();

            // Submit tasks for each URL
            for (String url : urlsToCrawl) {
                Callable<String> callable = new WebCrawlTask(url);
                Future<String> future = executorService.submit(callable);
                futures.add(future);
            }

            // Process the results when each task is done
            for (Future<String> future : futures) {
                try {
                    String result = future.get(); // Get the result of the task
                    System.out.println("Crawled: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // Shutdown the executor service
            executorService.shutdown();
        }
    }

    class WebCrawlTask implements Callable<String> {
        private final String url;

        public WebCrawlTask(String url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            // Replace this with your web crawling logic
            try {
                // Simulate web crawling by sleeping for a while
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return url; // Return the crawled URL as the result
        }
    }

