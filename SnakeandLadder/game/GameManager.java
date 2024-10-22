package game;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class GameManager {
    private static GameManager instGameManager;
    
    // Private constructor for Singleton pattern
    private GameManager() {
    }
    private static Lock mtx=new ReentrantLock();
    public static GameManager getGameManager() {
        if (instGameManager == null) {
            mtx.lock();
            if(instGameManager==null)
            {instGameManager = new GameManager();}
            mtx.unlock();
        }
        return instGameManager;
    }
    
    Board board = Board.getBoard();
    Dice dice = new Dice();

    public void start(String level, List<Player> players, int totalPlayers) {
        // Use equals() for string comparison
        if (level.equals("easy")) {
            board.createBoard(DiffLevel.EASY);
        } else if (level.equals("medium")) {
            board.createBoard(DiffLevel.MEDIUM);
        } else {
            board.createBoard(DiffLevel.HARD);
        }
         
        while (true) { 
            for (Player player : players) {
              
                System.out.println("It's " + player.getName() + " Turn. Type 'roll' to roll the dice.");
                Scanner scan = new Scanner(System.in);
                String roll = scan.next();

                // Use equals() for string comparison
                if (roll.equals("roll")) {
                    int rolledValue = dice.rollDice(); // Avoid variable name conflict
                    int oldPos=player.getPosition();
                    int newPos = player.getPosition() + rolledValue;
                    newPos = board.checkLadder(newPos); // Update newPos after checking ladders
                    newPos = board.checkSnake(newPos);   // Update newPos after checking snakes
                    player.setPosition(newPos); // Set the player's new position
                    System.out.println(player.getName() + " got a " + rolledValue + " and it's previous value is "+oldPos);
                    System.out.println(player.getName() + " new position is " + player.getPosition());
                    board.displayBoard(player.getName(),player.getPosition(),oldPos);
                    if (player.getPosition() == 100) {
                        System.out.println("Game Over!");
                        System.out.println(player.getName() + " is the winner.");
                        return; // End the game loop if a player wins
                    }
                } else {
                    System.err.println("Invalid move. Please type 'roll' to roll the dice.");
                }
            }
        }
    }
}