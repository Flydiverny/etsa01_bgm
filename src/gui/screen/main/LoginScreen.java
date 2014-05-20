package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainGUI;
import gui.base.Screen;

public class LoginScreen extends Screen {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		JPanel southPanel = new JPanel();
		
		final JTextField jtfpassword = new JTextField();
		JLabel jlpassword = new JLabel("Password");
		JButton nextBtn = new JButton();
		nextBtn.setText("Login");
		
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new BorderLayout());
		inputpanel.add(jlpassword, BorderLayout.WEST);
		inputpanel.add(jtfpassword,BorderLayout.EAST);
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(bgm.loginOperator(jtfpassword.getText())){
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
