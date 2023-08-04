package wuliuqi.suo;

import java.lang.reflect.Field;

/**
 * @author 伍六七
 * @date 2022/8/1 20:27
 */
class STest {

    //类锁方式1
    public void print() {
        synchronized (STest.class) {
            System.out.println("xxxx");
        }
    }
    //类锁方式2
//    public synchronized static void print(){
//        System.out.println("xxxx");
//    }
}

public class leisuo {

    public static void main(String[] args) throws InterruptedException {

        STest sTest = new STest();

        // Thread 1
        Thread t1 = new Thread(() -> {
            sTest.print();
        });

        // Thread 2
        Thread t2 = new Thread(() -> {

            try {
                synchronized (STest.class) {
                    while (true) ;
                }
            } catch (Exception e) {
                System.out.println("Exception=" + e.getMessage());
            }

        });

        t2.start();
        Thread.sleep(1000);
        t1.start();
    }

    public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(instance);
    }
}
