import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String ,Object> map=new HashMap<>();
    private ReadWriteLock rw=new ReentrantReadWriteLock();
    public void put(String key,String value){
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"要准备开始写"+key+"+++++++++++");
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.writeLock().unlock();
        }
    }
    public void read(String key){
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"要准备开始读"+key+"------------");
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName()+"读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.readLock().unlock();
        }
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyCache cache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            int num=i;
            new Thread(()->{
               cache.put(String.valueOf(num),String.valueOf(num));
            },String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(3);
        for (int i = 1; i <=5 ; i++) {
            int num=i;
            new Thread(()->{
                cache.read(String.valueOf(num));
            },String.valueOf(i)).start();
        }



    }

}
