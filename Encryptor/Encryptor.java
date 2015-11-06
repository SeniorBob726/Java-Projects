import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Encryptor extends JFrame
implements ActionListener {
	private JTextArea original, marked;
	private JButton go;

	// Constructor
	public Encryptor() {
		super("Encryptor");

		setJMenuBar(new EncryptorMenu(this));
		setupGui();

		original.setText("Type or paste your text here or load it from a file");
		refresh();
	}

	public String getText() {
		return original.getText();
	}

	public void setText(String text) {
		original.setText(text);
	}

	public void refresh() {
		String text = original.getText();
		Cipher cipher = new Cipher(text.toLowerCase());
		marked.setText(cipher.caesarShift(4));
	}

	// Called when the Refresh burron is clicked
	public void actionPerformed(ActionEvent e) {
		refresh();
	}

	// **********************  GUI setup ********************************

	private void setupGui() {
		original = new JTextArea(10, 20);
		original.setLineWrap(true);
		original.setWrapStyleWord(true);
		JScrollPane originalPane = new JScrollPane(original,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		marked = new JTextArea(10, 20);
		marked.setEditable(false);
		marked.setBackground(Color.LIGHT_GRAY);
		marked.setLineWrap(true);
		marked.setWrapStyleWord(true);
		JScrollPane markedPane = new JScrollPane(marked,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		go = new JButton("Refresh");
		go.addActionListener(this);

		Box box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(10));
		box1.add(originalPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(markedPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(go);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(box1);
	}

	public static void main(String[] args) {
		Encryptor window = new Encryptor();
		window.setBounds(100, 100, 480, 480);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}