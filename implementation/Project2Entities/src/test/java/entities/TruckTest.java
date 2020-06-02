/*package entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TruckTest {


    Truck truck;

    @Before
    public void setUp() throws Exception {
        truck = new Truck("KF-12", 1, true, 1200.50, 124,
                "Street123", LocalDate.of(2020, 01, 24));
    }


    @Test
    public void getTruckLicencePlateTest() {
        assertThat(truck.getTruckLicensePlate()).isEqualTo("KF-12");
    }


    @Test
    public void getTruckIdTest() {
        assertThat(truck.getTruckId()).isEqualTo(1);
    }

    @Test
    public void setTruckIdTest() {
        truck.setTruckId(1);
        assertThat(truck.getTruckId()).isEqualTo(1);
    }

    @Test
    void getTruckWeightTest() {
        assertThat(t.getTruckWeight()).isEqualTo(5000.1);
    }

    @Test
    void isAvailableTest() {
        assertThat(t.isAvailable()).isEqualTo(true);
    }

    @Test
    void setAvailableTest() {
        t.setAvailable(true);
        assertThat(t.isAvailable()).isEqualTo(true);
    }

    @Test
    void getMaintenanceCheckDateTest() {
        assertThat(t.getMaintenanceCheckDate()).isEqualTo(LocalDate.of(1999, 11, 11));
    }

    @Test
    void setMaintenanceCheckDateTest() {
        t.setMaintenanceCheckDate(LocalDate.of(1999, 11, 11));
        assertThat(t.getMaintenanceCheckDate()).isEqualTo(LocalDate.of(1999, 11, 11));
    }

    @Test
    void getMileageTest() {
        assertThat(t.getMileage()).isEqualTo(235);
    }

    @Test
    void setMileageTest() {
        t.setMileage(235);
        assertThat(t.getMileage()).isEqualTo(235);
    }

    @Test
    void getHazardousTest() {
        assertThat(t.isHazardous()).isEqualTo(true);
        Truck t1 = new Truck(555.5, true, LocalDate.of(1999, 11, 11), 235, false);
        assertThat(t1.isHazardous()).isEqualTo(false);

    }

    @Test
    void constructorTest() {
        Truck t1 = new Truck(555.5, true, LocalDate.of(1999, 11, 11), 235, true);
        assertThat(t1.getTruckWeight()).isEqualTo(5000);
        Truck t2 = new Truck(20000.0, true, LocalDate.of(1999, 11, 11), 235, true);
        assertThat(t2.getTruckWeight()).isEqualTo(15000.0);
    }
}*/