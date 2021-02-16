package multithreading;

import java.util.concurrent.*;

public class ExecutorExample {
    static volatile int i = 0;
    static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {


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

        Callable<String> callable = () -> {
            Thread.sleep(10000);
            return "DONE";
        };



        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);

        Future<String> future = executor.submit(callable);

        while (!future.isDone()) {}

        System.out.println(future.get());



//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();

    }

    public static void task(){
        synchronized (ExecutorExample.class) {
            i++;
            System.out.println("i = " + i);

//            try {
//                ThreadExample.class.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            ThreadExample.class.notify();
        }
    }
}
