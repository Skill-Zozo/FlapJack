package flapjack.types;
public class BST_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<String, Integer> t = new BST<String, Integer>();
		t.put("Skill", 4);
		t.put("Ayoba", 3);
		t.put("Zinhle", 6);
		t.changeVal("Zinhle", 78);
		t.String();
	}
}
