package entities;

import java.time.LocalDate;

public class Order {
    private String pickUpAddress;
    private String destinationAddress;
    private String postcode;
    private double amount;
    private LocalDate deliveryDate;
    private boolean hazardous;

    public Order(String pickUpAddress, String destinationAddress, String postcode, double amount, LocalDate deliveryDate, boolean hazardous) {
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.postcode = postcode;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
    }

    //public Order(){}

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isHazardous() {
        return hazardous;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pickUpAddress='" + pickUpAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", amount=" + amount +
                ", deliveryDate=" + deliveryDate +
                ", hazardous=" + hazardous +
                '}';
    }
}
