package model;

import java.util.ArrayList;

public class Player {
	private int piecesCaptured;
	private int id;
	private boolean myTurn;
	private ArrayList<Node> capturedPieces;
	public Player(int id){
		this.setId(id);
		capturedPieces = new ArrayList<Node>();
	}

	public int getId() {
		return id;
	}
	public void addCaptured(Node n){
		capturedPieces.add(n);
	
	}
	public ArrayList<Node> getCaptured(){
		return capturedPieces;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public int getPiecesCaptured() {
		return piecesCaptured;
	}

	public void setPiecesCaptured(int piecesCaptured) {
		this.piecesCaptured = piecesCaptured;
	}
	
	
}
