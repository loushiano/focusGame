package model;

public class Player {
	private int piecesCaptured;
	private int id;
	private boolean myTurn;
	
	public Player(int id){
		this.setId(id);
	}

	public int getId() {
		return id;
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
