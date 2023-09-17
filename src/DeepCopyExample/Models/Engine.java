package DeepCopyExample.Models;

public class Engine implements Cloneable {
    private String serialNumber;
    private int yearCreated;
    private int horsePower;

    public Engine(String serialNumber, int yearCreated, int horsePower) {
        this.serialNumber = serialNumber;
        this.yearCreated = yearCreated;
        this.horsePower = horsePower;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public Engine clone() {
        try {
            Engine clone = (Engine) super.clone();
            clone.setHorsePower(horsePower);
            clone.setSerialNumber(serialNumber);
            clone.setYearCreated(yearCreated);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Engine{" +
                "serialNumber='" + serialNumber + '\'' +
                ", yearCreated=" + yearCreated +
                ", horsePower=" + horsePower +
                '}';
    }
}
