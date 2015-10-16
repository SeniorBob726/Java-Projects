import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/*
 * Represents the control panel in BalloonDraw.
 */
public class ControlPanel extends JPanel
{
	private DrawingPanel canvas;
	private JButton colorButton, colorDisplay, inflateBalloon; //, balloonButton;
	private JComboBox<String> chooseBalloonShape;

	public ControlPanel(DrawingPanel canvas)
	{
		this.canvas = canvas;

		colorButton = new JButton("Pick Color");
		colorButton.addActionListener(new ColorButtonListener());

		colorDisplay = new JButton();
		colorDisplay.setBackground(canvas.getColor());
		colorDisplay.setEnabled(false);

		//balloonButton = new JButton("Add Balloon");
		inflateBalloon = new JButton("Inflate Balloon");
		String[] balloonShapeNames = {"Round", "Oval", "Square", "Fancy"};
		chooseBalloonShape = new JComboBox<String>(balloonShapeNames);
		//balloonButton.addActionListener(new BalloonButtonListener());
		inflateBalloon.addActionListener(new InflateButtonListener());
		chooseBalloonShape.addActionListener(new BalloonButtonListener());

		add(colorButton);
		add(colorDisplay);
		//add(balloonButton);
		add(inflateBalloon);
		add(chooseBalloonShape);
	}

	private class ColorButtonListener
			implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			canvas.pickColor();
			colorDisplay.setBackground(canvas.getColor());
			canvas.requestFocus();
		}
	}

	private class BalloonButtonListener
			implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			canvas.addBalloon(chooseBalloonShape.getSelectedIndex() + 1);
			canvas.requestFocus();
		}
	}

	private class InflateButtonListener
			implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(canvas.getActiveBalloon() != null) {
				canvas.getActiveBalloon().inflate(100);
				canvas.repaint();
				canvas.requestFocus();
			}
		}
	}
}
