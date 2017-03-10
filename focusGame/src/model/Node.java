package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class Node {
	private Node parent;
	private HashSet<Node> children;
	
	private View[][] state;
	private int depth=0;
	private int minMax;
	private int heuristics;
	public Node(Node p,View[][] state){
		setParent(p);
		children= new HashSet<Node>();
		this.setState(state);
		
		
	}
	
	public void addChild(Node n){
		children.add(n);
	}
	

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	

	
	public int getMinMax() {
		return minMax;
	}

	public void setMinMax(int minMax) {
		this.minMax = minMax;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public HashSet<Node> getChildren() {
		// TODO Auto-generated method stub
		return children;
	}

	public View[][] getState() {
		return state;
	}

	public void setState(View[][] state) {
		this.state = state;
	}

	public void setHeuristic(int minimax) {
		// TODO Auto-generated method stub
		
	}

	public int getHeuristic() {
		return heuristics;
	}

	public void setHeuristics(int heuristics) {
		this.heuristics = heuristics;
	}

	public int getCapturedTwo() {
		int count=0;
		for(int e=0;e<64;e++){
			if(state[e/8][e%8]!=null){
			count+=state[e/8][e%8].getCapturedTwo();
			}
		}
		return count;
	}

	public int getCapturedOne() {
		int count=0;
		for(int e=0;e<64;e++){
			if(state[e/8][e%8]!=null){
			count+=state[e/8][e%8].getCapturedOne();
		}}
		return count;
	}

	public int countPieces() {
		int count=0;
		for(int e=0;e<64;e++){
			if(state[e/8][e%8]!=null){
			
				count+=state[e/8][e%8].getCircles().size();
		}}
		return count;
	}
	
}
