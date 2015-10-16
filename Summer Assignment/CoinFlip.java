import java.util.Scanner;

public class CoinFlip{
	public static void main(String[] args){
		int heads = 0;
		int tails = 0;
		for(int i = 0; i < 100; i++){ // Randomly distribute 100 values to both heads and tails
			if(Math.random() > 0.5){
				heads++;
			}
			else{
				tails++;
			}
		}
		System.out.print("Heads: " + heads + ", Tails: " + tails);
	}
}