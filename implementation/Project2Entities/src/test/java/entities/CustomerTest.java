/*package entities;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("LK", "LK@d", LocalDate.parse("2020-02-14"),
                123456, "AStreet");
    }

    @Test
    public void testGetName() {
        String actual = customer.getName();
        String exp = "LK";
        assertEquals(actual, exp);
    }

    @Test
    public void testSetName() {
        customer.setName("LK");
        assertTrue(customer.getName() == "LK");
    }

    @Test
    public void testGetEmail() {
        String actual = customer.getEmail();
        String exp = "LK@d";
        assertEquals(actual, exp);
    }

    @Test
    public void testSetEmail() {
        customer.setEmail("LK@d");
        //assertTrue(customer.getName() == "LK@d");
        //assertTrue(customer.getEmail()=="LK@");
        assertTrue(customer.getEmail().equals("LK@d"));
    }

    @Test
    public void testGetBirthdate() {
        LocalDate actual = customer.getBirthDate();
        LocalDate exp = LocalDate.parse("2020-02-14");
        assertEquals(actual, exp);
    }


    @Test
    public void testGetPhoneNumber() {
        int actual = customer.getPhoneNumber();
        int exp = 123456;
        assertEquals(actual, exp);
    }

    @Test
    public void testSetPhoneNumber() {
        customer.setPhoneNumber(123456);
        assertTrue(customer.getPhoneNumber() == 123456);
    }

    @Test
    public void testGetAddress() {
        String actual = customer.getAddress();
        String exp = "AStreet";
        assertEquals(actual, exp);
    }

    @Test
    public void setGetAddress() {
        customer.setAddress("AStreet");
        assertTrue(customer.getAddress() == "AStreet");
    }

    @Test
    public void testToString() {
        String actual = customer.toString();
        customer.setName("LK");
        customer.setEmail("LK@d");
        customer.setPhoneNumber(123456);
        customer.setAddress("AStreet");
        customer.setBirthDate(LocalDate.parse("2020-02-14"));
        assertThat(actual).isEqualTo("Customer{name='LK', email='LK@d', birthDate=2020-02-14, phoneNumber=123456, address='AStreet'}");
    }
}
        */
