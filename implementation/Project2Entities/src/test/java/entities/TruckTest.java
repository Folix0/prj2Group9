package entities;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class TruckTest {
    private Truck truck = new Truck(1, "KF-12", true, 1200.50, 124,
            "Street123", LocalDate.of(2020, 1, 24));

    @Test
    public void getTruckIdTest() {
        assertThat(truck.getId()).isEqualTo(1);
    }

    @Test
    public void setTruckIdTest() {
        truck.setId(1);
        assertThat(truck.getId()).isEqualTo(1);
    }

    @Test
    public void getTruckLicencePlateTest() {
        assertThat(truck.getTruckLicensePlate()).isEqualTo("KF-12");
    }

    @Test
    public void isAvailableTest() {
        assertThat(truck.isAvailable()).isEqualTo(true);
    }

    @Test
    public void setAvailableTest() {
        truck.setAvailable(true);
        assertThat(truck.isAvailable()).isEqualTo(true);
    }

    @Test
    public void getTruckWeightTest() {
        assertThat(truck.getTruckWeight()).isEqualTo(1200.5);
    }

    @Test
    public void getMileageTest() {
        assertThat(truck.getMileage()).isEqualTo(124);
    }

    @Test
    public void setMileageTest() {
        truck.setMileage(235);
        assertThat(truck.getMileage()).isEqualTo(235);
    }

    @Test
    public void getTruckPickupLocationTest() {
        assertThat(truck.getTruckPickupLocation()).isEqualTo("Street123");
    }

    @Test
    public void setTruckPickupLocationTest() {
        truck.setTruckPickupLocation("Street555");
        assertThat(truck.getTruckPickupLocation()).isEqualTo("Street555");
    }

    @Test
    public void getMaintenanceCheckDateTest() {
        assertThat(truck.getMaintenanceCheckdate()).isEqualTo(LocalDate.of(2020, 01, 24));
    }

    @Test
    public void setMaintenanceCheckDateTest() {
        truck.setMaintenanceCheckdate(LocalDate.of(1999, 11, 11));
        assertThat(truck.getMaintenanceCheckdate()).isEqualTo(LocalDate.of(1999, 11, 11));
    }

    @Test
    public void testToString()
    {
        String expected = "Truck{" +
                "id=" + truck.getId() +
                ", truckLicensePlate='" + truck.getTruckLicensePlate() + '\'' +
                ", isAvailable=" + truck.isAvailable() +
                ", truckWeight=" + truck.getTruckWeight() +
                ", mileage=" + truck.getMileage() +
                ", truckPickupLocation='" + truck.getTruckPickupLocation() + '\'' +
                ", maintenanceCheckdate=" + truck.getMaintenanceCheckdate() +
                '}';
        Assert.assertEquals(expected, truck.toString());
    }
}