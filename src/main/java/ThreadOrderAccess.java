import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int num=1;//a:1,b:2,c:3
    private Lock lock=new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    void print5(int total){

        lock.lock();
        try {
            //判断
            while (num!=1){
                c1.await();
            }
            //干活儿
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"/t"+total+"/t"+i);
            }
            //通知
            num=2;
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void print10(int total){
        lock.lock();
        try {
            while (num!=2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "/t"+total +"/t"+ i);
            }
            num=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    void print15(int total){
        lock.lock();
        try {
            while (num!=3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "/t"+total +"/t"+ i);
            }
            num=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareData sd = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sd.print5(i);
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sd.print10(i);
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                sd.print15(i);
            }
        },"CC").start();
    }
}
