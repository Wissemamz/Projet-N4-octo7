package main;

import java.util.Scanner;



public class main {

	

	public static void main(String[] args){
		final var scanner = new Scanner(System.in);
		final var game = new jeu();

		var player = Player.FIRST;

		while(true){
			System.out.println(game);
			System.out.println(player + " / 1 ou 0 ? -->  \n");
			final var inputUser=scanner.nextInt();

			game.processInput(player,inputUser);
			player=nextPlayer(player);
		}
	}

	private static Player nextPlayer(Player player){
		if(player == Player.FIRST){
			return Player.SECOND;
		}else{
			return Player.FIRST;
		}

	}
}
