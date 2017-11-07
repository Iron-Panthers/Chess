package pieces;

public abstract class Piece {
	public static PieceType type;
	public static int color;
	public abstract String toString();
	public abstract void move(int x, int y, int moveX, int moveY);
	public abstract void capture(int x, int y, int moveX, int moveY);
	
}
