package DeepCopyExample;

import DeepCopyExample.Models.Car;
import DeepCopyExample.Models.Engine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeepCopySample {

    private final DeepCopyMode mode;
    private final Car car;
    private Car carCopied;

    private void init() throws JsonProcessingException {
        switch (mode) {
            case  DEEP_COPY -> carCopied=car.clone();
            case SHALLOW_COPY -> carCopied=new Car(car);
            case DEEP_COPY_WITH_JACKSON-> deepCopyWithJackson();
            default -> throw new RuntimeException("Invalid mode " + mode);
        }
    }

    private void deepCopyWithJackson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        carCopied = mapper.readValue(mapper.writeValueAsString(car), Car.class);
    }

    public DeepCopySample(DeepCopyMode mode) {
        this.car=new Car("Dodge",1973,"black",
                new Engine("1111",1973,200));
        this.mode=mode;
    }

    public static void main(String[] args) throws JsonProcessingException {
        DeepCopySample instance=new DeepCopySample(DeepCopyMode.DEEP_COPY);
        instance.init();
        System.out.println("Car before update :" +instance.car.toString());
        System.out.println("CopiedCar before update :" +instance.carCopied.toString());
        instance.car.setColor("Blue");
        instance.car.setModel("Toyota");
        instance.car.setYearCreated(2001);
        instance.car.getEngine().setSerialNumber("2222");
        instance.car.getEngine().setHorsePower(350);
        instance.car.getEngine().setYearCreated(2001);
        System.out.println("Car after update :" +instance.car.toString());
        System.out.println("CopiedCar after update :" +instance.carCopied.toString());
    }

    enum DeepCopyMode{
        DEEP_COPY,
        SHALLOW_COPY,
        DEEP_COPY_WITH_JACKSON
    }
}
