package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.gamePanel;

public class Entity {
	public int x, y;
	public int col, row, preCol, preRow;
	
	public BufferedImage image;
	public Entity hittingP;
	
	public Entity(int col, int row) {
        this.col= col;
        this.row= row;
        
        x = getX(col);
        y = getY(row);
        
        preCol = col;
        preRow = row;
	}


	public int getX(int col) {
		return col*Board.SQUARE_SIZE;
	
	}	

	public int getY(int row) {
		return row*Board.SQUARE_SIZE;
	
	}
	
	public int getCol(int x) {
		return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
		
	}
	
	public int getRow(int y) {
		return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
		
	}
	public void updatePosition() {
		
		x = getX(col);
		y = getY(row);
		preCol = getCol(x);
		preRow = getRow(y);
	}
	
	public void resetPosition() {
		col = preCol;
		row = preRow;
		x = getX(col);
		y = getY(row);	
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		return false;
	}
	
	public boolean isWithinBoard(int targetCol, int targetRow) {
		if(targetCol >= 0 && targetCol <= 4 && targetRow >= 0 && targetRow<=4) {
			return true;
		}
		return false;
	}
	public Entity getHittingP(int targetCol, int targetRow) {
		for(Entity player : gamePanel.simPlayers) {
			if(player.col == targetCol && player.row == targetRow && player != this)
				return player;
			}
		return null;
	}
	
	public BufferedImage getPlayerImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path+".png"));
		}catch(IOException e) {	
			e.printStackTrace();
		}
		return image; 
	}
	
	public boolean isValidSquare(int targetCol, int targetRow) {
		
		hittingP = getHittingP(targetCol, targetRow);
		
		if(hittingP == null) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y,Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
	}


	
}
 