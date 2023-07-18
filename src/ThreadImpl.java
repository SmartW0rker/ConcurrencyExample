import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class ThreadImpl implements Runnable{

    private final String name;

    private final List<Integer> numbers;

    public ThreadImpl(String name, List<Integer> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try {

            System.out.println(name + "starting...");

            Random r=new Random();

            Thread.sleep(2000);

//            synchronized (numbers) {

                numbers.add(r.nextInt(1000));
//            }

            System.out.println(name + "finished...");

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }






    }
}
