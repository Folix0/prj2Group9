package entities;

public class Calculator {


    private double pricePKm;
    private boolean hazardousPercentage;
    private double kms;

    public Calculator(double pricePKm, boolean hazardousPercentage, double kms) {
        this.pricePKm = pricePKm;
        this.hazardousPercentage = hazardousPercentage;
        this.kms = kms;
    }

    public double getPricePKm() {
        return pricePKm;
    }

    public void setPricePKm(double pricePKm) {
        this.pricePKm = pricePKm;
    }

    public boolean getHazardousPercentage() {
        return hazardousPercentage;
    }

    public void setHazardousPercentage(boolean hazardousPercentage) {
        this.hazardousPercentage = hazardousPercentage;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "pricePKm=" + pricePKm +
                ", hazardousPercentage=" + hazardousPercentage +
                ", kms=" + kms +
                '}';
    }
}
