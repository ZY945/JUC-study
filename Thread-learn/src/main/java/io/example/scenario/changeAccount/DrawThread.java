package io.example.scenario.changeAccount;

import java.math.BigDecimal;

public class DrawThread extends Thread{

    private final Account account;
    public DrawThread(Account account,String name){
        super(name);
        this.account=account;
    }
    @Override
    public void run() {
         //取钱
        account.drawMoney(new BigDecimal(20));
    }
}
