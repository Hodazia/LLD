package SnakeLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    // a board has cells with number marked, with snakes and ladders
    Cell[][] cells;

    Board(int boardSize, int numberOfSnakes, int numberOfLadders)
    {
        intiializeCells(boardSize);
        AddSnakeLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void intiializeCells(int boardSize)
    {
        cells = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                Cell cellobj = new Cell();
                cells[i][j] = cellobj;
            }
        }

    }

    private void AddSnakeLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders)
    {
        while(numberOfSnakes>0)
        {
            int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length - 1);
            if(snakeTail>=snakeHead)
            {
                continue;
            }

            Jump snakeobj = new Jump();
            snakeobj.start = snakeHead;
            snakeobj.end = snakeTail;

            Cell cell =getCell(snakeHead);
            cell.jump = snakeobj;

            numberOfSnakes--;
        }

        while(numberOfLadders>0)
            {
                int LadderHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length - 1);
                int LadderTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length - 1);
                if(LadderTail<=LadderHead)
                {
                    continue;
                }
    
                Jump ladderobj = new Jump();
                ladderobj.start = LadderHead;
                ladderobj.end = LadderTail;
    
                Cell cell =getCell(LadderHead);
                cell.jump = ladderobj;
    
                numberOfLadders--;
            }
    }

    Cell getCell(int playerPositon)
    {
        int boardRow = playerPositon / cells.length;
        int boardColumn = (playerPositon % cells.length);
        return cells[boardRow][boardColumn];
    }

}

class Cell {
    Jump jump;

}
