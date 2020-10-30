import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"callllll..");
        return 111;
    }
}
public class CallDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new MyThread2(),"AA").start();不能直接替换
        FutureTask<Integer> ft1 = new FutureTask<Integer>(new MyThread2());
        FutureTask<Integer> ft2 = new FutureTask<Integer>(()->{
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"calllldf");
            return 222;
        });

        new Thread(ft1,"zhangsan").start();
        new Thread(ft2,"lisi").start();
        System.out.println(Thread.currentThread().getName());

        System.out.println(ft1.get());
        System.out.println(ft2.get());

    }
}
