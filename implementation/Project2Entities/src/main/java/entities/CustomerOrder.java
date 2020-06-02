package entities;

import java.time.LocalDate;

public class CustomerOrder {

    private int customerOrderId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String pickUpAddress;
    private String destinationAddress;
    private String postcode;
    private double amount;
    private LocalDate deliveryDate;
    private boolean hazardous;
    private double proposedPrice;
    //private String orderStatus;
    private String orderStatus;

    public CustomerOrder() {
    }

    public CustomerOrder(int customerOrderId,String email, String pickUpAddress, String destinationAddress, String postcode,
                         double amount, LocalDate deliveryDate, boolean hazardous, double proposedPrice) {
        this.customerOrderId = customerOrderId;
        this.email = email;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.postcode = postcode;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.proposedPrice = proposedPrice;
    }

    public CustomerOrder(String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber,
                         String address, String pickUpAddress, String destinationAddress, String postcode, double amount,
                         LocalDate deliveryDate, boolean hazardous, double proposedPrice, String orderStatus) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.postcode = postcode;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.proposedPrice = proposedPrice;
        this.orderStatus = orderStatus;
    }

    public CustomerOrder(int customerOrderId, String firstName, String lastName, LocalDate birthDate, String email,
                         String phoneNumber, String address, String pickUpAddress, String destinationAddress,
                         String postcode, double amount, LocalDate deliveryDate, boolean hazardous, double proposedPrice,
                         String orderStatus) {
        this.customerOrderId = customerOrderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.postcode = postcode;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.proposedPrice = proposedPrice;
        this.orderStatus = orderStatus;
    }

    public CustomerOrder(int customerOrderId, String firstName, String lastName, LocalDate birthDate, String email,
                         String phoneNumber, String address, String pickUpAddress, String destinationAddress,
                         String postcode, double amount, LocalDate deliveryDate, boolean hazardous, double proposedPrice) {
        this.customerOrderId = customerOrderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.postcode = postcode;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.proposedPrice = proposedPrice;
    }


    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
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

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isHazardous() {
        return hazardous;
    }

    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    public double getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(double proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public String  getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + customerOrderId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", pickUpAddress='" + pickUpAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", amount=" + amount +
                ", deliveryDate=" + deliveryDate +
                ", hazardous=" + hazardous +
                ", proposedPrice=" + proposedPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }


}
