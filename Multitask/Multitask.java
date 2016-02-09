import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Multitask {
	public static void createPane(JFrame arcade, ArrayList<MiniGame> games) {
		Container pane = arcade.getContentPane();
		int rows = 1, cols = games.size();
		if(games.size() > 2) {
			rows = 2;
			cols /= 2;
		}
		pane.setLayout(new GridLayout(rows, cols));

		games.get(0).setFirstGame(true);

		for(MiniGame game : games) {
			pane.add(game);
		}

		JTextField text = new JTextField();
		pane.add(text);

		arcade.pack();
		arcade.setVisible(true);
	}

	public static void main(String[] args) {
		ArrayList<MiniGame> games = new ArrayList<MiniGame>();
		MiniGame game = new Balance();
		games.add(game);

		JFrame arcade = new JFrame("Multitask");
		arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createPane(arcade, games);
	}
}
