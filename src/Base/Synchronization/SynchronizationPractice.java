package Base.Synchronization;

public class SynchronizationPractice {

    public static void main(String[] args) throws InterruptedException {

        // Shared resource
        Counter counter = new Counter();


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        // JVM request OS start a new thread -> Thread status into runnable
        t1.start();
        t2.start();

        // Wait for t1 and t2 thread to finish
        t1.join();
        t2.join();

        // If t1 and t2 haven't finished yet, main thread will not execute this line
        System.out.println("After join : " + counter.getCount());

    }
}

class Counter {

    // Shared counter variable
    private int count = 0;

    // Use Synchronized block to ensure only one thread can access this method at a time
    // Synchronize only this block
    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}