package JMM;

/**
 * @author 伍六七
 * @date 2023/7/29 16:47
 */
public class JMMProblem {
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();
        student.start();
        while (true) {
            synchronized (student){
                if (student.isLeave()) {
                    System.out.println("检测到学生离开,自动锁门");
                    break;
                }
            }
        }

    }
}
