package pieces;

public abstract class Piece {
	public abstract void move(int x, int y, int moveX, int moveY);
	public abstract void capture(int x, int y, int moveX, int moveY);
	
}
