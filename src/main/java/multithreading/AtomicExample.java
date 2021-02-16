package multithreading;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample {
    static final AtomicInteger i = new AtomicInteger();
    public static void main(String[] args) {


        Runnable runnable = () -> {
            while (true) {
                task();


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

    }

    public static void task(){
        int iGet = i.incrementAndGet();
        System.out.println("i = " + iGet);
    }
}
