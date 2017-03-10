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
	private int capturedOne;
	private int capturedTwo;
	public View(int i, int j){
		circles=new ArrayList<Circle>();
		this.setI(i);
		this.setJ(j);
		center= new Point(ORIGINALPOS+j*67,ORIGINALPOS+i*67);
		emptyCircle=new Circle(center);
		capturedOne=0;
		capturedTwo=0;
		
	}
	public View(View v){
		 i=v.getI();
		 j =v.getJ();
			center= new Point(ORIGINALPOS+j*67,ORIGINALPOS+i*67);
		emptyCircle=v.getEmptyCopy();
		circles=v.getCirclesCopy();
		capturedTwo=v.getCapturedTwo();
		capturedOne=v.getCapturedOne();
		
	}
	public ArrayList<Circle> getCirclesCopy() {
		ArrayList<Circle> c = new ArrayList<Circle>();
			for(Circle c1:circles){
				c.add(new Circle(c1));
			}
			return c;
		}
		
	public String toString(){
		return getCircle().toString();
	}
	private Circle getEmptyCopy() {
			return new Circle(emptyCircle);
	}
	public void setTurn(int i){
		t=i;
		Circle c= new Circle(center);
		circles.add(c);
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
			System.out.println("goum bi");
		}
		return circles.remove(circles.size()-1);
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public void addCircle(int size, ArrayList<Circle> c) {
		for(int i=0;i<size;i++){
			addCircle(c.remove(c.size()-1));
		}
		
		if(circles.size()>5){
			removeUntilFive();
		}
		
		
	}
	private void removeUntilFive() {
		while(circles.size()>5){
			
			Circle c=circles.remove(0);
			if(circles.get(circles.size()-1).getColor()==Color.RED){
				setCapturedTwo(getCapturedTwo() + 1);
				//System.out.println(capturedTwo);
				
			}else{
				
					setCapturedOne(getCapturedOne() + 1);
					}
			}
		}
		
	
	public ArrayList<Circle> getCirclesCopyt() {
		// TODO Auto-generated method stub
		return null;
	}
	public void remove(int size) {
		for(int i=0;i<size;i++){
			if(!circles.isEmpty()){
			circles.remove(circles.size()-1);
			}
		}
		
	}
	public ArrayList<Circle> getCircles() {
		// TODO Auto-generated method stub
		return circles;
	}
	public int getCapturedOne() {
		return capturedOne;
	}
	public void setCapturedOne(int capturedOne) {
		this.capturedOne = capturedOne;
	}
	public int getCapturedTwo() {
		return capturedTwo;
	}
	public void setCapturedTwo(int capturedTwo) {
		this.capturedTwo = capturedTwo;
	}
	public void setCircles(ArrayList<Circle> c) {
		circles=c;
		
	}
	
}
