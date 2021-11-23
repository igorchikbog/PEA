package hangman_package;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class HangmanDrawerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int mistakes;

	public HangmanDrawerPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		mistakes = 6;
	}// HangmanPanel()

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}// setMistakes(int)

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawLine(24, 258, 24, 22);
		g.drawLine(24, 22, 128, 22);
		g.drawLine(128, 22, 128, 50);
		g.drawLine(10, 258, 40, 258);

		if (mistakes <= 5) {
			g.drawOval(105, 50, 50, 50);
		}

		if (mistakes <= 4) {
			g.drawLine(128, 100, 128, 189);
		}

		if (mistakes <= 3) {
			g.drawLine(128, 120, 95, 150);
		}

		if (mistakes <= 2) {
			g.drawLine(128, 120, 160, 150);
		}

		if (mistakes <= 1) {
			g.drawLine(128, 189, 95, 245);
		}

		if (mistakes <= 0) {
			g.drawLine(128, 189, 160, 245);
		}
	}// paintComponent()
}// HangmanDrawerPanel class
