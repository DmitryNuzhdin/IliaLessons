package multithreading;

public class ThreadExample {
    static volatile int i = 0;
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
        synchronized (ThreadExample.class) {
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
