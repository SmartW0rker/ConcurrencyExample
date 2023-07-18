import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

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
}