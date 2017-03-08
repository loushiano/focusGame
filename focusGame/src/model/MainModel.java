
package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import view.GUI;

public class MainModel extends Observable {
	private View[][] view;
	private ArrayList<View> aV;
	public MainModel(){
		view = new View[8][8];
		aV = new ArrayList<View>();
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
	
		
	
	public static void main(String args[]){
		MainModel m = new MainModel();
		
		GUI g= new GUI();
		g.createTopology();
		m.setGui(g);
		m.setView();
		m.setOriginalTurns();
	}
	
	
}

