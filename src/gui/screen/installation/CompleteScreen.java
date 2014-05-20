package gui.screen.installation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.InstallationGUI;
import gui.MainGUI;
import gui.base.Program;
import gui.base.Screen;

public class CompleteScreen extends Screen {

	@Override
	public void create() {
		JLabel welcomeTxt = new JLabel("Installation Complete!");
		JLabel descTxt = new JLabel("Have some herpa derpa derp info");
		welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(20f));
		
		JPanel southPanel = new JPanel();
		
		
		JButton nextBtn = new JButton();
		nextBtn.setText("Finish");
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InstallationGUI.getInstance().setVisible(false);
				InstallationGUI.getInstance().dispose();
				
				new MainGUI(Program.getManager());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		
		add(welcomeTxt, BorderLayout.NORTH);
		add(descTxt, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

}
