package ThreadStu;

/**
 * @author 伍六七
 * @date 2022/11/20 21:52
 */
public class _3ThreadCreateAndGo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            for(int i = 0; i < 50; i ++) {
                System.out.println("t1"+i);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 50; i ++){
                System.out.println("t2"+i);
            }
        });
        t1.start();
        t2.start();
    }
}
