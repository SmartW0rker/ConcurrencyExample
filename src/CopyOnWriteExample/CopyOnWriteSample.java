package CopyOnWriteExample;

import Models.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteSample {
    private final List<Person> people;



    public CopyOnWriteSample(CopyOnWriteExampleMode mode) {
        switch (mode) {
            case COPY_ON_WRITE -> people = new CopyOnWriteArrayList<>();
            case ARRAY_LIST -> people = new ArrayList<>();
            case LINKED_LIST -> people = new LinkedList<>();
            default -> throw new RuntimeException("Invalid mode " + mode);
        }
    }

    public void init() {
        people.add(new Person("Hristijan", 24));
        people.add(new Person("Darko", 21));
        people.add(new Person("Bojana", 23));
    }

    public void iterateListAndAdd(Person newPerson) {
        boolean firstTime = true;
        for (Person person : people) {
            System.out.println(person.getName());
            if (firstTime) {
                people.add(0, newPerson);
                firstTime = false;
            }
        }
    }



    public static void main(String[] args) {
        CopyOnWriteSample cows = new CopyOnWriteSample(CopyOnWriteExampleMode.LINKED_LIST);
        cows.init();
        System.out.println("\n1. Before iterateListAndAdd");
        cows.iterateListAndAdd(new Person("Ivan", 25));
        System.out.println("\n2. Before iterateListAndAdd");
        cows.iterateListAndAdd(new Person("Petar", 26));
        System.out.println("\n3. Before iterateListAndAdd");
        cows.iterateListAndAdd(new Person("Viktor", 26));
    }

    enum CopyOnWriteExampleMode {
        COPY_ON_WRITE,
        ARRAY_LIST,
        LINKED_LIST
    }


}
