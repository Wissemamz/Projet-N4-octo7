package model;

import java.awt.Graphics2D;
import java.io.IOException;


import javax.imageio.ImageIO;

public class TileManager {

		GamePanel gp;
		Tile[] tile;
		int mapTileNum[][];
		
		public TileManager(GamePanel gp) {
			
			this.gp=gp;
			//tableau pour tout les types des tiles grass, water, ...
			tile = new Tile[10];
			mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
			getTileImage();
		}
		
		public void getTileImage() {
			try {
				
				tile[0] = new Tile();
				tile[0].image  = ImageIO.read(getClass().getResourceAsStream("/tyles/grass1.png"));
			
				tile[1] = new Tile();
				tile[1].image  = ImageIO.read(getClass().getResourceAsStream("/tyles/water1.png"));
			
				tile[2] = new Tile();
				tile[2].image  = ImageIO.read(getClass().getResourceAsStream("/tyles/earth.png"));

				tile[3] = new Tile();
				tile[3].image  = ImageIO.read(getClass().getResourceAsStream("/tyles/wall.png"));

				tile[4] = new Tile();
				tile[4].image  = ImageIO.read(getClass().getResourceAsStream("/tyles/magma.png"));

			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void draw(Graphics2D g2){
			
			int col=0;
			int row=0;
			int x=0;
			int y=0;
			
			while(col<gp.maxScreenCol && row < gp.maxScreenRow) {
				int tileNum = mapTileNum[col][row];
				
				g2.drawImage(tile[tileNum].image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
				col++;
				x+= GamePanel.tileSize;
				
				if(col == gp.maxScreenCol) {
					col = 0;
					x = 0;
					row++;
					y += GamePanel.tileSize;
				}
			}
		}
}
