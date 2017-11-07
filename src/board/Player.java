package board;
import java.util.Scanner;

import exiled.Piece;
import pieces.PieceType;

public class Player {
	Scanner input;
	public int color;
	public char[] pInputArray;
	
	public Player(int color) {
		this.color = color;
		input = new Scanner(System.in);
		//0 = white, 1 = black
	}
	public void turn() {
		//Asks for move
		Main.display();
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.print("Which piece would you like to move, ");
			if (color == 0) {
				System.out.println("white?");
			}
			else {
				System.out.println("black?");
			}
			String pInput = input.nextLine();
			switch(pInput) {
				case "N":
					int x = getX();
					int y = getY();
					int moveX = moveX();
					int moveY = moveY();
					//Moves a knight with those things
					//Main.board[][]knight();
					isChoosing = false;
					break;
				case "R":
					rook();
					isChoosing = false;
					break;
				case "B":
					bishop();
					isChoosing = false;
					break;
				case "Q":
					queen();
					isChoosing = false;
					break;
				case "K":
					king();
					isChoosing = false;
					break;
				case "P":
					pawn();
					isChoosing = false;
				default:
					System.out.println("Invalid Letter for piece");
					break;
			}
		}
	}
	public void checkTurn() {
		System.out.println("You are in check. You need to get out of check");
	}
	public int moveY() {
		int moveY;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("To which rank or row would you like to move the piece?");
			String moveInput = input.nextLine();
			switch (moveInput) {
			case "1":
				moveY = 0;
				return moveY;
			case "2":
				moveY = 1;
				return moveY;
			case "3":
				moveY = 2;
				return moveY;
			case "4":
				moveY = 3;
				return moveY;
			case "5":
				moveY = 4;
				return moveY;
			case "6":
				moveY = 5;
				return moveY;
			case "7":
				moveY = 6;
				return moveY;
			case "8":
				moveY = 7;
				return moveY;
			default:	
				System.out.println("Invalid Square");
				break;
			}
		}
		return (Integer) null;
		
	}
	public int moveX() {
		int moveX;
		boolean isChoosing = true;
		while (isChoosing) {
			System.out.println("To which file or column would you like to move the piece?");
			String moveInput = input.nextLine();
			switch (moveInput) {
				case "a":
					moveX = 0;
					return moveX;
				case "b":
					moveX = 1;
					return moveX;
				case "c":
					moveX = 2;
					return moveX;
				case "d":
					moveX = 3;
					return moveX;
				case "e":
					moveX = 4;
					return moveX;
				case "f":
					moveX = 5;
					return moveX;
				case "g":
					moveX = 6;
					return moveX;
				case "h":
					moveX = 7;
					return moveX;
				default:
					System.out.println("Invalid Square");
					break;
			}
		}	
		return (Integer) null;
		//Moves piece to square specified
		
	}
	public void knight() {
		int x = getX();
		int y = getY();
		if (Main.board[x][y].type.equals(PieceType.N)) { //It is a knight
			int nextX = moveX();
			int nextY = moveY();
			if (Main.board[x][y].color==color) { //Checks if player owns the piece
				if (Math.abs(x-nextX)==2) { //Checks if change in x is 2
					if (Math.abs(y-nextY)==1) {
						//Succeeds
						if (!Main.board[nextX][nextY].type.equals(PieceType.X)) { //If not blank
							if (Main.board[nextX][nextY].color == color) { //Colors match, same team, cannot take
								System.out.println("You cannot move a piece on top of another one of your pieces");
							}
							else {
								//Finds a blank to set the empty spot to
								for (int i = 0; i<8; i++) {
									for (int j = 0; j<8; j++) {
										//If the piece found is blank, set empty space to blank
										if (Main.board[j][i].type.equals(PieceType.X)) {
											Main.board[nextX][nextY]=Main.board[x][y];
											Main.board[x][y] = Main.board[j][i];
											break;
										}
									}
								}
							}
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
						turn();
					}
				}
				else if (Math.abs(x-nextX)==1) { //Checks if change in y is 2
					if (Math.abs(y-nextY)==2) {
						//Succeeds
						if (!Main.board[nextX][nextY].type.equals(PieceType.X)) { //If not blank
							if (Main.board[nextX][nextY].color == color) { //Colors match, same team, cannot take
								System.out.println("You cannot move a piece on top of another one of your pieces");
							}
							else {
								//Finds a blank to set the empty spot to
								for (int i = 0; i<8; i++) {
									for (int j = 0; j<8; j++) {
										//If the piece found is blank, set empty space to blank
										if (Main.board[j][i].type.equals(PieceType.X)) {
											Main.board[nextX][nextY]=Main.board[x][y];
											Main.board[x][y] = Main.board[j][i];
											break;
										}
									}
								}
							}
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
						turn();
					}
				}
				else {
					System.out.println("Invalid knight move");
					turn();
				}
			}
			else {
				System.out.println("That is not your piece");
				turn();
			}
		}
	}
	public int getY() {
		int y;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("Which rank or row is your piece in?");
			String file = input.nextLine();
			switch(file) {
				//Arrays start at zero, uses the 2d array made in main
				case "1":
					y = 0;
					return y;
				case "2":
					y = 1;
					return y;
				case "3":
					y = 2;
					return y;
				case "4":
					y = 3;
					return y;
				case "5":
					y = 4;
					return y;
				case "6":
					y = 5;
					return y;
				case "7":
					y = 6;
					return y;
				case "8":
					y = 7;
					return y;
				default:
					System.out.println("Invalid position.");
					break;
			}
		}
		return (Integer) null;
	}
	public int getX() {
		int x;
		boolean isChoosing = true;
		while(isChoosing) {
			System.out.println("Which file or column is your piece in?");
			String file = input.next();
			switch(file) {
				//letters for columns
				case "a":
					x = 0;
					return x;
				case "b":
					x = 1;
					return x;
				case "c":
					x = 2;
					return x;
				case "d":
					x = 3;
					return x;
				case "e":
					x = 4;
					return x;
				case "f":
					x = 5;
					return x;
				case "g":
					x = 6;
					return x;
				case "h":
					x = 7;
					return x;
				default:
					System.out.println("Invalid position.");
					break;
			}
		}
		return (Integer) null;
	}
	public void pawn() {
		int x = getX();
		int y = getY();
		int nextX = moveX();
		int nextY = moveY();
	}
	public void rook() {
		int x = getX();
		int y = getY();
		int nextX = moveX();
		int nextY = moveY();
	}
	public void bishop() {
		int x = getX();
		int y = getY();
		int nextX = moveX();
		int nextY = moveY();
	}
	public void queen() {
		int x = getX();
		int y = getY();
		int nextX = moveX();
		int nextY = moveY();
	}
	public void king() {
		int x = getX();
		int y = getY();
		int nextX = moveX();
		int nextY = moveY();
	}
}
