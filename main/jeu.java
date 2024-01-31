package main;



public class jeu {
    
    private char[][] grid = new char[][]{
        {'.' ,'.' ,'.','.' ,'.'},
        {'.' ,'.' ,'.','.' ,'.'},
        {'.' ,'.' ,'.','.' ,'.'},
        {'.' ,'.' ,'.','.' ,'.'},
        {'.' ,'.' ,'.','.' ,'.'}
    };
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String SPACE = " ";
    
    public void processInput(Player player,int inputUser) {
        var row=(inputUser - 1 )/5;
        var column=(inputUser - 1)%5;
        if(grid[row][column]=='.'){
            if(player.equals(Player.FIRST)){
                grid[row][column]='X';
            }else{
                grid[row][column]='O';
            }
        }
    }


    @Override
    public String toString(){
        final var builder = new StringBuilder();
        builder.append("Grille Octopunks : ").append(LINE_SEPARATOR);
        for(char[] lines : grid ){
            for(char cell : lines){
                builder.append(SPACE).append(cell).append(SPACE);
            }
            builder.append(LINE_SEPARATOR);
        }
        return builder.toString();
 
    }

   


}