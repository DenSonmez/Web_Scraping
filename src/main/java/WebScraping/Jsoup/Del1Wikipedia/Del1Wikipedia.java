package WebScraping.Jsoup.Del1Wikipedia;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Del1Wikipedia {


    public static void main(String[] args) throws IOException {
        try {
            // Define the URL of the Wikipedia page
            String url = "https://en.wikipedia.org/wiki/Women_in_computing";

            // Fetch the page content using Jsoup
            Document document = Jsoup.connect(url).get();

            // Use Jsoup to select all image elements
            Elements imgElements = document.select("img");

            // Define a regular expression pattern to match image filenames
            Pattern pattern = Pattern.compile("/([^/]+)$");

            // Initialize a StringBuilder to store image names
            StringBuilder imageNames = new StringBuilder();

            // Iterate through image elements and apply the regular expression
            for (Element imgElement : imgElements) {
                // Get the "src" attribute of the image element
                String imgSrc = imgElement.attr("src");

                // Match the pattern against the "src" attribute
                Matcher matcher = pattern.matcher(imgSrc);

                // If a match is found, append the image name to the StringBuilder
                if (matcher.find()) {
                    String imageName = matcher.group(1); // Group 1 contains the image name
                    imageNames.append(imageName).append("\n");
                }
            }

            // Print the extracted image names with a header
            System.out.println("Extracted Image Names:");
            System.out.println(imageNames.toString().trim());

            // Data Cleaning: Remove HTML Tags
            String cleanedData = document.text();

            // Print the cleaned data with a header
            System.out.println("\nCleaned Data (without HTML tags):");
            System.out.println(cleanedData);

            // Display or Store Data in a File
            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(cleanedData);
                System.out.println("\nCleaned data is saved in 'output.txt'.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

