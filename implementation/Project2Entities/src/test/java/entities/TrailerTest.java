package entities;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


public class TrailerTest {
    private Trailer trailer = new Trailer(1, "LN-123", true, true, false,
            1500.50, "Street123", LocalDate.of(2020, 12, 12),
            1200.50);

    @Test
    public void GetTrailerIdTest() {
        assertThat(trailer.getId()).isEqualTo(1);
    }

    @Test
    public void SetTrailerIdTest() {
        trailer.setId(1);
        assertThat(trailer.getId()).isEqualTo(1);
    }

    @Test
    public void getTrailerLicensePlateTest() {
        assertThat(trailer.getTrailerLicencePlate()).isEqualTo("LN-123");
    }

    @Test
    public void setTrailerLicensePlateTest() {
        trailer.setTrailerLicencePlate("LN-124");
        assertThat(trailer.getTrailerLicencePlate()).isEqualTo("LN-124");
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
    public void isAvailableTest() {
        assertThat(trailer.isAvailable()).isEqualTo(true);

    }

    @Test
    public void setAvailableTest() {
        trailer.setAvailable(false);
        assertThat(trailer.isAvailable()).isEqualTo(false);

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
    public void setTrailerPickupLocationTest() {
        trailer.setTrailerPickupLocation("Street123");
        assertThat(trailer.getTrailerPickupLocation()).isEqualTo("Street123");

    }

    @Test
    public void getMaintenanceCheckdateTest() {
        assertThat(trailer.getMaintenanceCheckdate()).isEqualTo(LocalDate.of(2020, 12, 12));
    }

    @Test
    public void setMaintenanceCheckdateTest() {
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

    @Test
    public void testToString()
    {
        String expected = "Trailer{" +
                "id=" + trailer.getId() +
                ", trailerLicencePlate='" + trailer.getTrailerLicencePlate() + '\'' +
                ", isCleaned=" + trailer.isCleaned() +
                ", isAvailable=" + trailer.isAvailable() +
                ", isHazardous=" + trailer.isHazardous() +
                ", capacity=" + trailer.getCapacity() +
                ", trailerPickupLocation='" + trailer.getTrailerPickupLocation() + '\'' +
                ", maintenanceCheckdate=" + trailer.getMaintenanceCheckdate() +
                ", trailerWeight=" + trailer.getTrailerWeight() +
                '}';
        Assert.assertEquals(expected, trailer.toString());
    }
}