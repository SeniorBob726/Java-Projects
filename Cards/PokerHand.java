public class PokerHand {
	private Boolean royalFlush;
	private Boolean straightFlush;
	private int[] fourOfAKind;
	private Boolean fullHouse;
	private String flushSuit;
	private Card straight;
	private int[] threeOfAKind;
	private int[] twoOfAKind;
	private Card highCard;

	public PokerHand(boolean rf, boolean sf, int[] fourOAK, boolean fh, String fs, Card s, int[] threeOAK, int[] twoOAK, Card hc) {
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

	private int flushCompare(String a, String b) {
		if(a == null && b == null) {
			return 0;
		}
		else if(a == null && b != null) {
			return 1;
		}
		else if(b == null && a != null) {
			return -1;
		}

		return 0;
	}

	private int straightCompare(Card a, Card b) {
		if(a == null && b == null) {
			return 0;
		}
		else if(a == null && b != null) {
			return -1;
		}
		else if(b == null && a != null) {
			return 1;
		}

		return a.compareTo(b);
	}

	private int pairCompare(int[] a, int[] b) {
		int aSize = 0;
		int bSize = 0;

		for(int i = 0; i < a.length; i++) {
			if(a[i] != 0) {
				aSize++;
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(b[i] != 0) {
				bSize++;
			}
		}

		if(aSize != bSize) {
			return aSize > bSize ? 1 : -1;
		}
		else {
			for(int i = 0; i < aSize; i++) {
				if(a[i] != b[i]) {
					return a[i] > b[i] ? 1 : -1;
				}
			}
		}

		return 0;
	}

	public int getHandValue() {
		if(royalFlush) {
			return 9;
		}
		if(straightFlush) {
			return 8;
		}
		int fourSize = 0;
		for(int i = 0; i < fourOfAKind.length; i++) {
			if(fourOfAKind[i] != 0) {
				fourSize++;
			}
		}
		if(fourSize > 0) {
			return 7;
		}
		if(fullHouse) {
			return 6;
		}
		if(flushSuit != null) {
			return 5;
		}
		if(straight != null) {
			return 4;
		}
		int threeSize = 0;
		for(int i = 0; i < threeOfAKind.length; i++) {
			if(threeOfAKind[i] != 0) {
				threeSize++;
			}
		}
		if(threeSize > 0) {
			return 3;
		}
		int twoSize = 0;
		for(int i = 0; i < twoOfAKind.length; i++) {
			if(twoOfAKind[i] != 0) {
				twoSize++;
			}
		}
		if(twoSize > 0) {
			return 2;
		}

		return 1;
	}

	public int compareTo(PokerHand pokerHand) {
		int hC = highCard.compareTo(pokerHand.highCard);
		int rF = royalFlush.compareTo(pokerHand.royalFlush);
		if(rF != 0 || royalFlush && pokerHand.royalFlush) { // A hand won from a Royal Flush or both hands have Royal Flushes
			return rF;
		}
		int sF = straightFlush.compareTo(pokerHand.straightFlush);
		if(sF != 0) { // A hand won from a Straight Flush
			return sF;
		}
		else if(straightFlush && pokerHand.straightFlush) { // Both hands have a Straight Flush
			return hC;
		}
		int fourOAK = pairCompare(fourOfAKind, pokerHand.fourOfAKind);
		if(fourOAK != 0) { // A hand won from a Four Of A Kind
			return fourOAK;
		}
		int fH = fullHouse.compareTo(pokerHand.fullHouse);
		int threeOAK = pairCompare(threeOfAKind, pokerHand.threeOfAKind);
		int twoOAK = pairCompare(twoOfAKind, pokerHand.twoOfAKind);
		if(fH != 0) { // A hand won from a Full House
			return fH;
		}
		else if(fullHouse && pokerHand.fullHouse) { // Both hands have a Full House
			if(threeOAK != 0) {
				return threeOAK;
			}
			else if(twoOAK != 0) {
				return twoOAK;
			}
		}
		int flush = flushCompare(flushSuit, pokerHand.flushSuit);
		if(flush != 0) { // A hand won from a Flush
			return flush;
		}
		int str = straightCompare(straight, pokerHand.straight);
		if(str != 0) { // A hand won from a Straight
			return str;
		}
		if(threeOAK != 0) { // A hand won from a Three Of A Kind
			return threeOAK;
		}
		if(twoOAK != 0) { // A hand won from a Two Of A Kind
			return twoOAK;
		}
		if(hC != 0) { // A hand won from a High Card
			return hC;
		}

		return 0;
	}

	public String printAllTests() {
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

	public String toString() {
		int value = getHandValue();
		if(value == 9) {
			return "Royal Flush of " + flushSuit;
		}
		if(value == 8) {
			return "Straight Flush with the " + straight + " as the top card";
		}
		if(value == 7) {
			return "Four Of A Kind: " + fourOfAKind[0] + "s";
		}
		if(value == 6) {
			return "Full House";
		}
		if(value == 5) {
			return "Flush of" + flushSuit;
		}
		if(value == 4) {
			return "Straight with the " + straight + " as the top card";
		}
		if(value == 3) {
			return "Three Of A Kind: " + threeOfAKind[0] + "s";
		}
		if(value == 2) {
			return "Two Of A Kind: " + twoOfAKind[0] + "s";
		}

		return "High Card of " + highCard;
	}
}