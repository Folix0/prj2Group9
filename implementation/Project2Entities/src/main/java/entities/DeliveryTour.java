package entities;

import java.time.LocalDate;

public class DeliveryTour {

    private final int id;
    private LocalDate startOfDeliveryDate;
    private LocalDate finishOfDeliveryDate;

    public DeliveryTour(int id, LocalDate startOfDeliveryDate, LocalDate finishOfDeliveryDate) {
        this.id = id;
        this.startOfDeliveryDate = startOfDeliveryDate;
        this.finishOfDeliveryDate = finishOfDeliveryDate;
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartOfDeliveryDate() {
        return startOfDeliveryDate;
    }

    public void setStartOfDeliveryDate(LocalDate startOfDeliveryDate) {
        this.startOfDeliveryDate = startOfDeliveryDate;
    }

    public LocalDate getFinishOfDeliveryDate() {
        return finishOfDeliveryDate;
    }

    public void setFinishOfDeliveryDate(LocalDate finishOfDeliveryDate) {
        this.finishOfDeliveryDate = finishOfDeliveryDate;
    }

    @Override
    public String toString() {
        return "DeliveryTour{" +
                "id=" + id +
                ", startOfDeliveryDate=" + startOfDeliveryDate +
                ", finishOfDeliveryDate=" + finishOfDeliveryDate +
                '}';
    }
}
