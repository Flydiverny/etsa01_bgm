package gui.screen.main;

import gui.MainGUI;
import gui.base.Screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginScreen extends Screen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217673798999202074L;

	@Override
	public void create() {
		JPanel southPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		final JTextField jtfpassword = new JTextField();
		JLabel jlpassword = new JLabel("Password");
		JButton nextBtn = new JButton();
		nextBtn.setText("Login");
		
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new GridLayout(0,2));
		JLabel header = new JLabel("Login");
		header.setFont(header.getFont().deriveFont(20f));
		inputpanel.add(header);
		inputpanel.add(new JLabel());
		inputpanel.add(jlpassword);
		inputpanel.add(jtfpassword);
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(bgm.loginOperator(jtfpassword.getText())){
					MainGUI.getInstance().enableMenu();
					MainGUI.getInstance().setScreen(new MainScreen());
				}else{
					JOptionPane.showMessageDialog(LoginScreen.this, "Invalid password");
					return;
				}
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(nextBtn, BorderLayout.EAST);
		this.add(inputpanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
	}
}
