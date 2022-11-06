package wuliuqi;

/**
 * @author 伍六七
 * @date 2022/8/1 11:43
 */
public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        System.out.println(i);
    }
}
