package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    
    final static int originalTileSize= 16; //16x16 pixels
	final static int scale = 4;
	
	public final static int tileSize = originalTileSize * scale;
	
	public final int maxScreenCol=10; 
	public final int maxScreenRow=5;
	public final int screenWidth=tileSize * maxScreenCol;
	public final int screenHeight=tileSize * maxScreenRow;

    int FPS=60;

    Graphics2D g2;

    Thread gameThread;

    Board board = new Board();

    TileManager tileM = new TileManager(this);

    public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.white);
		//this.setDoubleBuffered(true)

	}


    @Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update(g2);
			
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval; 
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    @Override
    public void update(Graphics g) {
        // TODO Auto-generated method stub
        super.update(g);
    }

    @Override
    public void repaint() {
        // TODO Auto-generated method stub
        super.repaint();
    }

    public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

        board.draw(g2);

		Robot.draw(g2);
		
		g2.dispose();
	}

}
