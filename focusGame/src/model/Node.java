package model;

import java.util.HashSet;

public class Node {
	private Node parent;
	private HashSet<Node> children;
	public Node(Node p){
		setParent(p);
		children= new HashSet<Node>();
		
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
	
}
