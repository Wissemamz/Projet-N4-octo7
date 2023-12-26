package Entity;

public class enemy extends Entity {
	
	public enemy(int col, int row) {
		super(col, row);
		
		image = getPlayerImage("/player/enemy1");
	}
}
