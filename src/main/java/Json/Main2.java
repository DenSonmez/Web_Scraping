package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main2 {

    public static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public static void main(String[] args) {
        Person person = new Person("John", "Doe", "01-01-1970");
        Address address = new Address("Street", "City", "ZipCode");
        AccountInfo accountInfo = new AccountInfo("1234567890", "1000", true);

      AccountDTO accountDTO = new AccountDTO(person, address, accountInfo);

        String json = gson.toJson(accountDTO);
        System.out.println(json);
    }
}
