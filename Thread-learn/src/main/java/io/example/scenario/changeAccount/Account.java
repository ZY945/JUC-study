package io.example.scenario.changeAccount;

import com.sun.istack.internal.Nullable;

import java.math.BigDecimal;

public class Account {
    private String accountId;
    //BigDecimal每次操作都是生成一个新对象,需要重新赋值
    @Nullable private BigDecimal money;


    public synchronized void drawMoney(@Nullable BigDecimal needMoney){
        String name = Thread.currentThread().getName();
            if(needMoney.compareTo(this.money)>-1){
                System.out.println(name+"需要的金额过大,余额不足");
            }else if(needMoney.compareTo(this.money)<1){
                System.out.println("余额充裕,可以转账");
                this.money = this.money.subtract(needMoney);
                System.out.println(name+"成功取走"+needMoney);
            }
    }

    public Account() {
    }

    public Account(String accountId, BigDecimal money) {
        this.accountId = accountId;
        this.money = money;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
