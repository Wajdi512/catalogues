package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	
	public InfoDialog(String title, String message) {
		setResizable(false);
		setTitle(title);
		setBounds(100, 100, 318, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel(message);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel);
		}
		
		JButton btnNewButton = new JButton("Fermer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
	}


}
