package gui.screen.installation;

import gui.InstallationGUI;
import gui.base.Screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreen extends Screen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5807177214221954390L;

	@Override
	public void create() {
		JLabel welcomeTxt = new JLabel("Welcome to Bicycle Garage Management");
		JLabel descTxt = new JLabel("This installation wizard will guide you through the setup of the magic bicycle management system.");
		welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(20f));
		JPanel southPanel = new JPanel();
		
		JButton cancelBtn = new JButton();
		cancelBtn.setText("Cancel");
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Runtime.getRuntime().halt(0); // Exit without saving plox
			}
		});
		
		JButton nextBtn = new JButton();
		nextBtn.setText("Next");
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InstallationGUI.getInstance().setScreen(new ParameterScreen());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		
		add(welcomeTxt, BorderLayout.NORTH);
		add(descTxt, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
}
