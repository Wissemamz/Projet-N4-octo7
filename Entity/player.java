package Entity;

import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyMove;
import main.gamePanel;

public class player extends Entity{
	gamePanel gp;
	KeyMove KeyM;

	
	public player(gamePanel gp, KeyMove KeyM) {
	        this.gp = gp;
	        this.KeyM = KeyM;

	        setDefaultValues();
	        getPlayerImage();
	    }

	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 5;
		direction="down";
	}
	
	public void getPlayerImage() {
		try {
			 up1 = ImageIO.read(getClass().getResourceAsStream("/player/octo-up1.png"));
			 down1 = ImageIO.read(getClass().getResourceAsStream("/player/octo-front1.png")); 
			 up2 = ImageIO.read(getClass().getResourceAsStream("/player/octo-up2.png"));
			 down2 = ImageIO.read(getClass().getResourceAsStream("/player/octo-front1-2.png"));
			 right1 = ImageIO.read(getClass().getResourceAsStream("/player/octo-right1.png"));
			 right2= ImageIO.read(getClass().getResourceAsStream("/player/octo-right1.png"));
			 left1 = ImageIO.read(getClass().getResourceAsStream("/player/octo-left1.png"));
			 left2 = ImageIO.read(getClass().getResourceAsStream("/player/octo-left1.png"));

		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void update() {
	    if (KeyM.upPressed) {
	        direction = "up";
	        y -= speed;
	    } else if (KeyM.downPressed) {
	        direction = "down";
	        y += speed;
	    } else if (KeyM.leftPressed) {
	        direction = "left";
	        x -= speed;
	    } else if (KeyM.rightPressed) {
	        direction = "right";
	        x += speed;
	    }
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage image= null;
		switch(direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;
		
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
