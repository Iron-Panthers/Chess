package pieces;

import java.util.ArrayList;

import board.Constants;
import board.Main;
import board.Player;

public class Bishop extends Piece{
	public PieceType type;
	public Player owner;
	public int color;
	public Bishop(int color) {
		this.color = color;
		type = PieceType.B;
		for (int i = 0; i<2; i++) { //Finds the player that matches the pieces color
			if (Main.players.get(i).color == color) {
				owner = Main.players.get(i);
				continue;
			}
		}
	}
	public void checkMove(int x, int y, int moveX, int moveY) {
		if ((0<=moveX && moveX<=7)&&(0<=moveY && moveY<=7)) { //Checks if move is in bounds
			if (Main.board[x][y].type==(type)) { //It is a knight
				if (Main.board[x][y].color==color) { //Checks if player owns the piece
					movePiece(legalMoves(x,y),x,y,moveX,moveY);
				}
				else {
					System.out.println("That is not your piece");
					owner.turn();
				}
			}	
		}
	}
	@Override
	public void movePiece(ArrayList<String> legalMoves, int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		String playerMovement = moveX+","+moveY;
		if (legalMoves.contains(playerMovement)) {
			//Moves knight to new space, replaces empty space with a blank
			Main.board[moveX][moveY] = Main.board[x][y]; 
			Main.board[x][y] = Main.blank;
		}
	}

	@Override
	public void capture(int x, int y, int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
		return type.toString();
		// TODO Auto-generated method stub
	}
	public int getMaxDistance(int x, int y) {
		int maxDistance;
		int maxX = (Constants.BOARD_LENGTH-1)-x;
		int maxY = (Constants.BOARD_HEIGHT-1)-y;
		if (maxX>maxY) {
			maxDistance = maxY;
			return maxDistance;
		}
		else if (maxY>maxX) {
			maxDistance = maxX;
			return maxDistance;
		}
		else { //If they are the same
			maxDistance = maxX;
			return maxDistance;
		}
	}
	@Override
	public ArrayList<String> legalMoves(int x, int y) {
		ArrayList<String> legalMoves = new ArrayList<String>();
		//Sees the shortest distance to move, picks that
		int maxDistance = getMaxDistance(x,y);
		//Up and to the right
		for (int i = 0; i<maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if ((Main.board[x+i][y+i].color != color)&&(((x+i)>=0)&&((x+i)<=7))&&((y+i)>=0)&&((y+i)<=7)){
				legalMoves.add((x+i)+","+(y+i));
			}
			else {
				break;
			}
		}
		//Up and to the left 
		for (int i = 0; i<maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if ((Main.board[x-i][y+i].color != color)&&(((x-i)>=0)&&((x-i)<=7))&&((y+i)>=0)&&((y+i)<=7)){
				legalMoves.add((x-i)+","+(y+i));
			}
			else {
				break;
			}
		}
		//Down and to the right
		for (int i = 0; i<maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if ((Main.board[x+i][y-i].color != color)&&(((x+i)>=0)&&((x+i)<=7))&&((y-i)>=0)&&((y-i)<=7)){
				legalMoves.add((x+i)+","+(y-i));
			}
			else {
				break;
			}
		}
		//Down and to the left
		for (int i = 0; i<maxDistance; i++) {
			//Sees if this move is in the board and is not moving on its own color
			if ((Main.board[x-i][y-i].color != color)&&(((x-i)>=0)&&((x-i)<=7))&&((y-i)>=0)&&((y-i)<=7)){
				legalMoves.add((x-i)+","+(y-i));
			}
			else {
				break;
			}
		}
		return legalMoves;
	}
}
