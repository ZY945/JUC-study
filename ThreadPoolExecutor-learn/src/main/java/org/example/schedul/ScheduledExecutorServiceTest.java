package org.example.schedul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 伍六七
 * @date 2023/9/5 18:16
 * 在任务里try-catch包裹,捕获异常
 * 否则,例如数据库突然断了,导致抛出异常,没有及时捕获,会导致阻塞,任务不在定时执行
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        System.out.println("启动app");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        //scheduleAtFixedRate
        // 参数 (任务,延时时间,定时,时间单位)
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 启动时每隔60秒执行一次数据库的刷新
                // 将数据缓存至本地
                System.out.println("开始执行");
                List<String> list = queryAndSave();
                System.out.println("执行一次成功");
            }
        }, 3, 3, TimeUnit.SECONDS);
    }


    public static List<String> queryAndSave() {
        System.out.println("开始查询数据");
        // 数据库突然挂了！
        // throw new RuntimeException();
        // 应该使用try
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>();
    }
}
