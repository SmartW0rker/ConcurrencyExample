import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        copyListExample();

//        List<Integer> numbers= new LinkedList<>(List.of(1, 5, 7, 10));
        List<Integer> numbers= new CopyOnWriteArrayList<>(List.of(1,5,7,10));

        for (int i=0;i<10000;i++){
            ThreadImpl t=new ThreadImpl("Thread: "+i,numbers);
            Thread thread=new Thread(t);
            thread.start();
        }

//        synchronized (numbers) {
            for (Integer number : numbers) {
                System.out.println("Number: "+number);
                Thread.sleep(2000);
 //           }
        }
    }

    private static void copyListExample() {

        copyListExampleWithArrayList();

        copyListExampleWithCopyOnWriteArrayList();
    }

    private static void copyListExampleWithArrayList() {
        List<Person> people=new ArrayList<>(
                List.of(
                        new Person("Hristijan",24),
                        new Person("Darko",21),
                        new Person("Bojana",23))
        );
        System.out.println("Test ArrayList...");
        List<Person> peopleCopy=new ArrayList<>(people);
        testObjectReferences(people,peopleCopy);
    }

    private static void copyListExampleWithCopyOnWriteArrayList() {

        List<Person> people=new CopyOnWriteArrayList<>(
                List.of(
                        new Person("Hristijan",24),
                        new Person("Darko",21),
                        new Person("Bojana",23))
        );

        System.out.println("Test CopyOnWriteArrayList...");
        List<Person> peopleCopy=new CopyOnWriteArrayList<>(people);
        testObjectReferences(people,peopleCopy);
    }

    private static void testObjectReferences(List<Person> people,List<Person> peopleCopy) {

        if (people.get(0)==peopleCopy.get(0))
            System.out.println("Same Person object");
        else
            System.out.println("Different Person object");

        people.get(0).setName("Hristijan Ch");

        System.out.println(peopleCopy.get(0).getName());
    }
}