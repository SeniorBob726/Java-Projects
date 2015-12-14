public class PokerHand {
	private boolean royalFlush;
	private boolean straightFlush;
	private int[] fourOfAKind;
	private boolean fullHouse;
	private String flushSuit;
	private boolean straight;
	private int[] threeOfAKind;
	private int[] twoOfAKind;
	private Card highCard;

	public PokerHand(boolean rf, boolean sf, int[] fourOAK, boolean fh, String fs, boolean s, int[] threeOAK, int[] twoOAK, Card hc) {
		royalFlush = rf;
		straightFlush = sf;
		fourOfAKind = fourOAK;
		fullHouse = fh;
		flushSuit = fs;
		straight = s;
		threeOfAKind = threeOAK;
		twoOfAKind = twoOAK;
		highCard = hc;
	}

	public int booleanCompare(boolean a, boolean b) {
		if(a && !b) {
			return 1;
		}
		else if(b && !a) {
			return -1;
		}

		return 0;
	}

	public int compareTo(PokerHand pokerHand) {
		int value = 0;
		if(booleanCompare(royalFlush, pokerHand.royalFlush) != 0) {
			return booleanCompare(royalFlush, pokerHand.royalFlush);
		}
		if(booleanCompare(straightFlush, pokerHand.straightFlush) != 0) {
			return booleanCompare(straightFlush, pokerHand.straightFlush);
		}

		return 0;
	}

	public String toString() {
		String output = "";
		output += "Royal Flush: " + royalFlush + "\n";
		output += "Straight Flush: " + straightFlush + "\n";
		output += "fourOfAKind: ";
		for(int i = 0; i < fourOfAKind.length; i++) {
			if(fourOfAKind[i] != 0) {
				output += fourOfAKind[i] + ", ";
			}
		}
		output = output.substring(0, output.length() - 2) + "\n";
		output += "Full House: " + fullHouse + "\n";
		output += "Flush: " + flushSuit + "\n";
		output += "Straight: " + straight + "\n";
		output += "threeOfAKind: ";
		for(int i = 0; i < threeOfAKind.length; i++) {
			if(threeOfAKind[i] != 0) {
				output += threeOfAKind[i] + ", ";
			}
		}
		output = output.substring(0, output.length() - 2) + "\n";
		output += "twoOfAKind: ";
		for(int i = 0; i < twoOfAKind.length; i++) {
			if(twoOfAKind[i] != 0) {
				output += twoOfAKind[i] + ", ";
			}
		}
		output = output.substring(0, output.length() - 2) + "\n";
		output += "High Card: " + highCard + "\n";

		return output;
	}
}