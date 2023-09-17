package DeepCopyExample.Models;

public class Car implements Cloneable
{
    private String model;
    private int yearCreated;
    private String color;
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Car(String model, int yearCreated, String color, Engine engine) {
        this.model = model;
        this.yearCreated = yearCreated;
        this.color = color;
        this.engine=engine;
    }

    public Car(Car car){
        this.model = car.model;
        this.yearCreated = car.yearCreated;
        this.color = car.color;
        this.engine=car.engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Car clone() {
        try {
            Car clone = (Car) super.clone();
            clone.setColor(color);
            clone.setModel(model);
            clone.setYearCreated(yearCreated);
            clone.setEngine(engine.clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", yearCreated=" + yearCreated +
                ", color='" + color + '\'' +
                ", engine=" + engine.toString() +
                '}';
    }
}
