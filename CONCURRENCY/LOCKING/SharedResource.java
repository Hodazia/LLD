/* implement ReadWriteLock


*/

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    boolean isAvailable = false;


    public void producer(ReadWriteLock lock)
    {
        // passing a lock as parameter
        try{
            lock.readLock().lock();
            System.out.println("Read Lock acquired by " + Thread.currentThread().getName());
            //isAvailable = true;
            Thread.sleep(4000);
        }
        catch(Exception e) {

        }
        finally
        {
            lock.readLock().unlock();
            System.out.println("Lock released by " + Thread.currentThread().getName());
        }
    }

    public void consumer(ReadWriteLock lock)
    {
    // passing a lock as parameter
    try{
        lock.writeLock().lock();
        System.out.println("Write Lock acquired by " + Thread.currentThread().getName());
        isAvailable = true;
        Thread.sleep(4000);
    }
    catch(Exception e) {

    }
    finally
    {
        lock.writeLock().unlock();
        System.out.println("Write released by " + Thread.currentThread().getName());
    }
    }
}
