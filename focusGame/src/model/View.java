package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import view.Circle;

public class View {
	private Circle circle,emptyCircle;
	ArrayList<Circle> circles;
	private final int ORIGINALPOS=500;
	private Point center;
	private int i,j;
	private int t;
	public View(int i, int j){
		circles=new ArrayList<Circle>();
		this.i=i;
		this.j=j;
		center= new Point(ORIGINALPOS+j*67,ORIGINALPOS+i*67);
		emptyCircle=new Circle(center);
		circle = new Circle(center);
		circles.add(circle);
	}
	public void setTurn(int i){
		t=i;
		setCircleColor(circles.get(circles.size()-1));
	}
	public void setCircleColor(Circle circle){
		if(t==0){
			circle.setColor(Color.GREEN);
		}else if(t==1) {
			circle.setColor(Color.RED);
		}else {
			circle.setColor(Color.WHITE);
		}
	}
	
	public Circle getCircle(){
		if(circles.size()==0){
			return emptyCircle;
		}
		return circles.get(circles.size()-1);
	}
	public void addCircle(Circle c){
		circles.add(c);
		c.changeCenter(center);
	}
	public boolean isEmpty(){
		return circles.size()==0;
	}
	public Circle getTop() {
		// TODO Auto-generated method stub
		if(circles.size()==0){
			return emptyCircle;
		}
		return circles.remove(circles.size()-1);
	}
}
