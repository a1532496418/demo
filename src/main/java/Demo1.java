import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo1 {

    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>();
//        for (int i = 0; i <300 ; i++) {
//            new Thread(()->{
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(list);
//            },"AA").start();
//        }
        //List list = new ArrayList();
        //Set<String> set = new HashSet<>();
        Map<String,String> map = new HashMap<>();
        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),Thread.currentThread().getName());
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
