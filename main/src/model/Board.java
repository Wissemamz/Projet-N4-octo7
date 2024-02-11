package model;

import java.awt.Color;
import java.awt.Graphics2D;


public class Board {
	final int MAX_COL = 9;
	final int MAX_ROW = 5;
	public static final int SQUARE_SIZE = 64;
	public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;
	
	
	public void draw(Graphics2D g2) {

        for (int row = 0; row < MAX_ROW-4; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                    
                g2.setColor(new Color(175, 115, 70));
                g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);  
            
            }
        }
    }

    public void draw_comm(Graphics2D g2) {

        for (int row = 5; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {

                g2.setColor(new Color(210, 165, 125));
                g2.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
               
            }
            
            
        }

    }
}
