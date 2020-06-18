package entities;

import static org.assertj.core.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

public class CustomerOrderTest {
    private CustomerOrder cus = new CustomerOrder(1, "firstName", "lastName",
            LocalDate.of(2000, 1, 1), "email", "phoneNumber",
            "address", "pickUpAddress", "destinationAddress",
            "postcode", 99.99, LocalDate.of(2001, 1 ,1),
            true, 100.10, "orderStatus");


    @Test
    public void getCustomerOrderIdTest() {
        assertThat(cus.getCustomerOrderId()).isEqualTo(1);
    }

    @Test
    public void getFirstNameTest() {
        assertThat(cus.getFirstName()).isEqualTo("firstName");
    }

    @Test
    public void getLastNameTest() {
        assertThat(cus.getLastName()).isEqualTo("lastName");
    }

    @Test
    public void setLastNameTest() {
        cus.setLastName("newLastName");
        assertThat(cus.getLastName()).isEqualTo("newLastName");
    }

    @Test
    public void getBirthDateTest() {
        assertThat(cus.getBirthDate()).isEqualTo(LocalDate.of(2000,1,1));
    }

    @Test
    public void getEmailTest() {
        assertThat(cus.getEmail()).isEqualTo("email");
    }

    @Test
    public void setEmailTest() {
        cus.setEmail("newEmail");
        assertThat(cus.getEmail()).isEqualTo("newEmail");
    }

    @Test
    public void getPhoneNumberTest() {
        assertThat(cus.getPhoneNumber()).isEqualTo("phoneNumber");
    }

    @Test
    public void setPhoneNumberTest() {
        cus.setPhoneNumber("newPhonenumber");
        assertThat(cus.getPhoneNumber()).isEqualTo("newPhonenumber");
    }

    @Test
    public void getAddressTest() {
        assertThat(cus.getAddress()).isEqualTo("address");
    }

    @Test
    public void setAddressTest() {
        cus.setAddress("aaa");
        assertThat(cus.getAddress()).isEqualTo("aaa");
    }

    @Test
    public void getPickUpAddressTest() {
        assertThat(cus.getPickUpAddress()).isEqualTo("pickUpAddress");
    }

    @Test
    public void setPickUpAddressTest() {
        cus.setPickUpAddress("aaa");
        assertThat(cus.getPickUpAddress()).isEqualTo("aaa");
    }

    @Test
    public void getDestinationAddressTest() {
        assertThat(cus.getDestinationAddress()).isEqualTo("destinationAddress");
    }

    @Test
    public void setDestinationAddressTest() {
        cus.setDestinationAddress("aaa");
        assertThat(cus.getDestinationAddress()).isEqualTo("aaa");
    }

    @Test
    public void getPostcodeTest() {
        assertThat(cus.getPostcode()).isEqualTo("postcode");
    }

    @Test
    public void setPostcodeTest() {
        cus.setPostcode("aaa");
        assertThat(cus.getPostcode()).isEqualTo("aaa");
    }

    @Test
    public void getAmountTest() {
        assertThat(cus.getAmount()).isEqualTo(99.99);
    }

    @Test
    public void setAmountTest() {
        cus.setAmount(99.1);
        assertThat(cus.getAmount()).isEqualTo(99.1);
    }

    @Test
    public void getDeliveryDateTest() {
        assertThat(cus.getDeliveryDate()).isEqualTo(LocalDate.of(2001, 1 ,1));
    }

    @Test
    public void setDeliveryDateTest() {
        cus.setDeliveryDate(LocalDate.of(2001, 1 ,2));
        assertThat(cus.getDeliveryDate()).isEqualTo(LocalDate.of(2001, 1 ,2));
    }

    @Test
    public void isHazardousTest(){
        assertThat(cus.isHazardous()).isTrue();
    }

    @Test
    public void setHazardousTest() {
        cus.setHazardous(false);
        assertThat(cus.isHazardous()).isFalse();
    }

    @Test
    public void getProposedPriceTest(){
        assertThat(cus.getProposedPrice()).isEqualTo(100.10);
    }

    @Test
    public void setProposedPriceTest() {
        cus.setProposedPrice(11.99);
        assertThat(cus.getProposedPrice()).isEqualTo(11.99);
    }

    @Test
    public void  getOrderStatusTest() {
        assertThat(cus.getOrderStatus()).isEqualTo("orderStatus");
    }

    @Test
    public void setOrderStatusTest() {
        cus.setOrderStatus("newStatus");
        assertThat(cus.getOrderStatus()).isEqualTo("newStatus");
    }

    @Test
    public void testToString()
    {
        String expected = "CustomerOrder{" +
                "id=" + cus.getCustomerOrderId() +
                ", firstName='" + cus.getFirstName() + '\'' +
                ", lastName='" + cus.getLastName() + '\'' +
                ", birthDate=" + cus.getBirthDate() +
                ", email='" + cus.getEmail() + '\'' +
                ", phoneNumber='" + cus.getPhoneNumber() + '\'' +
                ", address='" + cus.getAddress() + '\'' +
                ", pickUpAddress='" + cus.getPickUpAddress() + '\'' +
                ", destinationAddress='" + cus.getDestinationAddress() + '\'' +
                ", postcode='" + cus.getPostcode() + '\'' +
                ", amount=" + cus.getAmount() +
                ", deliveryDate=" + cus.getDeliveryDate() +
                ", hazardous=" + cus.isHazardous() +
                ", proposedPrice=" + cus.getProposedPrice() +
                ", orderStatus=" + cus.getOrderStatus() +
                '}';
        Assert.assertEquals(expected, cus.toString());
    }
}
