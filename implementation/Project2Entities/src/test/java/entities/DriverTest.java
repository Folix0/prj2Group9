package entities;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class DriverTest {
    private Driver driver = new Driver(1, true, true, "Mino", "Laskovic", LocalDate.of(2000,1,1), "laskovi@gmail.com", "+420 775 755 655", "address");

    @Test
    public void getIdTest(){
        assertThat(driver.getId()).isEqualTo(1);
    }

    @Test
    public void getIsAvailableTest(){
        assertThat(driver.isAvailable()).isTrue();
    }

    @Test
    public void setIsAvailableTest(){
        driver.setAvailable(false);
        assertThat(driver.isAvailable()).isFalse();
    }

    @Test
    public void getHasHazardousLicenseTest(){
        assertThat(driver.isHasHazardousLicense()).isTrue();
    }

    @Test
    public void setHasHazardousLicenseTest(){
        driver.setHasHazardousLicense(false);
        assertThat(driver.isHasHazardousLicense()).isFalse();
    }

    @Test
    public void getFirstNameTest(){
        assertThat(driver.getFirstName()).isEqualTo("Mino");
    }

    @Test
    public void getLastNameTest(){
        assertThat(driver.getLastName()).isEqualTo("Laskovic");
    }

    @Test
    public void setLastNameTest(){
        driver.setLastName("Smith");
        assertThat(driver.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void getBirthDateTest(){
        assertThat(driver.getBirthDate()).isEqualTo(LocalDate.of(2000,1,1));
    }

    @Test
    public void getEmailTest(){
        assertThat(driver.getEmail()).isEqualTo("laskovi@gmail.com");
    }

    @Test
    public void setEmailTest(){
        driver.setEmail("laskov@gmail.com");
        assertThat(driver.getEmail()).isEqualTo("laskov@gmail.com");
    }

    @Test
    public void getPhoneNumberTest(){
        assertThat(driver.getPhoneNumber()).isEqualTo("+420 775 755 655");
    }

    @Test
    public void setPhoneNumberTest(){
        driver.setPhoneNumber("+420 774 255 555");
        assertThat(driver.getPhoneNumber()).isEqualTo("+420 774 255 555");
    }

    @Test
    public void getAddressTest(){
        assertThat(driver.getAddress()).isEqualTo("address");
    }

    @Test
    public void setAddressTest(){
        driver.setAddress("address2");
        assertThat(driver.getAddress()).isEqualTo("address2");
    }
        @Test
        public void testToString()
        {
            String expected = "Driver{" +
                    "id=" + driver.getId() +
                    ", isAvailable=" + driver.isAvailable() +
                    ", hasHazardousLicense=" + driver.isHasHazardousLicense() +
                    "} " +
            "User{" +
                    "firstName='" + driver.getFirstName() + "'" +
                    ", lastName='" + driver.getLastName() + "'" +
                    ", birthDate=" + driver.getBirthDate() +
                    ", email='" + driver.getEmail() + "'" +
                    ", phoneNumber='" + driver.getPhoneNumber() + "'" +
                    ", address='" + driver.getAddress() + "'" +
                    '}';
            Assert.assertEquals(expected, driver.toString());
        }
}