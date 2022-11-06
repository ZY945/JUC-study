package wuliuqi.suo;

import java.lang.reflect.Field;

/**
 * @author 伍六七
 * @date 2022/8/1 20:29
 */
class STest1 {

    private final Object object = new Object();

    //实例锁
    public void print() {
        synchronized (STest.class) {
            System.out.println("xxxx");
        }
    }
}

public class shilisuo {

    public static void main(String[] args) throws InterruptedException {

        STest1 sTest = new STest1();

        // Thread 1
        Thread t1 = new Thread(() -> {
            sTest.print();
        });

        // Thread 2
        Thread t2 = new Thread(() -> {

            try {
                synchronized (sTest) {
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
