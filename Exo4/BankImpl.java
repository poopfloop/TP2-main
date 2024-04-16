package org.example;

public class BankImpl implements Bank {

    private int balance;
    private int threshold;

    public BankImpl(int balance, int threshold) {
        this.balance = balance;
        this.threshold = threshold;
    }

    @Override
    public boolean isSolvent() {
        return balance >= threshold;
    }

    @Override
    public void credit(int amount) {
        this.balance += amount;
    }

    @Override
    public void debit(int amount) {
        this.balance -= amount;
    }
}
