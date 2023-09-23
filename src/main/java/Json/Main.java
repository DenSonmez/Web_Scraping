package Json;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) throws IOException {
        // Opret en GSON-instans for at behandle JSON-data
        Gson gson = new Gson();
        try (Reader reader = new FileReader("account.json")) {
            // Trin 1: Læs JSON-data fra filen og konverter til Java-objekter

            // Deserialize JSON-arrayet til en liste af Person-objekter
            Person[] people = gson.fromJson(reader, Person[].class);

            // Trin 2: Konverter Person-objekter til DTO-objekter
            // Opret et array til at indeholde DTO-objekter
            AccountDTO[] dtos = convertToDTOArray(people);

            // Trin 3: Udskriv DTO-objekterne i en pæn form
            // Udskriv DTO-objekterne i en pæn form
            printDTOArray(dtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metode til at konvertere et Person-objekt til et DTO-objekt
    public static AccountDTO convertToDTO(Person person) {
        // Opret et nyt DTO-objekt
        AccountDTO dto = new AccountDTO();

        // Trin 1: Kopier data fra Person til DTO
        dto.setFullName(person.getFirstName() + " " + person.getLastName());
        dto.setCity(person.getAddress().getCity());
        dto.setStreet(person.getAddress().getStreet());
        dto.setZipCode(person.getAddress().getZipCode());
        // Trin 2: Konverter boolesk status til en streng ("Aktiv" eller "Inaktiv")
        dto.setIsActive(person.getAccount().isActive() ? "Aktiv" : "Inaktiv");

        // Returner det færdige DTO-objekt
        return dto;
    }

    // Metode til at konvertere en liste af Person-objekter til en liste af DTO-objekter
    public static AccountDTO[] convertToDTOArray(Person[] people) {
        // Opret et array til at indeholde DTO-objekter med samme længde som Person-arrayet
        AccountDTO[] dtos = new AccountDTO[people.length];
        // Gennemløb hvert Person-objekt og konverter det til et DTO-objekt
        for (int i = 0; i < people.length; i++) {
            dtos[i] = convertToDTO(people[i]);
        }
        // Returner det resulterende array af DTO-objekter
        return dtos;
    }

    // Metode til at udskrive DTO-objekterne i en pæn form
    public static void printDTOArray(AccountDTO[] dtos) {
        // Gennemløb hvert DTO-objekt og udskriv oplysningerne i en pæn form
        for (AccountDTO dto : dtos) {
            System.out.println("Fulde Navn: " + dto.getFullName());
            System.out.println("By: " + dto.getCity());
            System.out.println("Gade: " + dto.getStreet());
            System.out.println("Postnummer: " + dto.getZipCode());
            System.out.println("Status: " + dto.getIsActive());
            System.out.println();
        }
    }
}


