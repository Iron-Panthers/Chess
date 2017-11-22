package pieces;

import java.util.ArrayList;

public abstract class Piece {
	public static PieceType type;
	public static int color;
	public boolean hasMoved;
//	public Piece(int color){
//		this.color = color;
//	}
	public abstract String toString();
	public abstract ArrayList<String> legalMoves(int x, int y);
	public abstract boolean isInRange(int x, int y);
	public abstract boolean checkMove(int x, int y, int moveX, int moveY, Piece[][] board);
	public abstract boolean movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY);
	public abstract void capture(int x, int y, int moveX, int moveY);
	
}
