package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.View;

/*
 * This class GUI is responsible for representing user interface of the network topology.
 * @author Ahmad Ayyoub
 */
public class GUI implements Observer{
	private JFrame frame; // The fame

	private Container contentPane; // Content Pane
	private JPanel rightPanel; //South Panel of the content Pane
	private JPanel southPanel; //North Panel of the content Pane
	private CirclePanel circlePanel;//a panel in which we will draw shapes


	/*
	 * This is the constructor for running the GUI for the network TOpology
	 * @param controler that listens to this view
	 */
	public GUI(){
		//Create the fame with specific features
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setFrame(new JFrame("Network Topology"));
		//getFrame().setPreferredSize(new Dimension(1200, 700));
		
		circlePanel=new CirclePanel();
		
		
		getFrame().setResizable(true);
		}
		/*
		 * creates the topology based on a user entry, with all the nodes and connections
		 */
		public void createTopology(){
		
		
		frame.setContentPane(circlePanel);
		//Get the content pane from the frame.
		contentPane = frame.getContentPane();
		
		
		frame.pack();
		
		
		//Set the layout manage of the North Panel to Border Layout and add the area that represents the Topology to the panel.
		/*JScrollPane pane1 =
	            new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane1.setPreferredSize(new Dimension(200,200));
		*/
		
		
		
        //add a mouse listener to the content pane which is the controler
		
		
		frame.setVisible(true);
		frame.pack();
		frame.setSize(new Dimension(1500,1500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		/*
		 * closes the application
		 */
	public void close() {
		getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
		
	}
	/*
	 * returns the frame of gui
	 * @return the frame of gui
	 */
	public JFrame getFrame() {
		return frame;
	}
	/*
	 * sets the frame of the gui
	 * @param frame to be set to the frame of this class
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		View v = (View)arg1;
		drawCircle(v.getCircle());
	}
	
	
	
	
	
	
	public void resetGui() {
		
		circlePanel.getCircles().clear();
		
		circlePanel.draw();
		
	}
	
	
	
	
	
	
	public void drawCircle(Circle c){
		
		
		circlePanel.addCircle(c);
		circlePanel.draw();
		
	}
	
}
