package org.example;

public interface Player {
    int bet();

    void debit(int bet) throws InsufficientFundsException;

    void credit(int amount);
    boolean isPlayerSolvent(int bet);
}
