import javax.swing.*;
import java.awt.*;

public class Multitask {
	public static void main(String[] args) {
		JFrame arcade = new JFrame("Multitask");
		arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = arcade.getContentPane();
		pane.setLayout(new GridLayout(1, 1));

		JPanel game = new GameHandler();
		pane.add(game);

		arcade.pack();
		arcade.setVisible(true);
	}
}
