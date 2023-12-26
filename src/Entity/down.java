package Entity;

public class down extends Entity{
	
	public down(int col, int row) {
		super(col, row);
		
		image = getPlayerImage("/player/octo_down");
	}
		
	public boolean canMove(int targetCol, int targetRow) {
			
			if(isWithinBoard(targetCol, targetRow)) {
				
				if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
					
					if(isValidSquare(targetCol, targetRow)) {
						return true;
					}
				}
			}
			return false; 
	}
}

