

public class ProducerConsumerMain {
    public static void main(String[] args) {
        Box box = new Box();
        Thread t1 = new Thread(() -> {
            try {
                for(int i=1;i<20;i++)
                {
                    box.produce(i);
                }
            }
            catch(Exception e)
            {

            }

        });

        Thread t2 = new Thread(() -> {
            try{
                for(int i=1;i<=20;i++)
                {
                    box.consume();
                }
            }
            catch(Exception e){}
        });

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();
    }    
}

/*
Producer consumer problem, 


*/
class Box {

    private Integer item;
    private boolean flag = false;

    synchronized void produce(int value)throws InterruptedException {

        while (flag) {
            wait();
        }

        item = value;
        flag = true;

        System.out.println("Producer produces " + item);

        notify();
    }

    synchronized void consume()
            throws InterruptedException {

        while (!flag) {
            wait();
        }

        System.out.println("Consumer consumes " + item);

        item = null;
        flag = false;

        notify();
    }
}