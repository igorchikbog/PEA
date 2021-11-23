package hangman_package;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
public class HangmanUserPanel extends JPanel implements ActionListener{

		private static final long serialVersionUID = 1L;
		private JComboBox<String> cmBxUsernames;
		private JButton btnSubmit;
		private JButton btnCancel;
		private HangmanMainFrame frame;

		public HangmanUserPanel() {
			setBackground(new Color(255, 255, 255));
			setLayout(null);

			cmBxUsernames = new JComboBox<String>();
			cmBxUsernames.setBackground(new Color(255, 255, 255));
			cmBxUsernames.setFont(new Font("Calibri", Font.PLAIN, 13));
			cmBxUsernames.setBounds(51, 107, 192, 20);
			add(cmBxUsernames);

			JLabel lblSelectYourUsername = new JLabel("Select your username:");
			lblSelectYourUsername.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblSelectYourUsername.setBounds(51, 69, 192, 27);
			add(lblSelectYourUsername);

			btnSubmit = new JButton("Submit");
			btnSubmit.setForeground(new Color(245, 255, 250));
			btnSubmit.setBackground(new Color(174, 0, 255));
			btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 13));
			btnSubmit.setBounds(51, 138, 90, 23);
			add(btnSubmit);
			btnSubmit.addActionListener(this);

			btnCancel = new JButton("Cancel");
			btnCancel.setBackground(new Color(255, 255, 255));
			btnCancel.setFont(new Font("Calibri", Font.PLAIN, 13));
			btnCancel.setToolTipText("Don't see your username? Select here to enter.");
			btnCancel.addActionListener(this);
			btnCancel.setBounds(153, 138, 90, 23);
			add(btnCancel);

			JLabel lblNoUsername = new JLabel("Don't see your username? Press cancel.");
			lblNoUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNoUsername.setBounds(36, 171, 240, 27);
			add(lblNoUsername);

		}// HangmanUserNameDropDownPanel()

		public void setFrame(HangmanMainFrame frame) {
			this.frame = frame;
		}

		public String getUserName() {
			return null;
		}// getUserName()

		public void setUsernames(String[] usernames) {
			for (String username : usernames) {
				cmBxUsernames.addItem(username);
			}
		}// retrieveUserNames()

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSubmit) {
				this.setVisible(false);
				submit();
			} else if (e.getSource() == btnCancel) {
				this.setVisible(false);
				frame.displayEnterUsername();
			}
		}// actionPerformed(ActionEvent)

		private void submit() {
			frame.startGameAs(cmBxUsernames.getSelectedItem().toString());
			this.setVisible(false);
			
			if(frame.isGameInProgress()) {
				frame.toggleGame(true);
			}
		}// submit()
	}// HangmanUserNameDropDownPanel class


