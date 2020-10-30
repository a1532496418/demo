
class Lock123 {
    private int num=0;//初始值为0

    public synchronized void add() throws InterruptedException {
        //1.判断
        while (num!=0){
            this.wait();
        }
        //2.干活
        num++;
        System.out.println(Thread.currentThread().getName()
                +"\t"+num);
        //3.通知
        this.notifyAll();
    }
    public synchronized void delete() throws InterruptedException {
        while (num!=1){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()
                +"\t"+num);
        this.notifyAll();
    }
}
public class LockDemo{
    public static void main(String[] args) {
        Lock123 sd = new Lock123();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sd.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sd.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sd.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sd.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();


    }
}
