What is Synchronization(同步)

- Synchronization is ensuring that only one thread can access a shared resource (like a variable, object, or method) at a time. 
- It prevents concurrent(併發) threads from interfering(干擾) with each other while modifying shared data.

Why is Synchronization Needed?
- Prevents Data Inconsistency(不一致): Ensures that multiple threads don’t corrupt(損壞) shared data when accessing it simultaneously(同時).
- Avoids Race Conditions: Allows only one thread to execute a critical section at a time, maintaining predictable results.
- Maintains Thread Safety: Protects shared resources from concurrent modification by multiple threads.
- Ensures Data Integrity(完整性): Keeps shared data accurate and consistent throughout program execution.

Methods of Synchronization in Java

- start() : Creates a new thread and starts its execution.(Into runnable state)
  - Creating a thread and starting a thread are two different stages.
- run() : Contains the code that constitutes the new thread's task.(只是一般的Method call)
- sleep() : Pauses the execution of the current thread for a specified time.
- join() : Waits for a thread to die.
- wait() : Causes the current thread to wait until another thread invokes notify() or notifyAll() on the same object.
- notify() : Wakes up a single thread that is waiting on the object's monitor.
- notifyAll() : Wakes up all threads that are waiting on the object's monitor.



Flowchart of Synchronization

There are three ways to achieve synchronization in Java
- Synchronized Methods
- Synchronized Blocks
- Static Synchronization

-----------------------------------
Synchronized Methods Example

```
In practice code, we have two threads (t1 and t2) trying to increment a shared counter variable.

main thread
│
│ new Counter
│
│ new Thread t1  (還沒跑)
│ new Thread t2  (還沒跑)
│
│ t1.start() ───────▶ t1 thread 開始跑
│ t2.start() ───────▶ t2 thread 開始跑
│
│ t1.join()  (等 t1)
│ t2.join()  (等 t2)
│
│ System.out.println
│
└── 程式結束
```
```
package Base.Synchronization;

public class SynchronizationPractice {

    public static void main(String[] args) {

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

        t1.start(); // JVM request OS start a new thread -> Thread status into runnable
        t2.start();

        try {
            t1.join(); // Wait for t1 thread to finish
            t2.join(); // Wait for t2 thread to finish
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // If t1 and t2 haven't finished yet, main thread will not execute this line
        System.out.println("After join : " + counter.getCount());

    }
}

class Counter {

    // Shared counter variable
    private int count = 0;

    // Synchronized method to increment the counter
    public synchronized void increment() {
        count++;
    }

    // Synchronized method to get the current count
    public synchronized int getCount() {
        return count;
    }
}
```
-----------------------------------

Synchronize Block Example

- The purpose of Synchronized block is to lock only "shared resources (or program sections) that actually need mutual exclusion", so as to prevent method-level synchronized from locking logic that does notod-level. parallelism and performance.
- Specifically, when a thread enters a synchronized block, it acquires a lock on the specified object (in this case, 'this', which refers to the current instance of the Counter class). 
  - This means that no other thread can enter any synchronized block or method that locks on the same object until the first thread exits the block and releases the lock.
- Synchronized block gives you a chance to shrink the lock, But if you write a big block, the performance is just as poor.

```
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
```

-----------------------------------
All of references

Reference 
- https://medium.com/java-magazine-translation/java-中的同步-part-2-synchronized-關鍵字-53db29839989 => TODO
- https://www.geeksforgeeks.org/java/synchronization-in-java/