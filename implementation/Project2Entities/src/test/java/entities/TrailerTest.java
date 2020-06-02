/*package entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


public class TrailerTest {


    Trailer trailer;


    @Before
    public void setUp() throws Exception {
        trailer = new Trailer(true, "LN-123", 1, true, false,
                1500.50, "Street123", LocalDate.of(2020, 12, 12),
                1200.50);
    }



    @Test
    public void isCleanedTest() {
        assertThat(trailer.isCleaned()).isEqualTo(true);
    }

    @Test
    public void setCleanedTest() {
        trailer.setCleaned(true);
        assertThat(trailer.isCleaned()).isEqualTo(true);
    }



    @Test
    public void getTrailerLicensePlateTest() {
        assertThat(trailer.getTrailerLicencePlate()).isEqualTo("LN-123");
    }

    @Test
    public void setTrailerLicensePlateTest() {
        trailer.setTrailerPickupLocation("LN-123");
        assertThat(trailer.getTrailerPickupLocation()).isEqualTo("LN-123");
    }



    @Test
    public void testGetTrailerId() {
        assertThat(trailer.getTrailerId()).isEqualTo(1);
    }

    @Test
    public void testSetTrailerId() {
        trailer.setTrailerId(1);
        assertThat(trailer.getTrailerId()).isEqualTo(1);
    }



    @Test
    public void isAvailableTest() {
        assertThat(trailer.isAvailable()).isEqualTo(true);

    }

    @Test
    public void setAvailableTest() {
        trailer.setAvailable(true);
        assertThat(trailer.isAvailable()).isEqualTo(true);

    }


    @Test
    public void getIsHazardousTest() {
        assertThat(trailer.isHazardous()).isEqualTo(false);
    }

    @Test
    public void setIsHazardousTest() {
        trailer.setHazardous(false);
        assertThat(trailer.isHazardous()).isEqualTo(false);
    }



    @Test
    public void getCapacityTest() {
        assertThat(trailer.getCapacity()).isEqualTo(1500.50);
    }

    @Test
    public void setCapacityTest() {
        trailer.setCapacity(1500.50);
        assertThat(trailer.getCapacity()).isEqualTo(1500.50);
    }



    @Test
    public void getTrailerPickupLocationTest() {
        assertThat(trailer.getTrailerPickupLocation()).isEqualTo("Street123");
    }

    @Test
    public void setTrailerPickupLocation() {
        trailer.setTrailerPickupLocation("Street123");
        assertThat(trailer.getTrailerPickupLocation()).isEqualTo("Street123");

    }



    @Test
    public void getMaintenanceCheckdateTest() {
        assertThat(trailer.getMaintenanceCheckdate()).isEqualTo(LocalDate.of(2020, 12, 12));
    }

    @Test
    public void setMaintenanceCheckdate() {
        trailer.setMaintenanceCheckdate(LocalDate.of(2020, 12, 12));
        assertThat(trailer.getMaintenanceCheckdate()).isEqualTo(LocalDate.of(2020, 12, 12));
    }



    @Test
    public void getTrailerWeightTest() {
        assertThat(trailer.getTrailerWeight()).isEqualTo(1200.50);
    }

    @Test
    public void setTrailerWeightTest() {
        trailer.setTrailerWeight(1200.50);
        assertThat(trailer.getTrailerWeight()).isEqualTo(1200.50);
    }


    /*@Test
    void constructorTest(){
        Trailer t1 = new Trailer(true, true, 400, 55.50, "Home", LocalDate.of(2019, 12, 31));
        assertThat(t1.getCapacity()).isEqualTo(300);
        assertThat(t1.getTrailerWeight()).isEqualTo(500.0);
        Trailer t2 = new Trailer(true, true, 900, 8000, "Home", LocalDate.of(2019, 12, 31));
        assertThat(t2.getCapacity()).isEqualTo(6000);
        assertThat(t2.getTrailerWeight()).isEqualTo(800);


    }
}
        */