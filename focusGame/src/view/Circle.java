package view;
/*
 * This class Circle is responsible for representing the circle in the topology.
 * @author Ali Fawaz
 */
import java.awt.*;   // for Graphics
import java.util.ArrayList;





//Each Circle object represents a circle in the 2D plane

//with a given center and radius.

public class Circle {
	private final int RADIUS=30;
	private Point center;   // fields
	private int radius;
	private Color color;
	


	/*
	 *  constructor
	 *  @param centre the center of the circle
	 *  @param radius the radius of the center
	 *  @param letter the letter that the circle holds
	 */
	public Circle(Point center) {

		this.center = center;

		this.radius = RADIUS;
		color =Color.WHITE;
		

	}

	/*
	 * 
	 * Returns the area of this circle.
	 * @return the are of this circle
	 */

	public Circle(Circle emptyCircle) {
		this.center=new Point((int)emptyCircle.getCenter().getX(),(int)emptyCircle.getCenter().getY());
		this.radius=RADIUS;
		color=emptyCircle.getColor();
	}

	public double getArea() {

		return Math.PI * Math.pow(this.radius, 2);

	}
	public void changeCenter(Point p){
		center=new Point((int)p.getX(),(int)p.getY());
	}
	

	/*
	 *  Returns the circumference of this circle (distance around the circle).
	 *  @return the circumference of this circle (distance around the circle).
	 */

	public Point getCenter() {
		return center;
	}
	/*
	 * sets the center of the circle
	 * @param center of the circle
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	/*
	 * returns the circumference of the circle
	 * @return circumference of the circle
	 */
	public double getCircumference() {

		return 2 * Math.PI * this.radius;

	}
	
	/*
	 *  Returns whether the given point lies inside this circle.
	 *  @return whether the given point lies inside this circle.
	 */

	public boolean contains(Point p) {

		return this.center.distance(p) <= this.radius;

	}
	/*
	 *  Returns a text representation of this circle, such as(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	// "Circle{center=(40, 100),radius=100}".

	public String toString() {
		return "center=" + this.center + "color"+ this.color;
	}
	
	/*
	 *  Draws this Circle onto a DrawingPanel.
	 *  @param g the graphics of the panel
	 */
    public void draw(Graphics g) {
    	Graphics2D g2 =(Graphics2D)g;
    	g2.setRenderingHint(
    		    RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    		g2.setRenderingHint(
    		    RenderingHints.KEY_TEXT_ANTIALIASING,
    		    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    		

    	
    	g2.setColor(Color.BLACK);
    	
    	g2.drawOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
    	
    	g2.setColor(color);
    	g2.fillOval((int)center.getX() - radius, (int)center.getY() - radius, 2 * radius, 2 * radius);
		
		
		
    }

	
    /*
     * adds a line to the circle
     * @param line to add to the circle
     */
	
	
	/*
	 * returns true if the circle contains the line that is passed to it, false otherwise
	 * @param a line to be checked if it is in the circle
	 * @return true if the circle contains the line that is passed to it, false otherwise
	 */
	

	/*
	 * this method gets the radius of the circle
	 * @return the radius of this circle
	 */
	public int getRadius() {
		
		return this.radius;
	}
	
	/*
	 * this method sets the centre of the circle 
	 * @param x is the x coordinate of the circle 
	 * @param y is the y coordinate of the circle 
	 */
	public void setCentre(int x,int y){
		this.center=new Point(x,y);
	}
	/*
	 * 
	/*
	 * returns an xml representation of the circle in form of a string
	 * @return an xml representation of the circle in form of a string
	 */
	public String toXML(int i) {
		String tab="";
		String tab1="";
		for(int j=0;j<i;j++){
			tab += "\t";
			if(j<i-1){
				tab1+="\t";
			}
		}
		String s="<circle>\n"+tab+"<x>"+center.getX()+"</x>\n"+tab+"<y>"+center.getY()+"</y>\n"+tab1+"</circle>";
		return s;
	}
	public void setColor(Color c){
		color =c;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}