package entities;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.time.LocalDate;

public class UserTest {
    private User u = new User("Jonas", "Jebkovič", LocalDate.of(2000, 1,1), "jon.jebkovič@gmail.com", "+421 933 222 222", "my Address");

    @Test
    public void getFirstNameTest(){
        assertThat(u.getFirstName()).isEqualTo("Jonas");
    }

    @Test
    public void getLastNameTest(){
        assertThat(u.getLastName()).isEqualTo("Jebkovič");
    }

    @Test
    public void setLastNameTest(){
        u.setLastName("Abcd");
        assertThat(u.getLastName()).isEqualTo("Abcd");
    }

    @Test
    public void getBirthDateTest(){
        assertThat(u.getBirthDate()).isEqualTo(LocalDate.of(2000, 1,1));
    }

    @Test
    public void getEmailTest(){
        assertThat(u.getEmail()).isEqualTo("jon.jebkovič@gmail.com");
    }

    @Test
    public void setEmailTest(){
        u.setEmail("aaa@aa.aa");
        assertThat(u.getEmail()).isEqualTo("aaa@aa.aa");
    }

    @Test
    public void getPhoneNumberTest(){
        assertThat(u.getPhoneNumber()).isEqualTo("+421 933 222 222");
    }

    @Test
    public void setPhoneNumberTest(){
        u.setPhoneNumber("+420 933 222 222");
        assertThat(u.getPhoneNumber()).isEqualTo("+420 933 222 222");
    }

    @Test
    public void getAddressTest(){
        assertThat(u.getAddress()).isEqualTo("my Address");
    }

    @Test
    public void setAddressTest(){
        u.setAddress("my New Address");
        assertThat(u.getAddress()).isEqualTo("my New Address");
    }







}