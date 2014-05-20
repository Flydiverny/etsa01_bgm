package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainGUI;
import gui.base.Screen;
import gui.screen.installation.ParameterScreen;

public class OperatorParametersScreen extends Screen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8816124463919742645L;

	@Override
	public void create() {

		GridLayout gl = new GridLayout(5,2);
		final JLabel jloppin = new JLabel("Operator PIN is " + bgm.getOperatorPIN());
		JButton jboppingen = new JButton("Generate new PIN");
		JLabel jlcurpas = new JLabel("Current Password");
		JLabel jlnewpas1 = new JLabel("New Password");
		JLabel jlnewpas2 = new JLabel("Reenter New Password");
		
		final JTextField jtfnewpas1 = new JTextField();
		final JTextField jtfnewpas2 = new JTextField();
		final JTextField jtfcurpas = new JTextField();
		
		this.setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		JLabel header = new JLabel("System Parameters");
		header.setFont(header.getFont().deriveFont(20f));
		
		centerpanel.setLayout(gl);
		centerpanel.add(header);
		centerpanel.add(new JLabel());
		centerpanel.add(jloppin);
		centerpanel.add(jboppingen);
		centerpanel.add(jlcurpas);
		centerpanel.add(jtfcurpas);
		centerpanel.add(jlnewpas1);
		centerpanel.add(jtfnewpas1);
		centerpanel.add(jlnewpas2);
		centerpanel.add(jtfnewpas2);
		this.add(centerpanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		
		JButton cancelBtn = new JButton();
		cancelBtn.setText("Go Back");
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		
		JButton jbchangepassword = new JButton();
		jbchangepassword.setText("Confirm Password Change");
		
		jbchangepassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(!bgm.setOperatorPassword(jtfcurpas.getText(), jtfnewpas1.getText(), jtfnewpas2.getText())) {
					JOptionPane.showMessageDialog(OperatorParametersScreen.this, "Entered password(s) invalid, make sure the new passwords match and that the length is between 10 and 32 characters. Also make sure the current password is correctly entered.");
					return;
				}
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		
		
		
		jboppingen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(OperatorParametersScreen.this,"Are you sure you want to generate a new PIN to replace the old one?", "Are You Sure?", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
					jloppin.setText("Operator PIN is " + bgm.genereateOperatorPIN());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(jbchangepassword, BorderLayout.EAST);
		this.add(southPanel, BorderLayout.SOUTH);
		
	}

}
