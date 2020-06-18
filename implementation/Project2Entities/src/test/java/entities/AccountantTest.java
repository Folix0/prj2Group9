package entities;

import static org.assertj.core.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class AccountantTest {
    private Accountant a = new Accountant(1, "Jonas", "Jebkovič", "jon.jebkovič@gmail.com", LocalDate.of(2000, 1,1), "+421 933 222 222", "my Address");

    @Test
    public void getIdTest(){
        assertThat(a.getId()).isEqualTo(1);
    }

    @Test
    public void testToString()
    {
        String expected = "Accountant{" +
                "id=" + a.getId() +
                "} " +
                "User{" +
                "firstName='" + a.getFirstName() + "'" +
                ", lastName='" + a.getLastName() + "'" +
                ", birthDate=" + a.getBirthDate() +
                ", email='" + a.getEmail() + "'" +
                ", phoneNumber='" + a.getPhoneNumber() + "'" +
                ", address='" + a.getAddress() + "'" +
                '}';
        Assert.assertEquals(expected, a.toString());
    }



}