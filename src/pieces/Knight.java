package pieces;

import board.Main;
import board.Player;

public class Knight extends Piece{
	public int color;
	public PieceType type;
	public Player owner;
	public Knight(int color) {
		this.color = color;
		type = PieceType.N;
		for (int i = 0; i<2; i++) { //Finds the player that matches the pieces color
			if (Main.players.get(i).color == color) {
				owner = Main.players.get(i);
				continue;
			}
		}
	}
	public void choose(int x, int y, int moveX, int moveY) {
		if ((0<=moveX && moveX<=7)&&(0<=moveY && moveY<=7)) { //Checks if move is in bounds
			if (Main.board[x][y].type==(type)) { //It is a knight
				if (Main.board[x][y].color==color) { //Checks if player owns the piece
					move(x,y,moveX,moveY);
				}
				else {
					System.out.println("That is not your piece");
					owner.turn();
				}
			}	
		}
	}
	public void move(int x, int y, int moveX, int moveY) {
		int nextX = moveX;
		int nextY = moveY;
		if (Math.abs(x-nextX)==2) { //Checks if change in x is 2
			if (Math.abs(y-nextY)==1) {
				//Succeeds
				if (!(Main.board[nextX][nextY].type==(PieceType.X))) { //If not blank
					if (Main.board[nextX][nextY].color == color) { //Colors match, same team, cannot take
						System.out.println("You cannot move a piece on top of another one of your pieces");
					}
					Main.board[nextX][nextY]=Main.board[x][y];
					Main.board[x][y] = Main.blank;
//					else {
//						//Finds a blank to set the empty spot to
//						for (int i = 0; i<8; i++) {
//						for (int j = 0; j<8; j++) {
//								//If the piece found is blank, set empty space to blank
//								if (Main.board[j][i].type==(PieceType.X)) {
//									Main.board[nextX][nextY]=Main.board[x][y];
//									Main.board[x][y] = Main.board[j][i];
//									break;
//								}
//							}
//						}
//					}
				}
				else {
					//Replaces blank with knight, replaces knight with blank
					Piece blank = Main.board[nextX][nextY];
					Main.board[nextX][nextY]=Main.board[x][y];
					Main.board[x][y] = blank;
				}
			}
			else {
				System.out.println("Invalid knight move");
				owner.turn();
			}
		}
		else if (Math.abs(x-nextX)==1) { //Checks if change in y is 2
			if (Math.abs(y-nextY)==2) {
				//Succeeds
				if (!(Main.board[nextX][nextY].type==(PieceType.X))) { //If not blank
					if (Main.board[nextX][nextY].color == color) { //Colors match, same team, cannot take
						System.out.println("You cannot move a piece on top of another one of your pieces");
					}
					Main.board[nextX][nextY]=Main.board[x][y];
					Main.board[x][y] = Main.blank;
//					else {
//						//Finds a blank to set the empty spot to
//						for (int i = 0; i<8; i++) {
//							for (int j = 0; j<8; j++) {
//								//If the piece found is blank, set empty space to blank
//								if (Main.board[j][i].type==(PieceType.X)) {
//									Main.board[nextX][nextY]=Main.board[x][y];
//									Main.board[x][y] = Main.board[j][i];
//									break;
//								}
//							}
//						}
//					}
				}
				else {
					//Replaces blank with knight, replaces knight with blank
					Piece blank = Main.board[nextX][nextY];
					Main.board[nextX][nextY]=Main.board[x][y];
					Main.board[x][y] = blank;
				}
			}
			else {
				System.out.println("Invalid knight move");
				owner.turn();
			}
		}
		else {
			System.out.println("Invalid knight move");
			owner.turn();
		}
	}
			

	public void capture(int x, int y, int moveX, int moveY) { //Not needed for knight, captures the same way it moves
		
	}
	@Override
	public String toString() {
		return type.toString();
		// TODO Auto-generated method stub
	}
}
