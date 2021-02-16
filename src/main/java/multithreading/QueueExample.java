package multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueExample {
    static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
    static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {


        Runnable runnable = () -> {
            while (true) {
                try {
                    blockingQueue.put(1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

        Runnable worker = () -> {
            int i = 0;
            while (true){
                try {
                    int iFromQueue = blockingQueue.take();
                    Thread.sleep(10000);

                    i = i + iFromQueue;
                    System.out.println("i = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.execute(worker);

    }

}
