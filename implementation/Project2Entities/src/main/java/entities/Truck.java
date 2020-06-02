package entities;

import java.time.LocalDate;

public class Truck {

    private int id;
    private String truckLicensePlate;
    private boolean isAvailable;
    private double truckWeight;
    private int mileage;
    private String truckPickupLocation;
    private LocalDate maintenanceCheckdate;

    public Truck(){

    }

    public Truck(String truckLicensePlate, boolean isAvailable, double truckWeight, int mileage,
                 String truckPickupLocation, LocalDate maintenanceCheckdate) {
        this.truckLicensePlate = truckLicensePlate;
        this.isAvailable = isAvailable;
        this.truckWeight = truckWeight;
        this.mileage = mileage;
        this.truckPickupLocation = truckPickupLocation;
        this.maintenanceCheckdate = maintenanceCheckdate;
    }
    public Truck(int id,String truckLicensePlate, boolean isAvailable, double truckWeight, int mileage,
                 String truckPickupLocation, LocalDate maintenanceCheckdate) {
        this.id=id;
        this.truckLicensePlate = truckLicensePlate;
        this.isAvailable = isAvailable;
        this.truckWeight = truckWeight;
        this.mileage = mileage;
        this.truckPickupLocation = truckPickupLocation;
        this.maintenanceCheckdate = maintenanceCheckdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getTruckWeight() {
        return truckWeight;
    }

    public void setTruckWeight(double truckWeight) {
        this.truckWeight = truckWeight;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getTruckPickupLocation() {
        return truckPickupLocation;
    }

    public void setTruckPickupLocation(String truckPickupLocation) {
        this.truckPickupLocation = truckPickupLocation;
    }

    public LocalDate getMaintenanceCheckdate() {
        return maintenanceCheckdate;
    }

    public void setMaintenanceCheckdate(LocalDate maintenanceCheckdate) {
        this.maintenanceCheckdate = maintenanceCheckdate;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", truckLicensePlate='" + truckLicensePlate + '\'' +
                ", isAvailable=" + isAvailable +
                ", truckWeight=" + truckWeight +
                ", mileage=" + mileage +
                ", truckPickupLocation='" + truckPickupLocation + '\'' +
                ", maintenanceCheckdate=" + maintenanceCheckdate +
                '}';
    }
}
