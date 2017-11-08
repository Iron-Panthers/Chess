package pieces;

import java.util.ArrayList;

public abstract class Piece {
	public static PieceType type;
	public static int color;
	public abstract String toString();
	public abstract ArrayList<String> legalMoves(int x, int y);
	public abstract void checkMove(int x, int y, int moveX, int moveY);
	public abstract void movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY);
	public abstract void capture(int x, int y, int moveX, int moveY);
	
}
