package entities;

import java.time.LocalDate;

public class Trailer {

    //TODO add the attribute in diagram; not to be displaxyed in frontend
    private int id;
    private String trailerLicencePlate;
    private boolean isCleaned;
    private boolean isAvailable;
    private boolean isHazardous;
    private double capacity;
    private String trailerPickupLocation;
    private LocalDate maintenanceCheckdate;
    private double trailerWeight;

    public Trailer() {

    }

    public Trailer(String trailerLicencePlate, boolean isCleaned, boolean isAvailable, boolean isHazardous,
                   double capacity, String trailerPickupLocation, LocalDate maintenanceCheckdate, double trailerWeight) {
        this.isCleaned = isCleaned;
        this.trailerLicencePlate = trailerLicencePlate;
        this.isAvailable = isAvailable;
        this.isHazardous = isHazardous;
        this.capacity = capacity;
        this.trailerPickupLocation = trailerPickupLocation;
        this.maintenanceCheckdate = maintenanceCheckdate;
        this.trailerWeight = trailerWeight;
    }

    public Trailer(int id, String trailerLicencePlate, boolean isCleaned, boolean isAvailable, boolean isHazardous,
                   double capacity, String trailerPickupLocation, LocalDate maintenanceCheckdate, double trailerWeight) {
        this.id = id;
        this.isCleaned = isCleaned;
        this.trailerLicencePlate = trailerLicencePlate;
        this.isAvailable = isAvailable;
        this.isHazardous = isHazardous;
        this.capacity = capacity;
        this.trailerPickupLocation = trailerPickupLocation;
        this.maintenanceCheckdate = maintenanceCheckdate;
        this.trailerWeight = trailerWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrailerLicencePlate() {
        return trailerLicencePlate;
    }

    public void setTrailerLicencePlate(String trailerLicencePlate) {
        this.trailerLicencePlate = trailerLicencePlate;
    }

    public boolean isCleaned() {
        return isCleaned;
    }

    public void setCleaned(boolean cleaned) {
        isCleaned = cleaned;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isHazardous() {
        return isHazardous;
    }

    public void setHazardous(boolean hazardous) {
        isHazardous = hazardous;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getTrailerPickupLocation() {
        return trailerPickupLocation;
    }

    public void setTrailerPickupLocation(String trailerPickupLocation) {
        this.trailerPickupLocation = trailerPickupLocation;
    }

    public LocalDate getMaintenanceCheckdate() {
        return maintenanceCheckdate;
    }

    public void setMaintenanceCheckdate(LocalDate maintenanceCheckdate) {
        this.maintenanceCheckdate = maintenanceCheckdate;
    }

    public double getTrailerWeight() {
        return trailerWeight;
    }

    public void setTrailerWeight(double trailerWeight) {
        this.trailerWeight = trailerWeight;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id +
                ", trailerLicencePlate='" + trailerLicencePlate + '\'' +
                ", isCleaned=" + isCleaned +
                ", isAvailable=" + isAvailable +
                ", isHazardous=" + isHazardous +
                ", capacity=" + capacity +
                ", trailerPickupLocation='" + trailerPickupLocation + '\'' +
                ", maintenanceCheckdate=" + maintenanceCheckdate +
                ", trailerWeight=" + trailerWeight +
                '}';
    }
}
