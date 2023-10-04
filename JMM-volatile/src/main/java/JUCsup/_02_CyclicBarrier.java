package JUCsup;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 伍六七
 * @date 2022/12/3 11:36
 */
public class _02_CyclicBarrier {

    private static final int num = 12;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            System.out.println("集齐十二张卡片合成红包");
        });

        for(int i = 0; i < 12; i ++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"已获得");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
//class test{
//    public static void main(String[] args) {
//
//    }
//    public static int test(){
//        Scanner scanner = new Scanner(System.in);
//        while (true){
//            System.out.println("输入第一个数");
//            int i = scanner.nextInt();
//            System.out.println("输入运算符");
//            String next = scanner.next();
//            System.out.println("输入第二个数");
//            int i1 = scanner.nextInt();
//            System.out.println("输入你的答案");
//            int i2 = scanner.nextInt();
//            if(calculate(i1,i2,next)==i2){
//                System.out.println("答对了");
//            }else{
//                System.out.println("答错了");
//            }
//        }
//    }
//    public static int calculate(int a, int b,String operator){
//        int x = 0;
//        if(operator.equals("+")){
//            return a+b;
//        }else if(operator.equals("-")){
//            return a-b;
//        }else if(operator.equals("*")){
//            return a*b;
//        }else if(operator.equals("/")){
//            if(b==0){
//                System.out.println("除数不能为0");
//                return 0;
//            }
//            return a/b;
//        }
//        return x;
//    }
//}