import javax.swing.*;
import java.awt.*;

public class Multitask {
	private ArrayList<MiniGame> games;

	public static void main(String[] args) {
		JFrame arcade = new JFrame();
		arcade.setTitle("Multitask");
		arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = arcade.getContentPane();
		pane.setLayout(new GridLayout(games.length() / 2, games.length())); // Figure out math for GridLayout

		for(MiniGame game : games) {
			pane.add(game);
		}

		arcade.pack();
		arcade.setVisible(true);
		panel.requestFocus();
	}
}
