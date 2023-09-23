package Json;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class AccountDTO {

    private String fullName;
    private String city;
    private String street;
    private String zipCode;
    private String isActive;


    public AccountDTO(Person person, Address address, AccountInfo accountInfo) {
        this.fullName = person.getFirstName() + " " + person.getLastName();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.zipCode = address.getZipCode();
        this.isActive = accountInfo.isActive() ? "Active" : "Inactive"; // ternary operator
    }
}

