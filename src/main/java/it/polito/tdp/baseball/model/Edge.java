package it.polito.tdp.baseball.model;

public class Edge {
	People player1 ;
	People player2 ;
	public Edge(People player1, People player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
	public People getPlayer2() {
		// TODO Auto-generated method stub
		return player2;
	}
	public People getPlayer1() {
		return player1 ;
	}

	
}
