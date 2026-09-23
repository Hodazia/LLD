/*
HOW TO IMPLEMENT RUNNABLE
1. Traditional Class Implementation
You create a class that implements Runnable, override the run() method, 
pass an instance of it to a Thread object, and call start()

2. Using Lambda Expressions (Recommended for simple tasks)
Because Runnable is a functional interface, you can bypass creating a separate named 
class and write clean, inline code using a lambda expression

what is the run method in Thread class
@Override
public void run()
{
    if(target!=null)
    {
        target.run();
    }
}
target is the Runnable object    

*/

public class Learn1 implements  Runnable {
    @Override 
    public void run()
    {
        System.out.println("Code executed by thread " + Thread.currentThread().getName());
    }
}

/*
thread subclass , 
- create a class that extends the Thread
- create an instance of the subclass and call the start method

*/
public class Learn2 extends Thread {
    @Override 
    public void run()
    {
        System.out.println("Code executed by thread in Learn2 " + Thread.currentThread().getName());
    }
}


public class learning1 {
    public static void main(String args[])
    {
        Learn1 obj = new Learn1();
		Thread thread = new Thred(obj); // takes a runnable object as a param
		//  at this time, the thread is created

        // via lambda 
        Thread thread2 = new Thread(() -> {
            System.out.println("Lambda thread running: " + Thread.currentThread().getName());
        });

        Learn2 lrn = new Learn2();
        lrn.start();
        
        thread2.start();
		
		// now when we do start, then internally it calls the run method of the thread
		// obj is the target ,  target is a Runnable object!!
		thread.start();
    }
}
