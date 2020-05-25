package entities;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderTest {

    Order order;

    @Before
    public void setUp() throws Exception {
        order = new Order("AStreet", "BStreet", "123456", 3500.50,
                LocalDate.parse("2020-02-14"), true);
    }

    @Test
    public void testGetPickUpAddress() {
        String actual = order.getPickUpAddress();
        String exp = "AStreet";
        assertEquals(actual, exp);
    }

    @Test
    public void testSetPickUpAddress() {
        order.setPickUpAddress("AStreet");
        assertTrue(order.getPickUpAddress() == "AStreet");
    }

    @Test
    public void testGetDestinationAddress() {
        String actual = order.getDestinationAddress();
        String exp = "BStreet";
        assertEquals(actual, exp);
    }

    @Test
    public void testSetDestinationAddress() {
        order.setDestinationAddress("BStreet");
        assertTrue(order.getDestinationAddress() == "BStreet");
    }

    @Test
    public void testGetPostcode() {
        String actual = order.getPostcode();
        String exp = "123456";
        assertEquals(actual, exp);
    }

    @Test
    public void testSetPostcode() {
        order.setDestinationAddress("123456");
        assertTrue(order.getPostcode() == "123456");
    }

    @Test
    public void testGetAmount() {
        double actual = order.getAmount();
        double exp = 3500.50;
        //assertEquals(actual, exp);
        //assertEquals(actual, exp, delta);
        assertTrue(actual == exp);
    }

    @Test
    public void testSetAmount() {
        order.setAmount(3500.50);
        assertTrue(order.getAmount() == 3500.50);
    }

    @Test
    public void testIsHazardous() {
        boolean actual = order.isHazardous();
        boolean exp = true;
        //assertEquals(actual, exp);
        assertEquals(actual, exp);
    }

    @Test
    public void testSetIsHazardous() {
        order.setHazardous(true);
        assertTrue(order.isHazardous() == true);
    }

    @Test
    public void testToString() {
        String actual = order.toString();
        String exp = "Order{" + "pickupAddress=" + order.getPickUpAddress() + ", destinationAddress=" + order.getDestinationAddress()
                + ", postcode=" + order.getPostcode() + ", amount=" + order.getAmount() + ", deliveryDate=" + order.getDeliveryDate()
                + ", hazardous=" + order.isHazardous() + "}";
    }

    @Test
    public void testGetDeliveryDate() {
        LocalDate date = LocalDate.parse("2020-02-14");
        assertThat(date).isEqualTo("2020-02-14");
    }

}
