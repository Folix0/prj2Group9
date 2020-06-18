package entities;

import java.time.LocalDate;

public class Driver extends User{

    private int id;
    private boolean isAvailable;
    private boolean hasHazardousLicense;

    public Driver(int id, boolean isAvailable, boolean hasHazardousLicense, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, String address) {
        super(firstName, lastName, birthDate, email, phoneNumber, address);
        this.id = id;
        this.isAvailable = isAvailable;
        this.hasHazardousLicense = hasHazardousLicense;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isHasHazardousLicense() {
        return hasHazardousLicense;
    }

    public void setHasHazardousLicense(boolean hasHazardousLicense) {
        this.hasHazardousLicense = hasHazardousLicense;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                ", hasHazardousLicense=" + hasHazardousLicense +
                "} " + super.toString();
    }
}
