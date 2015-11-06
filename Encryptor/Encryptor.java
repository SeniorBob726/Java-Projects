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

public class Encryptor extends JFrame implements ActionListener {
	private JTextArea original, ciphertext, key;
	private JButton caesarShift, vigenereCipher;

	// Constructor
	public Encryptor() {
		super("Encryptor");

		setJMenuBar(new EncryptorMenu(this));
		setupGui();

		original.setText("Type or paste your text here or load it from a file.");
		key.setText("Type or paste a key here for the Vigenere Cipher.");
		ciphertext.setText("Encrypted output");
	}

	public String getText() {
		return original.getText();
	}

	public void setText(String text) {
		original.setText(text);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vigenereCipher) {
			String keytext = key.getText().trim().toLowerCase();
			if(!keytext.matches("^[a-z]+$")) {
				key.setBackground(Color.RED);
			}
			else {
				key.setBackground(Color.WHITE);
				String text = original.getText();
				Cipher cipher = new Cipher(text.toLowerCase());
				ciphertext.setText(cipher.vigenereCipher(keytext));
			}
		}
		if(e.getSource() == caesarShift) {
			String text = original.getText();
			Cipher cipher = new Cipher(text.toLowerCase());
			ciphertext.setText(cipher.caesarShift(4));
		}
	}

	// **********************  GUI setup ********************************

	private void setupGui() {
		original = new JTextArea(10, 15);
		original.setLineWrap(true);
		original.setWrapStyleWord(true);
		JScrollPane originalPane = new JScrollPane(original,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		key = new JTextArea(10, 15);
		key.setEditable(true);
		key.setLineWrap(true);
		key.setWrapStyleWord(true);
		JScrollPane keyPane = new JScrollPane(key,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		ciphertext = new JTextArea(10, 30);
		ciphertext.setEditable(false);
		ciphertext.setBackground(Color.LIGHT_GRAY);
		ciphertext.setLineWrap(true);
		ciphertext.setWrapStyleWord(true);
		JScrollPane ciphertextPane = new JScrollPane(ciphertext,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		caesarShift = new JButton("Caesar Shift");
		caesarShift.addActionListener(this);
		vigenereCipher = new JButton("Vigenere Cipher");
		vigenereCipher.addActionListener(this);

		Box box1 = Box.createHorizontalBox();
		box1.add(originalPane);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(keyPane);

		Box box2 = Box.createVerticalBox();
		box2.add(ciphertextPane);

		Box box3 = Box.createHorizontalBox();
		box3.add(caesarShift);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(vigenereCipher);
		box1.add(box3);

		Box box4 = Box.createVerticalBox();
		box4.add(Box.createVerticalStrut(10));
		box4.add(box1);
		box4.add(Box.createVerticalStrut(10));
		box4.add(box2);
		box4.add(Box.createVerticalStrut(20));
		box4.add(box3);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(box4);
	}

	public static void main(String[] args) {
		Encryptor window = new Encryptor();
		window.setBounds(100, 100, 480, 480);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}