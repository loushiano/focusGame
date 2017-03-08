package model;

import java.awt.Color;
import java.awt.Point;

import view.Circle;

public class View {
	private Circle circle;
	private final int ORIGINALPOS=500;
	private int i,j;
	private int t;
	public View(int i, int j){
		this.i=i;
		this.j=j;
		Point p = new Point(ORIGINALPOS+j*67,ORIGINALPOS+i*67);
		circle = new Circle(p);
	}
	public void setTurn(int i){
		t=i;
		setCircleColor();
	}
	public void setCircleColor(){
		if(t==0){
			circle.setColor(Color.GREEN);
		}else if(t==1) {
			circle.setColor(Color.RED);
		}else {
			circle.setColor(Color.WHITE);
		}
	}
	
	public Circle getCircle(){
		return circle;
	}
}
