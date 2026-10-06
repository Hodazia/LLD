package SnakeLadder;

import java.util.LinkedList;
import java.util.Deque;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;

    public Game()
    {
        intiializeGame();
    }

    private void intiializeGame()
    {
        board = new Board(10,5,4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers()
    {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame()
    {
        while(winner==null)
        {
            Player playerTurn = findPlayer();
            System.out.println("player turn is: " + playerTurn.id + " current position is: " + playerTurn.currentposition);

            // roll the dice
            int diceNumbers = dice.rollDice();

            // get the new positon
            int playerNewPosition = playerTurn.currentposition + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition);
            playerTurn.currentposition = playerNewPosition;

            System.out.println(" player turn is : " + playerTurn.id + " new position is: " + playerNewPosition);
            if(playerNewPosition >= board.cells.length * board.cells.length - 1)
            {
                winner = playerTurn;
            }

        }
        System.out.println("Winner is " + winner.id);
    }

    private Player findPlayer()
    {
        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }

    private int jumpCheck(int playerNewPosition)
    {
        if(playerNewPosition > board.cells.length * board.cells.length - 1)
        {
            return playerNewPosition;
        }
        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition)
        {
            String jumpyBy = (cell.jump.start < cell.jump.end) ? "ladder": "Snake";
            System.out.println("jump done by: "+ jumpyBy);
            return cell.jump.end;
        }

        return playerNewPosition;
    }
}
