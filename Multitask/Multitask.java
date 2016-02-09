import javax.swing.*;
import java.awt.*;

public class Multitask {
	public static void main(String[] args) {
		JFrame arcade = new JFrame();
		arcade.setTitle("Multitask");
		arcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new UserPanel();

		Container pane = arcade.getContentPane();
		pane.setLayout(new GridLayout(1, 1));
		pane.add(panel);

		arcade.pack();
		arcade.setVisible(true);
		panel.requestFocus();
	}
}
