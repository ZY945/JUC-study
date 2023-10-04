package org.example.AtomicReference;

public class DebitCard {
    private final String name;
    private final int account;
    public DebitCard(String name, int account) {
        this.name = name;
        this.account = account;
    }
    public String getName() {
        return name;
    }
 
    public int getAccount() {
        return account;
    }
 
    @Override
    public String toString() {
        return "DebitCard {name=\""+name+"\"," +
                "account="+account+"}";
    }
}