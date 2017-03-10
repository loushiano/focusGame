
package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Random;

import view.Circle;
import view.GUI;

public class MainModel extends Observable {
	private View[][] view;
	
	private Node head;
	private int turn=0;
	private Player p1,p2;
	private HashSet<String> strings; 
	public MainModel(){
		view = new View[8][8];
		
		head =new Node(null,view);
		p1=new Player(0);
		p2=new Player(1);
		strings=new HashSet<String>();
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
					
				}else if(i>0 && i<7){
					if(j==0 && i>1 && i<6){
						view[i][j]=new View(i,j);
						setChanged();
						notifyObservers(view[i][j]);
					
					}
					if( j>=1 && j<7){
					view[i][j]=new View(i,j);
					setChanged();
					notifyObservers(view[i][j]);
				
					}
					if(j==7 && i>1 && i<6){
						view[i][j]=new View(i,j);
						setChanged();
						notifyObservers(view[i][j]);
						
					}
					
				}else if(i>=7 ){
					if(j>=2 && j<=5){
					view[i][j]=new View(i,j);
					setChanged();
					notifyObservers(view[i][j]);
					
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
			setChanged();
			notifyObservers(view);
			
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
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	
		public Node alphabetaAlgorithm(Node currentNode,int a,int b){
				
				createChildren(currentNode,turn);
				//System.out.println(currentNode.getChildren().size());
				int highestValue=-1000;
				for(Node n:currentNode.getChildren()){
					//System.out.println(n.getCapturedOne());
					n.setHeuristic(minimax(n,a,b,turn));
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
			int i=0;
			while(head.getCapturedTwo()<5 && head.getCapturedOne()<5){
				
				head=alphabetaAlgorithm(head,Integer.MIN_VALUE,Integer.MAX_VALUE);
				head.setDepth(0);
				head.getChildren().clear();
				
				
				setChanged();
				notifyObservers(head.getState());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(turn==0){
					turn=1;
				}else{
					turn =0;
				}
				i++;
			}
			System.out.println("done");
			setChanged();
			notifyObservers("player one has won the game");
			
		}
		
			
		private int minimax(Node currentNode,int a,int b,int turn){
			
			if(isTerminal(currentNode)){
				if(turn==0){
					return heuristicOne(currentNode);
				}
				return heuristicTwo(currentNode);
			}
				if(turn==0){
					int v = Integer.MIN_VALUE;
				createChildren(currentNode,0);
				for(Node n:currentNode.getChildren()){
					v=max(v,minimax(n,a,b,1));
					a=max(a,v);
					if(b<=a){
						break;
					}
				}
				
				return v;
			}else {
				int v=Integer.MAX_VALUE;
				createChildren(currentNode,1);
				for(Node n:currentNode.getChildren()){
					v=min(v,minimax(n,a,b,0));
					b=min(b,v);
					if(b<=a){
						break;
					}
				}
				
				return v;
			}
			
				
			}
			
		//this is lb
		private int heuristicTwo(Node currentNode) {
			return countGREEN(currentNode.getState());
				
				
			
		}
		private int countRED(View[][] state) {
			int count=0;
			for(int i=0;i<64;i++){
				if(state[i/8][i%8]!=null){
				if(state[i/8][i%8].getCircle().getColor()==Color.RED){
					count++;
				}
				}
			}
			return count;
		}
		private int countGREEN(View[][] state) {
				int count=0;
			for(int i=0;i<64;i++){
				if(state[i/8][i%8]!=null){
				if(state[i/8][i%8].getCircle().getColor()==Color.GREEN){
					count++;
				}
				}
			}
			return count;
		}
		private void createChildren(Node currentNode,int t) {
			
			if(t==0){
				int i=0;
				int j=0;
				for(int e=0;e<64;e++){
						i=e/8;
						j=e%8;
						if(currentNode.getState()[i][j]!=null){
							if(currentNode.getState()[i][j].getCircle().getColor()==Color.GREEN){
								for(int k=0;k<4;k++){
									
								
								for(int size=1;size<=currentNode.getState()[i][j].getCircles().size();size++){
									View[][] copy =copyThat(currentNode.getState());
									
									ArrayList<Circle> c=copy[i][j].getCirclesCopy();
								if(j<8-size&&copy[i][j+size]!=null && k==0){
									
								copy[i][j+size].addCircle(size,c);
								
								Node child= new Node(currentNode,copy);
								child.getState()[i][j].setCircles(c);
								child.setDepth(currentNode.getDepth()+1);
								
								
								
								currentNode.addChild(child);
								
																							
								}else
								if(j>=size&&copy[i][j-size]!=null && k==1){
									copy[i][j-size].addCircle(size,c);
									
									Node child= new Node(currentNode,copy);
									child.setDepth(currentNode.getDepth()+1);
									
									child.getState()[i][j].setCircles(c);
									
									
									currentNode.addChild(child);
									
									
									}else
								if(i<8-size&&copy[i+size][j]!=null && k==2){
									copy[i+size][j].addCircle(size,c);
									
									Node child= new Node(currentNode,copy);
									child.setDepth(currentNode.getDepth()+1);
									
									child.getState()[i][j].setCircles(c);
									
									
									currentNode.addChild(child);
								
									}else
								if(i>=size&&copy[i-size][j]!=null && k==3){
									copy[i-size][j].addCircle(size,c);
									
									Node child= new Node(currentNode,copy);
									child.setDepth(currentNode.getDepth()+1);
									
									child.getState()[i][j].setCircles(c);
									
									
									currentNode.addChild(child);
									
									
									
									}
								}
								
							}
						}
						}
				}
			}else {
				int i=0;
				int j=0;
				for(int e=0;e<64;e++){
							i=e/8;
							j=e%8;
						if(currentNode.getState()[i][j]!=null){
							
							if(currentNode.getState()[i][j].getCircle().getColor()==Color.RED){
								
								for(int k=0;k<4;k++){
								
								for(int size=1;size<=currentNode.getState()[i][j].getCircles().size();size++){
									View[][] copy =copyThat(currentNode.getState());
									ArrayList<Circle> c=copy[i][j].getCirclesCopy();
								if(j<8-size&&copy[i][j+size]!=null && k==0){
								copy[i][j+size].addCircle(size,c);
								//System.out.println(copy[i][j+size].getCapturedOne());
								Node child= new Node(currentNode,copy);
								child.setDepth(currentNode.getDepth()+1);
								child.getState()[i][j].setCircles(c);
								
								
								
									
								currentNode.addChild(child);
								
								
								}else 
								if(j>=size&&copy[i][j-size]!=null && k==1){
									copy[i][j-size].addCircle(size,c);
									//System.out.println(copy[i][j-size].getCapturedOne());
									Node child= new Node(currentNode,copy);
									
									child.setDepth(currentNode.getDepth()+1);
									child.getState()[i][j].setCircles(c);
									
									
									currentNode.addChild(child);
									
							}else
								if(i<8-size&&copy[i+size][j]!=null && k==2){
									copy[i+size][j].addCircle(size,c);
									//System.out.println("is: "+copy[i+size][j].getCapturedTwo());
									Node child= new Node(currentNode,copy);
									child.setDepth(currentNode.getDepth()+1);
									
									child.getState()[i][j].setCircles(c);
								
								
									currentNode.addChild(child);
								
								
									}else
								if(i>=size&&copy[i-size][j]!=null && k==3){
									copy[i-size][j].addCircle(size,c);
									//System.out.println(copy[i-size][j].getCapturedOne());
									Node child= new Node(currentNode,copy);
									child.setDepth(currentNode.getDepth()+1);
									
									child.getState()[i][j].setCircles(c);
									
								
									currentNode.addChild(child);
																
								}
								}
								}
								
							}
						}
					}
				
				
			}
			
			
		}
		private View[][] copyThat(View[][] state) {
			View[][] v= new View[8][8];
			int i=0;
			int j=0;
			for(int e=0;e<64;e++){
				i=e/8;
				j=e%8;
					if(state[i][j]!=null){
						v[i][j] = new View(state[i][j]);
					}
				
				
			}
			
			return v;
		}
		private int heuristicOne(Node currentNode) {
			
			return currentNode.getCapturedTwo()*100+countGREEN(currentNode.getState())+stacksOfGreen(currentNode.getState());
			
		}
		
		
		private int stacksOfGreen(View[][] state) {
			int count=0;
			for(int i=0;i<64;i++){
				if(state[i/8][i%8]!=null){
				if(state[i/8][i%8].getCircle().getColor()==Color.GREEN &&state[i/8][i%8].getCircles().size()>1){
					count++;
				}
				}
			}
			return count;
			
		}
		public boolean isTerminal(Node currentNode){
			if(currentNode.getDepth()==2){
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
	public String toString(View[][] state){
		String s="";
		int i=0;
		int j=0;
		for(int e=0;e<64;e++){
			i=e/8;
			j=e%8;
				if(state[i][j]!=null){
					s+=state[i][j].toString();
				}
			
		}
		return s;
	}
	public void addString(String s){
		strings.add(s);
	}
	
	
}

