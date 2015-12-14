public class PokerHand {
	private boolean straightFlush;
	private String flushSuit;
	private boolean straight;
	private int[] fourOfAKind;
	private int[] threeOfAKind;
	private int[] twoOfAKind;
	private Card highCard;

	public PokerHand(boolean sf, String fs, boolean s, int[] fourOAK, int[] threeOAK, int[] twoOAK, Card hc) {
		straightFlush = sf;
		flushSuit = fs;
		straight = s;
		fourOfAKind = fourOAK;
		threeOfAKind = threeOAK;
		twoOfAKind = twoOAK;
		highCard = hc;
	}

	public String toString() {
		String output = "";
		output += "Straight Flush: " + straightFlush + "\n";
		output += "Flush: " + flushSuit + "\n";
		output += "Straight: " + straight + "\n";

		output += "fourOfAKind: ";
		for(int i = 0; i < fourOfAKind.length; i++) {
			if(fourOfAKind[i] != 0) {
				output += fourOfAKind[i] + ", ";
			}
		}
		output += output.substring(0, output.length() - 2)) + "\n";

		output += "threeOfAKind: ";
		for(int i = 0; i < threeOfAKind.length; i++) {
			if(threeOfAKind[i] != 0) {
				output += threeOfAKind[i] + ", ";
			}
		}
		output += output.substring(0, output.length() - 2)) + "\n";

		output += "twoOfAKind: ";
		for(int i = 0; i < twoOfAKind.length; i++) {
			if(twoOfAKind[i] != 0) {
				output += twoOfAKind[i] + ", ";
			}
		}
		output += output.substring(0, output.length() - 2)) + "\n";

		output += "High Card: " + highCard + "\n";

		return output;
	}
}