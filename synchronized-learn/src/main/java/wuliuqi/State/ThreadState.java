package wuliuqi.State;

/**
 * @author 伍六七
 * @date 2022/8/1 15:30
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        System.out.println(t.getName() + "状态" + t.getState());
        t.start();
        while (Thread.State.TERMINATED != t.getState()) {
            System.out.println(t.getName() + "状态" + t.getState());
            Thread.sleep(500);
        }
        System.out.println(t.getName() + "状态" + t.getState());
    }
}

class T extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("执行次数" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
