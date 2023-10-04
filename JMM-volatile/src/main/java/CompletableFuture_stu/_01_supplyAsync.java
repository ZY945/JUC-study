package CompletableFuture_stu;

/**
 * @author 伍六七
 * @date 2022/11/30 11:14
 */

import java.util.concurrent.CompletableFuture;

/**
 * 盲僧r闪，小兵在a他，这个过程
 */
public class _01_supplyAsync {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("按下R健");
        SmallTool.printTimeAndThread("按下D闪健");

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("R抬脚了");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("闪现了");
            SmallTool.sleepMillis(100);
            return "盲僧R闪了";
        });
        SmallTool.printTimeAndThread("小兵在打盲僧");
        SmallTool.printTimeAndThread(String.format("%s,ADC被踢回来了",cf.join()));

    }
}
