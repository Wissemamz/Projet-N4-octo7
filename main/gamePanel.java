package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

import Entity.player;

public class gamePanel extends JPanel implements Runnable{
	// SCREEN SETINGS
	final int originalTileSize= 16; //16x16 pixels
	final int scale = 6;
	
	public final int tileSize = originalTileSize * scale;
	
	final int maxScreenCol=5; 
	final int maxScreenRow=5;
	final int screenWidth=tileSize * maxScreenCol;
	final int screenHeight=tileSize * maxScreenRow;
	
	//FPS
	int FPS = 60;
	
	KeyMove KeyM= new KeyMove();
	Thread gameThread;
	player player = new player(this, KeyM);
	
	//set players default positions
	public int playerX = 100;
	public int playerY = 100;
	public int playerSpeed = 4;

	

	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyM);
		this.setFocusable(true);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
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
	
	public void update() {
	    
		player.update();
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
	
}
