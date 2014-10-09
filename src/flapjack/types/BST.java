package flapjack.types;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root; 
	private class Node {
		Node left;	//lower
		Node right;	//higher
		Key key;
		Value val;
		Node (Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}	
	public Value get(Key key) {
		return get(root, key);
	}
	
	public void changeVal(Key key, Value val ) {
		Node s = findNode(root,key);
		s.val = val;
	}
	
	private Value get(Node x, Key key) {
		if(x == null) {
			return null;
		}
		int compare = key.compareTo(x.key);
		if(compare > 0) {
			return get(x.right,key);
		} else if(compare < 0) {
			return get(x.left, key);
		} else {
			return x.val;
		}
	}
	 
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		if(x == null) {
			return new Node(key, val);
		} 
		int compare = key.compareTo(x.key);
		if(compare > 0) {
			x.right = put(x.right, key, val);
		} else if (compare < 0) {
			x.left = put(x.left, key, val);
		} else {
			x.val = val;
		}
		return x;
	}
	
	private Node findNode(Node x, Key key) {
		if(key.compareTo(x.key) == 0) {
			return x;
		} else if (key.compareTo(x.key) > 0) return findNode(x.right, key);
		else return findNode(x.left,key);
	}
	
	public void transverse(Node x) {
		if(x == null) {
			return;
		} 
		transverse(x.left);
		System.out.println(x.val);
		transverse(x.right);
	}
	
	public void String() {
		transverse(root);
	}
}