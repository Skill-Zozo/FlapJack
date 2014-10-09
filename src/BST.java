
public class BST<Key extends Comparable<Key>, Value> {
	private Node root; 
	private class Node {
		Node left;	//lower
		Node right;	//higher
		Key key;
		Value val;
		/*Node() {
		}*/
		Node (Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}	
	public Value get(Key key) {
		return get(root, key);
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
	
	public Node put(Node x, Key key, Value val) {
		if(x == null) {
			return new Node(key, val);
		} 
		int compare = key.compareTo(x.key);
		if(compare > 0) {
			Node y = put(x.right, key, val);
			y.left = x;
			//x.right = y;
			return y;
		} else if (compare < 0) {
			Node y = put(x.left, key, val);
			y.right = x;
			//x.left = y;
			return y;
		} else return x;
	}
	
	public void transverse(Node x) {
		if(x == null) {
			return;
		} 
		transverse(x.right); 
		System.out.println(x.key);
		transverse(x.left);
	}
	
	public void String() {
		transverse(root);
	}
}