/**
 * @author 伍六七
 * @date 2022/10/24 16:40
 */
public class newThread {
    public static void main(String[] args) {
        //推荐Runnable()
        Runnable r = new Runnable() {

            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        // do your work
                        System.out.println("执行");
                        Thread.interrupted();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(r);
        thread.start();
    }
}
