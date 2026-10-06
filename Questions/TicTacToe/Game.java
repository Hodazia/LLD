package TicTacToe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import TicTacToe.Board;

public class Game {
    Deque<Player> players;
    Board gameBoard;

    Game()
    {
        initialize();
    }

    public void initialize()
    {
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("ZIAUL", crossPiece);

        PlayingPieceO noughtsPiece = new PlayingPieceO();
        Player player2 = new Player("ARUN", noughtsPiece);

        players.add(player1);
        players.add(player2);
        gameBoard = new Board(3);
    }

    public String startGame()
    {
        boolean noWinner = true;
        while(noWinner)
        {
            // take out the player whose turn is current and put it at the back
            Player playerTurn = players.removeFirst();

            // set the free space from the board
            gameBoard.printBoard();
            List<Pair<Integer,Integer>> freeSpaces = gameBoard.getFreeCells();
            if(freeSpaces.isEmpty())
            {
                noWinner = false;
                continue;
            }

            // read the userinput
            System.out.println("Player: " + playerTurn.name + " Enter row column");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputCol = Integer.valueOf(values[1]);

            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputCol, playerTurn.playingPiece);
            if(!pieceAddedSuccessfully)
            {
                System.out.println("Incorrect position chosen, try again ");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner = istherewinner(inputRow, inputCol, playerTurn.playingPiece.pieceType);
            if(winner)
            {
                return playerTurn.name;
            }
        }
        return "tie";
    }

    public boolean istherewinner(int row, int column, PieceType pieceType)
    {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        for(int i=0;i<gameBoard.size; i++)
        {
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType != pieceType)
            {
                rowMatch = false;
            }
        }

        for(int i=0;i<gameBoard.size; i++)
            {
                if(gameBoard.board[i][column]==null || gameBoard.board[i][column].pieceType != pieceType)
                {
                    columnMatch = false;
                }
            }
        for(int i=0;i<gameBoard.size; i++)
                {
                    if(gameBoard.board[i][i]==null || gameBoard.board[i][i].pieceType != pieceType)
                    {
                        diagonalMatch = false;
                    }
                }
        return rowMatch || columnMatch || diagonalMatch;
    }
}
