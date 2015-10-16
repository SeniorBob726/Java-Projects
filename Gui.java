import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.drawRect(20, 40, 150, 45);

		g.setColor(Color.BLUE);
		g.drawString("Hello, World!", 55, 65);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("Graphics");

		window.setBounds(300, 300, 200, 150);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Gui panel = new Gui();
		panel.setBackground(Color.WHITE);
		Container c = window.getContentPane();
		c.add(panel);

		window.setVisible(true);
	}
}