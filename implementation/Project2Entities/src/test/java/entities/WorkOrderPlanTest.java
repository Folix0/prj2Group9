package entities;

import static org.assertj.core.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class WorkOrderPlanTest {
    private WorkOrderPlan wop = new WorkOrderPlan(1, 2, 3, "trailerLicencePlate", "truckLicencePlate",
    "trailerPickupLocation", 11.9, true, true,
    "deliveryAddress", "deliveryPostcode", LocalDate.of(2020, 1, 6));

    @Test
    public void getIdTest() {
        assertThat(wop.getId()).isEqualTo(1);
    }

    @Test
    public void getOrderIdTest() {
        assertThat(wop.getOrderId()).isEqualTo(2);
    }

    @Test
    public void setOrderIdTest() {
        wop.setOrderId(4);
        assertThat(wop.getOrderId()).isEqualTo(4);
    }

    @Test
    public void getDriverIdTest() {
        assertThat(wop.getDriverId()).isEqualTo(3);
    }

    @Test
    public void setDriverIdTest() {
        wop.setDriverId(9);
        assertThat(wop.getDriverId()).isEqualTo(9);
    }

    @Test
    public void getTrailerLicencePlateTest() {
        assertThat(wop.getTrailerLicencePlate()).isEqualTo("trailerLicencePlate");
    }

    @Test
    public void setTrailerLicencePlateTest() {
        wop.setTrailerLicencePlate("newLicencePlate");
        assertThat(wop.getTrailerLicencePlate()).isEqualTo("newLicencePlate");
    }

    @Test
    public void getTruckLicencePlateTest() {
        assertThat(wop.getTruckLicencePlate()).isEqualTo("truckLicencePlate");
    }

    @Test
    public void setTruckLicencePlateTest() {
        wop.setTruckLicencePlate("newTruckLicencePlate");
        assertThat(wop.getTruckLicencePlate()).isEqualTo("newTruckLicencePlate");
    }

    @Test
    public void getTrailerPickupLocationTest() {
        assertThat(wop.getTrailerPickupLocation()).isEqualTo("trailerPickupLocation");
    }

    @Test
    public void setTrailerPickupLocationTest() {
        wop.setTrailerPickupLocation("newTrailerPickupLocation");
        assertThat(wop.getTrailerPickupLocation()).isEqualTo("newTrailerPickupLocation");
    }

    @Test
    public void getLoadAmountTest() {
        assertThat(wop.getLoadAmount()).isEqualTo(11.9);
    }

    @Test
    public void setLoadAmountTest() {
        wop.setLoadAmount(11.1);
        assertThat(wop.getLoadAmount()).isEqualTo(11.1);
    }

    @Test
    public void isHazardousLiquidTest() {
        assertThat(wop.isHazardousLiquid()).isTrue();
    }

    @Test
    public void setHazardousLiquidTest() {
        wop.setHazardousLiquid(false);
        assertThat(wop.isHazardousLiquid()).isFalse();
    }

    @Test
    public void isTrailerCleanedTest() {
        assertThat(wop.isTrailerCleaned()).isTrue();
    }

    @Test
    public void setTrailerCleanedTest() {
        wop.setTrailerCleaned(false);
        assertThat(wop.isTrailerCleaned()).isFalse();
    }

    @Test
    public void getDeliveryAddressTest() {
        assertThat(wop.getDeliveryAddress()).isEqualTo("deliveryAddress");
    }

    @Test
    public void setDeliveryAddressTest() {
        wop.setDeliveryAddress("newDeliveryAddress");
        assertThat(wop.getDeliveryAddress()).isEqualTo("newDeliveryAddress");
    }

    @Test
    public void getDeliveryPostcodeTest() {
        assertThat(wop.getDeliveryPostcode()).isEqualTo("deliveryPostcode");
    }

    @Test
    public void setDeliveryPostcodeTest() {
        wop.setDeliveryPostcode("aaaa");
        assertThat(wop.getDeliveryPostcode()).isEqualTo("aaaa");
    }

    @Test
    public void getDeliveryDateTest() {
        assertThat(wop.getDeliveryDate()).isEqualTo(LocalDate.of(2020, 1, 6));
    }

    @Test
    public void setDeliveryDateTest() {
        wop.setDeliveryDate(LocalDate.of(2020, 1, 7));
        assertThat(wop.getDeliveryDate()).isEqualTo(LocalDate.of(2020, 1, 7));
    }

    @Test
    public void testToString()
    {
        String expected = "WorkOrderPlan{" +
                "id=" + wop.getId() +
                ", orderId=" + wop.getOrderId() +
                ", driverId=" + wop.getDriverId() +
                ", trailerLicencePlate='" + wop.getTrailerLicencePlate() + '\'' +
                ", truckLicencePlate='" + wop.getTruckLicencePlate() + '\'' +
                ", trailerPickupLocation='" + wop.getTrailerPickupLocation() + '\'' +
                ", loadAmount=" + wop.getLoadAmount() +
                ", hazardousLiquid=" + wop.isHazardousLiquid() +
                ", trailerCleaned=" + wop.isTrailerCleaned() +
                ", deliveryAddress='" + wop.getDeliveryAddress() + '\'' +
                ", deliveryPostcode='" + wop.getDeliveryPostcode() + '\'' +
                ", deliveryDate=" + wop.getDeliveryDate() +
                '}';
        Assert.assertEquals(expected, wop.toString());
    }
}
