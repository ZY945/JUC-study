package org.example.delay;

/**
 * @author 张洋
 * @description TODO
 * @date 2023-10-16 23:11
 */
public class DelaySave extends ContinuousDelayBaseService<Integer>{
    @Override
    protected void done(Integer value) {
        System.out.println("执行IO,值为:"+value);
    }
}
