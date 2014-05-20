package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainGUI;
import gui.base.Screen;

public class MainScreen extends Screen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779944875318021304L;

	@Override
	public void create() {

		GridLayout gl = new GridLayout(0,2);
		JLabel jlmemam = new JLabel("Registered members");
		JLabel jlgarsiz = new JLabel("Amount of bicycles supported by garage");
		JLabel jlchebik = new JLabel("Checked in bicycles ");
		
		JLabel jlmemamval = new JLabel(String.valueOf(memberManager.amountOfMembers()));
		JLabel jlgarsizval = new JLabel(String.valueOf(bgm.getGarageSize()));
		JLabel jlchebikval = new JLabel(String.valueOf(bgm.getAmountOfCheckedInBicycles()));
		
		this.setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		JLabel header = new JLabel("Main Status Window");
		header.setFont(header.getFont().deriveFont(20f));
		
		centerpanel.setLayout(gl);
		centerpanel.add(header);
		centerpanel.add(new JLabel());
		centerpanel.add(jlmemam);
		centerpanel.add(jlmemamval);
		centerpanel.add(jlgarsiz);
		centerpanel.add(jlgarsizval);
		centerpanel.add(jlchebik);
		centerpanel.add(jlchebikval);
		this.add(centerpanel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		
		JButton jbrefresh = new JButton();
		jbrefresh.setText("Refresh");
		
		jbrefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		

		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(jbrefresh, BorderLayout.EAST);
		this.add(southPanel, BorderLayout.SOUTH);
	}

}
