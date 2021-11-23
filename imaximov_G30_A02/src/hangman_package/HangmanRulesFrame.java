package hangman_package;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HangmanRulesFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public HangmanRulesFrame() throws HeadlessException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("INSTRUCTIONS");
		getContentPane().setLayout(null);
		setSize(445, 100);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 439, 200);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblStart = new JLabel("HANGMAN RULES:");
		lblStart.setBounds(10, 7, 419, 19);
		panel.add(lblStart);
		lblStart.setFont(new Font("Calibri", Font.BOLD, 15));
		lblStart.setHorizontalAlignment(SwingConstants.LEFT);

		JTextArea txtrSelectWhich = new JTextArea();
		txtrSelectWhich.setBounds(10, 30, 414, 100);
		panel.add(txtrSelectWhich);
		txtrSelectWhich.setBackground(Color.WHITE);
		txtrSelectWhich.setEditable(false);
		txtrSelectWhich.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtrSelectWhich.setText("- A random word will be selected and you may guess a single\r\n"
				+ "\tletter at a time; \r\n" + "- If you lose, the man will hang \r\n"
				+ "- You can make six mistakes before he hangs\r\n");
	}
}
