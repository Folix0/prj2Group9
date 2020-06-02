package entities;

import java.time.LocalDate;

public class Owner extends User {

    public Owner(String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName,birthDate, email, phoneNumber, address);
    }
}
