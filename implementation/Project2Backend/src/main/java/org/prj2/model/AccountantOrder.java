package org.prj2.model;

import java.sql.Date;

public class AccountantOrder {

    private int orderId;
    private int customerId;
    private double amount;
    private String destinationAddress;
    private int destinationPostcode;
    private String pickupAddress;
    private Date deliveryDate;
    private boolean hazardous;
    private String email;
    private double totalPrice;

    public AccountantOrder(){

    }

    public AccountantOrder(int customerId, double amount, String destinationAddress, int destinationPostcode, String pickupAddress, Date deliveryDate, boolean hazardous, String email, double totalPrice) {
        this.customerId = customerId;
        this.amount = amount;
        this.destinationAddress = destinationAddress;
        this.destinationPostcode = destinationPostcode;
        this.pickupAddress = pickupAddress;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public AccountantOrder(int orderId, int customerId, double amount, String destinationAddress, int destinationPostcode, String pickupAddress, Date deliveryDate, boolean hazardous, String email, double totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.destinationAddress = destinationAddress;
        this.destinationPostcode = destinationPostcode;
        this.pickupAddress = pickupAddress;
        this.deliveryDate = deliveryDate;
        this.hazardous = hazardous;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getDestinationPostcode() {
        return destinationPostcode;
    }

    public void setDestinationPostcode(int destinationPostcode) {
        this.destinationPostcode = destinationPostcode;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isHazardous() {
        return hazardous;
    }

    public void setHazardous(boolean hazardous) {
        this.hazardous = hazardous;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "AccountantOrder{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationPostcode=" + destinationPostcode +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", hazardous=" + hazardous +
                ", email='" + email + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
