import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class DeadDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+"已经有A锁了还想抢b锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "抢到b锁");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"已经有B锁了还想抢a锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "抢到a锁");
                }
            }
        },"B").start();

    }
}
