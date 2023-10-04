package JMM;

/**
 * @author 伍六七
 * @date 2023/7/29 16:53
 */
public class Student extends Thread{
    private volatile boolean leave = false;

    public boolean isLeave() {
        return leave;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leave = true;
        System.out.println("学生走了");
    }
}
