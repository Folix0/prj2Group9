package entities;

import java.time.LocalDate;

public class WorkOrderPlan {

    private final int id;
    private int orderId;
    private int driverId;
    private String trailerLicencePlate;
    private String truckLicencePlate;
    private String trailerPickupLocation;
    private double loadAmount;
    private boolean hazardousLiquid;
    private boolean trailerCleaned;
    private String deliveryAddress;
    private String deliveryPostcode;
    private LocalDate deliveryDate;

    public WorkOrderPlan(int id, int orderId, int driverId, String trailerLicencePlate, String truckLicencePlate,
                         String trailerPickupLocation, double loadAmount, boolean hazardousLiquid, boolean trailerCleaned,
                         String deliveryAddress, String deliveryPostcode, LocalDate deliveryDate) {
        this.id = id;
        this.orderId = orderId;
        this.driverId = driverId;
        this.trailerLicencePlate = trailerLicencePlate;
        this.truckLicencePlate = truckLicencePlate;
        this.trailerPickupLocation = trailerPickupLocation;
        this.loadAmount = loadAmount;
        this.hazardousLiquid = hazardousLiquid;
        this.trailerCleaned = trailerCleaned;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPostcode = deliveryPostcode;
        this.deliveryDate = deliveryDate;
    }


    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getTrailerLicencePlate() {
        return trailerLicencePlate;
    }

    public void setTrailerLicencePlate(String trailerLicencePlate) {
        this.trailerLicencePlate = trailerLicencePlate;
    }

    public String getTruckLicencePlate() {
        return truckLicencePlate;
    }

    public void setTruckLicencePlate(String truckLicencePlate) {
        this.truckLicencePlate = truckLicencePlate;
    }

    public String getTrailerPickupLocation() {
        return trailerPickupLocation;
    }

    public void setTrailerPickupLocation(String trailerPickupLocation) {
        this.trailerPickupLocation = trailerPickupLocation;
    }

    public double getLoadAmount() {
        return loadAmount;
    }

    public void setLoadAmount(double loadAmount) {
        this.loadAmount = loadAmount;
    }

    public boolean isHazardousLiquid() {
        return hazardousLiquid;
    }

    public void setHazardousLiquid(boolean hazardousLiquid) {
        this.hazardousLiquid = hazardousLiquid;
    }

    public boolean isTrailerCleaned() {
        return trailerCleaned;
    }

    public void setTrailerCleaned(boolean trailerCleaned) {
        this.trailerCleaned = trailerCleaned;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPostcode() {
        return deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "WorkOrderPlan{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", driverId=" + driverId +
                ", trailerLicencePlate='" + trailerLicencePlate + '\'' +
                ", truckLicencePlate='" + truckLicencePlate + '\'' +
                ", trailerPickupLocation='" + trailerPickupLocation + '\'' +
                ", loadAmount=" + loadAmount +
                ", hazardousLiquid=" + hazardousLiquid +
                ", trailerCleaned=" + trailerCleaned +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPostcode='" + deliveryPostcode + '\'' +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}

