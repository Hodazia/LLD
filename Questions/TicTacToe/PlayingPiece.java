package TicTacToe;

public class PlayingPiece {
    public PieceType pieceType;
    PlayingPiece(PieceType type){
        this.pieceType = pieceType;
    }
}

class PlayingPieceX extends PlayingPiece{
    public PlayingPieceX()
    {
        super(PieceType.X);
    }
}



class PlayingPieceO extends PlayingPiece{
    public PlayingPieceO()
    {
        super(PieceType.O);
    }
}

