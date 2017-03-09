
package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import view.Circle;
import view.GUI;

public class MainModel extends Observable {
	private View[][] view;
	private ArrayList<View> aV;
	private Node head;
	private int turn=0;
	private Player p1,p2;
	public MainModel(){
		view = new View[8][8];
		aV = new ArrayList<View>();
		head =new Node(null,view);
		p1=new Player(0);
		p2=new Player(1);
	}
	public void setGui(GUI g) {
			addObserver(g);
			
		}
	public void setView(){
		
		for (int i=0;i<8;i++){
			
			for(int j=0;j<8;j++){
				
				if(j>=2 && i==0 && j<=5){
					view[i][j]=new View(i,j);
					setChanged();
					notifyObservers(view[i][j]);
					aV.add(view[i][j]);
				}else if(i>0 && i<7){
					if(j==0 && i>1 && i<6){
						view[i][j]=new View(i,j);
						setChanged();
						notifyObservers(view[i][j]);
						aV.add(view[i][j]);
					}
					if( j>=1 && j<7){
					view[i][j]=new View(i,j);
					setChanged();
					notifyObservers(view[i][j]);
					aV.add(view[i][j]);
					}
					if(j==7 && i>1 && i<6){
						view[i][j]=new View(i,j);
						setChanged();
						notifyObservers(view[i][j]);
						aV.add(view[i][j]);
					}
					
				}else if(i>=7 ){
					if(j>=2 && j<=5){
					view[i][j]=new View(i,j);
					setChanged();
					notifyObservers(view[i][j]);
					aV.add(view[i][j]);
					}
					
				}
				
				
				
			}
			
			
		}
		
	}
		public void setOriginalTurns(){
			int k=0;
			int t=0;
			int g=0;
			Random r = new Random();
			for(int i=1;i<7;i++){
				for (int j=1;j<7;j++){
					t=r.nextInt(2);
					if(t==0){
						k++;
					}else {
						g++;
					}
					if(k>18){
						t=1;
					}
					if (g>18){
						t=0;
					}
					
					view[i][j].setTurn(t);
					
				}
			}
			
		}
		public boolean GOAstep(int i, int j){
			if(view[i][j].isEmpty()){
				return false;
			}
			Circle c=view[i][j].getTop();
			setChanged();
			notifyObservers(view[i][j+1].getCircle());
			view[i][j+1].addCircle(c);
			setChanged();
			notifyObservers(view[i][j]);
			
			setChanged();
			notifyObservers(view[i][j+1]);
			return true;
			
		}
	
		public Node alphabetaAlgorithm(Node currentNode,int a,int b){
				createChildren(currentNode);
				int highestValue=-1000;
				for(Node n:currentNode.getChildren()){
					n.setHeuristic(minimax(n,a,b));
					if(highestValue < n.getHeuristic()){
						highestValue = n.getHeuristic();
						
					}
				}
				for(Node n:currentNode.getChildren()){
					if(n.getHeuristic()==highestValue){
						return n;
					}
					
				}
			return null;
			
		}
		public void playGame(){
			while(p1.getCaptured().size()<8 && p2.getCaptured().size()<8){
				head=alphabetaAlgorithm(head,Integer.MIN_VALUE,Integer.MAX_VALUE);
				setChanged();
				notifyObservers(head.getState());
			}
		}
		
			
		private int minimax(Node currentNode,int a,int b){
		
			if(isTerminal(currentNode)){
				if(turn==0){
					return heuristicOne(currentNode);
				}
				return heuristicTwo(currentNode);
			}
				if(turn==0){
				createChildren(currentNode);
				for(Node n:currentNode.getChildren()){
					a=max(a,minimax(n,a,b));
					if(a>=b){
						return a;
					}
				}
				turn=1;
				return a;
			}else {
				
				createChildren(currentNode);
				for(Node n:currentNode.getChildren()){
					b=min(a,minimax(n,a,b));
					if(a>=b){
						return b;
					}
				}
				turn=0;
				return b;
			}
			
				
			}
			
		//this is lb
		private int heuristicTwo(Node currentNode) {
			// TODO Auto-generated method stub
			return 0;
		}
		private void createChildren(Node currentNode) {
			// TODO Auto-generated method stub
			
		}
		private int heuristicOne(Node currentNode) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		public boolean isTerminal(Node currentNode){
			if(currentNode.getDepth()==4){
				return true;
			}else {
				return false;
			}
		}
		public int max(int first, int second){
			if(first > second)
				return first;
			return second;
		}
		
		public int min(int first, int second){
			if(first < second)
				return first;
			return second;
		}
		
		
	
	public static void main(String args[]){
		MainModel m = new MainModel();
		
		GUI g= new GUI();
		g.createTopology();
		m.setGui(g);
		m.setView();
		m.setOriginalTurns();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.playGame();
	}
	
	
}

