/**
 * @(#)Coin.java
 *
 *
 * @author 
 * @version 1.00 2015/5/19
 */

public class Coin {
	private final int HEADS = 1;
	private final int TAILS = 0;
	private int face;
	
	public Coin() {
		flip();
	}

	public void flip() {
		face = (int)(Math.random() * 2);
	}
	
	public boolean isHeads() {
		if(face == HEADS) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String retValue = "tails";
		if(face == HEADS) {
			retValue = "heads";
		}

		return "The coin is " + retValue;
	}
}