/**
 * Implements a program with a pie chart
 * that shows interactive polling results for three candidates
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Poll extends JFrame {
	public Poll() {
		super("Vote for Dylan, Taha, or Shawn");

		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		PollDisplayPanel chart = new PollDisplayPanel("Dylan", "Taha", "Shawn");
		PollControlPanel controls = new PollControlPanel(chart);
		c.add(chart, BorderLayout.CENTER);
		c.add(controls, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Poll w = new Poll();
		w.setBounds(300, 300, 400, 400);
		w.setDefaultCloseOperation(EXIT_ON_CLOSE);
		w.setVisible(true);
	}
}

