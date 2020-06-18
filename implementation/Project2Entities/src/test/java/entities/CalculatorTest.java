package entities;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
    private Calculator c = new Calculator(1, false, 100);

    @Mock
    List<String> mockedList;

    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {


    mockedList.add("one");
    Mockito.verify(mockedList).add("one");
    assertEquals(0, mockedList.size());

    Mockito.when(mockedList.size()).thenReturn(100);
    assertEquals(100, mockedList.size());
    }


    @Test
    public void getPriceTest(){
        assertThat(c.getPricePKm()).isEqualTo(1);
    }

    @Test
    public void setPriceTest(){
        c.setPricePKm(2);
        assertThat(c.getPricePKm()).isEqualTo(2);
    }

    @Test
    public void getHazardousTest(){
        assertThat(c.getHazardousPercentage()).isEqualTo(false);
    }

    @Test
    public void setHazardousTest(){
        c.setHazardousPercentage(true);
        assertThat(c.getHazardousPercentage()).isEqualTo(true);
    }

    @Test
    public void getKmTest(){
        assertThat(c.getKms()).isEqualTo(100);
    }

    @Test
    public void setKmTest(){
        c.setKms(50);
        assertThat(c.getKms()).isEqualTo(50);
    }

    @Test
    public void testToString()
    {
        String expected = "Calculator{" +
                "pricePKm=" + c.getPricePKm() +
                ", hazardousPercentage=" + c.getHazardousPercentage() +
                ", kms=" + c.getKms() +
                '}';
        assertEquals(expected, c.toString());
    }
}
