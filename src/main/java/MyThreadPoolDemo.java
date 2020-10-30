import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.DiscardPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
                // new ThreadPoolExecutor.AbortPolicy()

        );
        try {
            for (int i = 1; i <=11 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()
                            +"\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
    private static void test(){
        //一个银行网点5个受理业务的窗口
        ExecutorService t1 = Executors.newFixedThreadPool(5);
        ExecutorService t2 = Executors.newSingleThreadExecutor();
        ExecutorService t3 =Executors.newCachedThreadPool();

        //10个顾客
        try{
            for (int i = 1; i <=30 ; i++) {
                t3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            t3.shutdown();
        }

    }


}
