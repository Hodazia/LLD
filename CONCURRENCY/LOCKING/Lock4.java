import java.util.concurrent.locks.StampedLock;

public class Lock4 {
    
}

class SharedResourceStamp {
    int a = 10;
    StampedLock lock = new StampedLock();

    public void producer()
    {
        long stamp = lock.tryOptimisticRead();
        // 
        try{
            System.out.println("taken optimistic Loc");
            a = 11;
            Thread.sleep(4000);
            if(lock.validate(stamp))
            {
                // whatever stamp we got during read, it tells if any write operation has done or not
                System.out.println("updated a value successfully ");
            }
            else {
                System.out.println("rollback of work ");
                a=10;
            }
        }
        catch(Exception e) {

        }
    }

    public void consumer()
    {
        long stamp = lock.writeLock();
        System.out.println("write lock acquired by " + Thread.currentThread().getName());
    try{
        System.out.println("performing work");
        a = 9;;
    }
    finally
    {
        lock.unlockWrite(stamp);
        System.out.println("Write released by " + Thread.currentThread().getName());
    }
    }
}