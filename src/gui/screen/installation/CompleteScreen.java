package gui.screen.installation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.InstallationGUI;
import gui.MainGUI;
import gui.base.Program;
import gui.base.Screen;

public class CompleteScreen extends Screen {

	@Override
	public void create() {
		JLabel welcomeTxt = new JLabel("Installation Complete!");
		welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(20f));
		
		JPanel centerPanel = new JPanel();
		
		centerPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel descTxt = new JLabel("System has been installed successfully. Below you can see the operator PIN, its used for entering the garage as an operator.");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(descTxt, c);
		
		JLabel pinTxt = new JLabel("PIN (can be viewed in settings)");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(pinTxt, c);
		
		JTextField pinIn = new JTextField();
		
		pinIn.setEditable(false);
		pinIn.setText(bgm.genereateOperatorPIN());
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(pinIn, c);
		

		JPanel southPanel = new JPanel();
		
		JButton nextBtn = new JButton();
		nextBtn.setText("Finish");
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InstallationGUI.getInstance().setVisible(false);
				InstallationGUI.getInstance().dispose();
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		
		add(welcomeTxt, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

}
