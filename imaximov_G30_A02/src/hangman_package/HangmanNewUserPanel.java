package hangman_package;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HangmanNewUserPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField fldUsername;
	private JButton btnEnter;
	private JButton btnCancelEnterUsername;
	private JLabel lblEnterUsername;
	private JLabel lblEnterUsernameCancel;
	private HangmanMainFrame frame;

	public HangmanNewUserPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		fldUsername = new JTextField();
		fldUsername.setBounds(80, 92, 203, 20);
		add(fldUsername);
		fldUsername.setColumns(10);

		btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnEnter.setForeground(new Color(245, 255, 250));
		btnEnter.setBackground(new Color(174, 0, 255));
		btnEnter.setBounds(80, 123, 89, 23);
		add(btnEnter);
		btnEnter.addActionListener(this);

		btnCancelEnterUsername = new JButton("Cancel");
		btnCancelEnterUsername.setToolTipText("Press if you have played before");
		btnCancelEnterUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnCancelEnterUsername.setBackground(new Color(255, 255, 255));
		btnCancelEnterUsername.setBounds(194, 123, 89, 23);
		add(btnCancelEnterUsername);
		btnCancelEnterUsername.addActionListener(this);

		lblEnterUsername = new JLabel("Enter a unique username:");
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEnterUsername.setBounds(78, 57, 203, 23);
		add(lblEnterUsername);

		lblEnterUsernameCancel = new JLabel("Press cancel to select a username from a drop down list.");
		lblEnterUsernameCancel.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblEnterUsernameCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsernameCancel.setBounds(0, 166, 370, 23);
		add(lblEnterUsernameCancel);

	}// HangmanNewUserPanel()

	public void setFrame(HangmanMainFrame frame) {
		this.frame = frame;
	}// setFrame()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnter) {
			if (frame.enterUsername(fldUsername.getText()))
				this.setVisible(false);
		} else if (e.getSource() == btnCancelEnterUsername) {
			this.setVisible(false);
			frame.displayUserDropDown();
		}
	}// actionPerformed(ActionEvent)

}// HangmanEnterUsername class
