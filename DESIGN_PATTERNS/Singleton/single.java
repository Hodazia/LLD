package Singleton;

/*
its objective is to create only 1 and 1 object should be created

*/
public class single {
    public static void main(String[] args) {
        EagerInitialization eagerobj = EagerInitialization.getInstance();
    }
    
}


class EagerInitialization {
    /*
    as sson as we start the application,
    all the static variables are preloaded when the jVM loads, 
    even if i am not using it, it is getting created,
     */

    // created an object in advance , private so that nobody access it apart from this class
    // static it belongs to the class not to the object
    private static EagerInitialization obj = null;
    private EagerInitialization() {
        // private constructor, nobody is allowed to create new object using new keyword
        // apart from this class, nobody can do new EagerInitialization
    }

    public static  EagerInitialization getInstance()
    {
        return obj;
    }
}

class LazyInitialization {
    /*
    a performance optimization pattern in Java where the creation of an object,
     calculation of a value, or execution of an expensive process is delayed until the 
     first time it is needed. If the resource is never used during the application's lifecycle,
      it is never initialized, saving memory and CPU cycles



    what if 2 threads come in parallel, it won't be able to handle it
     */
    private static LazyInitialization conn;

    private LazyInitialization() {

    }

    public static LazyInitialization getInstance()
    {
        if(conn == null)
        {
            conn = new LazyInitialization();
        }
        return conn;
    }
}

class syncInitialization {
    /*
    design pattern uses the synchronized keyword to ensure that only one thread can 
    execute the instance creation method at a time, preventing multiple threads from
    creating separate instances in a multi-threaded environment,

• Lazy loading: The single instance is created only when requested for the first time.
• Thread safety: The synchronized keyword places a lock on the method or block, forcing 
concurrent threads to wait their turn.
• Prevention: It stops a race condition where two threads check instance == null at the 
exact same time and both instantiate the class.
    
    */
    private static syncInitialization obj; 
    private syncInitialization()
    {

    }

    synchronized public static syncInitialization getinstance()
    {
        // only 1 thread will be allowed to go inside,
        if(obj == null)
        {
            obj = new syncInitialization();
        }
        return obj;
    }
}


/*

Without declaring the instance variable as volatile, double-checked locking is broken and can cause intermittent crashes or bugs.
This happens because the operation instance = new Singleton(); is not atomic. The JVM/CPU executes it in three distinct step instructions:
1. Allocate memory for the object.
2. Construct the object (run the constructor).
3. Assign the memory address to the instance variable.

Due to compiler optimizations and instruction reordering, the JVM might rearrange the 
execution order to 1 ➔ 3 ➔ 2. If this reordering happens, Thread A might assign the 
memory address (Step 3) before the constructor finishes running (Step 2).

If Thread B calls getInstance() at that exact moment, it hits the first check, sees that 
instance is not null, and returns a reference to a partially initialized object. 
When Thread B tries to use that object, the application will break.

Declaring the variable volatile solves this by establishing a "happens-before" relationship,
 ensuring that memory writes are completely visible across threads and preventing the 
 compiler from reordering those object construction steps


*/
class DoubleCheckLock {
    /*
    • The First Check (if (instance == null)): Threads check if the instance is already
     created without acquiring a lock. If it is not null
      (which is true for 99.9% of the application's lifespan after initialization),
       threads bypass the synchronized block entirely and return the instance immediately.
• The Lock (synchronized): If the instance is null, threads compete to enter the 
synchronized block. Only one thread gains access at a time to handle instantiation.
• The Second Check (if (instance == null)): The winning thread checks again inside the lock. 
This is crucial because multiple threads could have passed the first check simultaneously 
before the lock was engaged. The second check ensures that subsequent threads waiting at the
 lock don't accidentally create a duplicate instance.
    
    
    
    */
    private static volatile DoubleCheckLock obj; 
    // volatile ??
    private DoubleCheckLock()
    {

    }

    public static DoubleCheckLock getinstance()
    {
        // 
        if(obj == null)
        {
            // put a lock here
            synchronized (DoubleCheckLock.class)
            {
                // check if it is null again
                if(obj == null)
                {
                    obj = new DoubleCheckLock();
                }
            }
        }
        return obj;
    }
}