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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.InstallationGUI;
import gui.base.Screen;

public class ParameterScreen extends Screen {

	@Override
	public void create() {
		JLabel welcomeTxt = new JLabel("System Parameters");
		
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
				int bicycles = 0;
				
				try {
					bicycles = Integer.parseInt(bicyclesInput.getText());
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(ParameterScreen.this, "Invalid amount of bicycles");
					return;
				}
				
				if(!bgm.setOperatorPassword("", oppwdIn.getText(), oppwdrepIn.getText())) {
					JOptionPane.showMessageDialog(ParameterScreen.this, "Entered password is invalid, make sure they match and that the length is between 10 and 32 characters.");
					return;
				}
				
				bgm.setGarageSize(bicycles);
				
				InstallationGUI.getInstance().setScreen(new CompleteScreen());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		
		this.add(welcomeTxt, BorderLayout.NORTH);
		this.add(centerPanel(), BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	private JTextField bicyclesInput, oppwdIn, oppwdrepIn;
	
	private JPanel centerPanel() {
		JPanel pane = new JPanel();
		
		GridBagConstraints c = new GridBagConstraints();
		pane.setLayout(new GridBagLayout());

		JLabel info = new JLabel("To install the system we need some starting information.");	
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.weightx = 2;
		pane.add(info,c);
		
		JLabel bicycles = new JLabel("Number of bicycles allowed in garage");	
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.weightx = 1;
		pane.add(bicycles,c);
				
		bicyclesInput = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
	    c.gridy = 1;
		pane.add(bicyclesInput,c);
		
		JLabel oppwd = new JLabel("Operator Password");
		c.gridx = 0;
	    c.gridy = 2;
		pane.add(oppwd,c);
		
		oppwdIn = new JTextField();
		c.gridx = 1;
	    c.gridy = 2;
		pane.add(oppwdIn,c);
		
		JLabel oppwdrep = new JLabel("Repeat Password");
		c.gridx = 0;
	    c.gridy = 3;
		pane.add(oppwdrep,c);
		
		oppwdrepIn = new JTextField();
		c.gridx = 1;
	    c.gridy = 3;
		pane.add(oppwdrepIn,c);
		
		return pane;
	}
	
}
