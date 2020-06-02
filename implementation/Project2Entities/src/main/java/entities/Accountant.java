package entities;

import java.time.LocalDate;

public class Accountant extends User {
    private int id;

    public Accountant(){}

    public Accountant(String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName,birthDate, email, phoneNumber, address);
    }

    public Accountant(int id, String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName,birthDate, email, phoneNumber, address);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }
}



