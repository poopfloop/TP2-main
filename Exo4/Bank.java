package org.example;

public interface Bank {
    boolean isSolvent();
    void credit(int amount);
    void debit(int amount);
}
