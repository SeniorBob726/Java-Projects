public class PokerHand {
	private Boolean royalFlush;
	private Boolean straightFlush;
	private int[] fourOfAKind;
	private Boolean fullHouse;
	private String flushSuit;
	private Boolean straight;
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

	private int suitCompare(String a, String b) {
		if(a == null && b != null) {
			return 1;
		}
		else if(b == null && a != null) {
			return -1;
		}

		return 0;
	}

	private int pairCompare(int[] a, int[] b) {
		int aSize = 0;
		int bSize = 0;
		int aSum = 0;
		int bSum = 0;

		for(int i = 0; i < a.length; i++) {
			if(a[i] != 0) {
				aSize++;
				aSum += a[i];
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(b[i] != 0) {
				bSize++;
				bSum += b[i];
			}
		}

		if(aSize != bSize) {
			return aSize > bSize ? 1 : -1;
		}
		else if(aSum != bSum) {
			return aSum > bSum ? 1 : -1;
		}

		return 0;
	}

	public int compareTo(PokerHand pokerHand) {
		if(royalFlush.compareTo(pokerHand.royalFlush) != 0) {
			return royalFlush.compareTo(pokerHand.royalFlush);
		}
		if(straightFlush.compareTo(pokerHand.straightFlush) != 0) {
			return straightFlush.compareTo(pokerHand.straightFlush);
		}
		if(pairCompare(fourOfAKind, pokerHand.fourOfAKind) != 0) {
			return pairCompare(fourOfAKind, pokerHand.fourOfAKind);
		}
		if(fullHouse.compareTo(pokerHand.fullHouse) != 0) {
			return fullHouse.compareTo(pokerHand.fullHouse);
		}
		if(suitCompare(flushSuit, pokerHand.flushSuit)) {
			return suitCompare(flushSuit, pokerHand.flushSuit);
		}
		if(straight.compareTo(pokerHand.straight) != 0) {
			return straight.compareTo(pokerHand.straight);
		}
		if(pairCompare(threeOfAKind, pokerHand.threeOfAKind) != 0) {
			return pairCompare(threeOfAKind, pokerHand.threeOfAKind);
		}
		if(pairCompare(twoOfAKind, pokerHand.twoOfAKind) != 0) {
			return pairCompare(twoOfAKind, pokerHand.twoOfAKind);
		}
		if(highCard.compareTo(pokerHand.highCard) != 0) {
			return highCard.compareTo(pokerHand.highCard);
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