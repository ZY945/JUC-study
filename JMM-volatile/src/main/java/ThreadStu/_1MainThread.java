package ThreadStu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 伍六七
 * @date 2022/11/20 20:59
 */
public class _1MainThread {
    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] ids = bean.getAllThreadIds();
        ThreadInfo[] infos = bean.getThreadInfo(ids);
        for(ThreadInfo info: infos){

            System.out.println(info.getThreadName());
        }
    }
}
