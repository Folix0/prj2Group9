package entities;

import java.time.LocalDate;

public class Driver {

    private int id;
    private boolean isAvailable;
    private boolean hasHazardousLicense;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;


    public Driver() {
    }

    public Driver(int id, boolean isAvailable, boolean hasHazardousLicense, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, String address) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.hasHazardousLicense = hasHazardousLicense;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Driver(boolean isAvailable, boolean hasHazardousLicense, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, String address) {
        this.isAvailable = isAvailable;
        this.hasHazardousLicense = hasHazardousLicense;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                ", hasHazardousLicense=" + hasHazardousLicense +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
