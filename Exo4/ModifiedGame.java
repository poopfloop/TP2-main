package org.example;

public interface ModifiedGame {
    public void play(Player player, Dice dice1, Dice dice2) throws ClosedGameException;
    public void close();
    public boolean isOpen();
}
