/*
implement Reentrantlock
bcz of this lock, it does not matter how many different objects u create,
only one thread will go inside the synchronized method,
*/

import java.util.concurrent.locks.ReentrantLock;

public class Lock2 {
    public static void main(String args[])
    {
        ReentrantLockResource resource1 = new ReentrantLockResource();
        ReentrantLockResource resource2 = new ReentrantLockResource();
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            resource1.producer(lock);
        });

        Thread t2 = new Thread(() -> {
            resource2.producer(lock);
        });

        t1.start();
        t2.start();

    }
}

class ReentrantLockResource {
    boolean isAvailable = false;


    public void producer(ReentrantLock lock)
    {
        // passing a lock as parameter
        try{
            lock.lock();
            System.out.println("Lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        }
        catch(Exception e) {

        }
        finally
        {
            lock.unlock();
            System.out.println("Lock released by " + Thread.currentThread().getName());
        }
    }
}