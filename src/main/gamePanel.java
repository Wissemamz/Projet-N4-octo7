package main;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Entity.Entity;
import Entity.down;
import Entity.enemy;
import Entity.up;
import tile.Tile;
import tile.TileManager;

public class gamePanel extends JPanel implements Runnable{
	// SCREEN SETINGS
	final int originalTileSize= 16; //16x16 pixels
	final int scale = 4;
	
	public final int tileSize = originalTileSize * scale;
	
	public final int maxScreenCol=10; 
	public final int maxScreenRow=5;
	public final int screenWidth=tileSize * maxScreenCol;
	public final int screenHeight=tileSize * maxScreenRow;
	
	//FPS
	int FPS = 60;
	
	Thread gameThread;
	Board board = new Board();
	
	TileManager tileM = new TileManager(this);
	
	Move mouse= new Move();

	public static ArrayList<Entity> players = new ArrayList<>();
	public static ArrayList<Entity> simPlayers = new ArrayList<>();
	Entity activep;
	
	boolean canMove;
	boolean validSquare;
	
	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		//this.setDoubleBuffered(true);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(mouse);
		
		setPlayer();
		copyPlayer(players, simPlayers);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void setPlayer() {
		
		players.add(new down(0,0));
		players.add(new up(4, 4));
		players.add(new enemy(2, 3));
	}
	
	public void copyPlayer(ArrayList<Entity> source, ArrayList<Entity> target) {
		target.clear();
		for(int i=0;i<source.size(); i++) {
			target.add(source.get(i));
		}
		
		
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
		
		// bouton de la sourie is pressed
		if(mouse.pressed) {
			if(activep == null) {
				//si activep est null, on check si on peut pick up un robot
				for(Entity player : simPlayers) {
					//si la sourie est sur le bon robot , on la pick up 
					if(player.col == mouse.x/Board.SQUARE_SIZE && 
							player.row == mouse.y/Board.SQUARE_SIZE )
					
						{activep = player;}
					
				}
			}else {
			//si le joueur is holding un robot, on simule le mouvement
			simulate();
			}
		}
		
		
		if(mouse.pressed == false) {
		// bouton de la sourie is released
			if(activep != null) {
				
				if(validSquare) {
					activep.updatePosition();
					
				}
				else {
					activep.resetPosition();
					activep = null;
				}
			}
		}
	}
	private void simulate() {
		canMove = false;
		validSquare = false;
		
		
		//si un robot is being held, on update sa position  
		activep.x = mouse.x - Board.HALF_SQUARE_SIZE;
		activep.y = mouse.y - Board.HALF_SQUARE_SIZE;
		
		activep.col = activep.getCol(activep.x);
		activep.row = activep.getRow(activep.y);
		
		//check si le robot ne sort pas du board 
		if(activep.canMove(activep.col, activep.row)) {
			canMove = true;
			validSquare = true ;
		}
			
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		board.draw(g2);
		
		tileM.draw(g2);
		
		for(Entity p : simPlayers) {
			p.draw(g2);
		}
		
		if(activep != null) {
			if(canMove) {
				g2.setColor(Color.white);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
				g2.fillRect(activep.col*Board.SQUARE_SIZE, activep.row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
			//il faut la dessiner a la fin pour qe'elle ne soit pas cache derriere le board
			activep.draw(g2);
		
		}
	}
	
}
