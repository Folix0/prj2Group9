package entities;

import java.time.LocalDate;

public class Accountant extends User {
    private final int id;

    public Accountant(int id, String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName,birthDate, email, phoneNumber, address);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Accountant{" +
                "id=" + id +
                "} " + super.toString();
    }
}



