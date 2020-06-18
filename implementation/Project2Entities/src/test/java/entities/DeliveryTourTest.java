package entities;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


public class DeliveryTourTest {

    DeliveryTour deliveryTour = new DeliveryTour(1,LocalDate.of(2020, 01, 24),
            LocalDate.of(2020, 01, 25));;

    @Test
    public void gettersTest(){
        assertThat(deliveryTour.getId()).isEqualTo(1);
        assertThat(deliveryTour.getStartOfDeliveryDate()).isEqualTo(LocalDate.of(2020,01,24));
        assertThat(deliveryTour.getFinishOfDeliveryDate()).isEqualTo(LocalDate.of(2020,01,25));
    }

    @Test
    public void settersTest() {
        deliveryTour.setStartOfDeliveryDate(LocalDate.of(2020, 01, 24));
        assertThat(deliveryTour.getStartOfDeliveryDate()).isEqualTo(LocalDate.of(2020, 01, 24));

        deliveryTour.setFinishOfDeliveryDate(LocalDate.of(2020, 01, 24));
        assertThat(deliveryTour.getFinishOfDeliveryDate()).isEqualTo(LocalDate.of(2020, 01, 24));
    }

    @Test
    public void testToString()
    {
        String expected = "DeliveryTour{" +
                "id=" + deliveryTour.getId() +
                ", startOfDeliveryDate=" + deliveryTour.getStartOfDeliveryDate() +
                ", finishOfDeliveryDate=" + deliveryTour.getFinishOfDeliveryDate() +
                '}';
        Assert.assertEquals(expected, deliveryTour.toString());
    }
}




