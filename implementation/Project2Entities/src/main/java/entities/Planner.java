package entities;

import java.time.LocalDate;

public class Planner extends User {

    private int id;

    public Planner() {
    }


    public Planner(String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName, birthDate, email, phoneNumber, address);
    }

    public Planner(int id, String firstName, String lastName, String email, LocalDate birthDate, String phoneNumber, String address) {
        super(firstName, lastName, birthDate, email, phoneNumber, address);
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
        return "Planner{" +
                "id=" + id +
                '}';
    }
}
