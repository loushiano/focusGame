package view;
/*
 * This class CirclePanel is responsible for representing the circle panel in the topology.
 * @author Ali Al-Saaidi
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;



public class CirclePanel extends JPanel {
	private ArrayList<Circle> circles;//ArrayList of circles

	private ArrayList<Point2D> points;
	
	private int counter;
	private Timer _timer;
	
	/*
	 * Constructor that initialize the fields of this class
	 */
		public CirclePanel(){
			circles=new ArrayList<Circle>();
		}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
			this.setBackground(Color.WHITE);
		
		
		
		if(circles.size()!=0){
			for(Circle c:circles){
				drawCircle(c,g);
			}
		}
		
		
		
	}
	/*
	 * draws a circle on the panel
	 * @param circle to be drawn
	 * @param g the graphics of the panel
	 */
	public void drawCircle(Circle circle,Graphics g) {
	  
	    g.setColor(Color.BLUE);
	    circle.draw(g);
	   
	}
	/*
	 * adds a circle to the circles of the panel
	 * @param circle to be added to the circles of the panel
	 */
	public void addCircle(Circle circle) {
		if(!circles.contains(circle)){
		circles.add(circle);
		}else {
			circles.remove(circle);
			addCircle(circle);
		}
	}
	/*
	 * invokes repaint to show all missing shapes
	 */
	public void draw() {
		repaint();
		
	}
	
	

	boolean b=true;
	
	
	/*
	 * this method transfers the Rectangle message from this circle (this node) to one of the node in the given array list of nodes
	 * @param message the given message 
	 * @param circle the circle that represents the node
	 * @param nodes is the array list of nodes 
	 */
	/*public void moveMessage(String message, Circle circle, ArrayList<Router> nodes) {
		points=new ArrayList<Point2D>();
		for(Router n: nodes){
			Circle circle2=n.getCircle();
		
			
			int x=(int)circle.getCenter().getX();
			int y=(int)circle.getCenter().getY();
			rectangle=new RectangleMessage(message,x,y);
		Line line =getLine(circle,circle2);
		Point2D current;
		for (Iterator<Point2D> it = new LineIterator(line); it.hasNext();) {
		    current = it.next();
		    points.add(current);
		}
		}
		 counter=0;
		 
		 
		timer.start();
			
		
			 	
		}	 
		    
		*/	 
	
		    	
		    

	

			
		
		
		 
		
		
		
	
	
	
	/*
	 * this method is responsible to stop the timer 
	 */

	

	
	
	/*
	 * return the circles of the panel
	 * @return the circles of the panel
	 */
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	/*
	 * return the lines of the panel
	 * @return the lines of the panel
	 */
	

}