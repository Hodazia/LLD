import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
since th1,th2 have put a S lock on a resource, 
so th3 wanting to put a X lock will have to wait unitl th1,th2 release the S lock

*/
public class Lock3 {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread th1 = new Thread(() -> resource.producer(lock));
        Thread th2 = new Thread(() -> resource.producer(lock));

        SharedResource resource2 = new SharedResource();
        Thread th3 = new Thread(()-> resource2.consumer(lock));

        th1.start();
        th2.start();
        th3.start();
    }
}

/*
OUTPUT

Read Lock acquired by Thread-0
Read Lock acquired by Thread-1
Lock released by Thread-1
Lock released by Thread-0
Write Lock acquired by Thread-2
Write released by Thread-2
*/