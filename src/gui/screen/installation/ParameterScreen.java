package gui.screen.installation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.InstallationGUI;
import gui.base.Screen;

public class ParameterScreen extends Screen {

	@Override
	public void create() {
		JLabel welcomeTxt = new JLabel("System Parameters");
		JLabel descTxt = new JLabel("To install the system we need some starting information.");
		
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
				InstallationGUI.getInstance().setScreen(new CompleteScreen());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		
		add(welcomeTxt, BorderLayout.NORTH);
		add(centerPanel(), BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	private JPanel centerPanel() {
		JPanel pane = new JPanel();
		
		GridBagConstraints c = new GridBagConstraints();
		pane.setLayout(new GridBagLayout());
		
		JLabel bicycles = new JLabel("Bicycle");	
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
		pane.add(bicycles,c);
				
		JTextField bicyclesInput = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
	    c.gridy = 0;
		pane.add(bicyclesInput,c);
		
		JLabel oppwd = new JLabel("Operator Password");
		c.gridx = 0;
	    c.gridy = 1;
		pane.add(oppwd,c);
		
		JLabel oppwdrep = new JLabel("Repeat Password");
		c.gridx = 0;
	    c.gridy = 2;
		pane.add(oppwdrep,c);
		
		return pane;
	}
	
}
