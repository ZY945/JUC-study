package wuliuqi.suo;

import java.lang.reflect.Field;

/**
 * @author 伍六七
 * @date 2022/8/1 20:32
 */
class STest2 {

    private final Object object = new Object();

    //引用锁
    public void print() {
        synchronized (object) {
            System.out.println("xxxx");
        }
    }
}

public class yinyongsuo {
    public static void main(String[] args) throws InterruptedException {

        STest2 sTest = new STest2();

        // Thread 1
        Thread t1 = new Thread(() -> {
            sTest.print();
        });

        // Thread 2
        Thread t2 = new Thread(() -> {

            try {
                synchronized (sTest) {//
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
