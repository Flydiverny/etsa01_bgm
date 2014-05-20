package gui.screen.main;

import interfaces.IMember;

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

public class CreateMemberScreen extends Screen {

	private static final long serialVersionUID = 8077241173028123090L;

	private JTextField name = new JTextField();
	private final JTextField ssn = new JTextField();
	private JTextField phone = new JTextField();
	private JTextField addr = new JTextField();
	
	@Override
	public void create() {

		JLabel header = new JLabel("Create new Member");
		header.setFont(header.getFont().deriveFont(20f));
		JPanel centerpanel = new JPanel();
				
		GridLayout gl = new GridLayout(5,2);
		JLabel jlName = new JLabel("Name");
		JLabel jlSSN = new JLabel("Social Security Number");
		JLabel jlPhone = new JLabel("Phone Nbr.");
		JLabel jlAddr = new JLabel("Addr");
		
		this.setLayout(new BorderLayout());
		
		centerpanel.setLayout(gl);
		centerpanel.add(jlName);
		centerpanel.add(name);
		
		centerpanel.add(jlSSN);
		centerpanel.add(ssn);
		
		centerpanel.add(jlPhone);
		centerpanel.add(phone);
		
		centerpanel.add(jlAddr);
		centerpanel.add(addr);
		
		JPanel southPanel = new JPanel();
		
		JButton cancelBtn = new JButton();
		cancelBtn.setText("Cancel");
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		
		JButton nextBtn = new JButton();
		nextBtn.setText("Save");
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.print(ssn.getText());
				
				if(!memberManager.validateSSN(ssn.getText())) {
					JOptionPane.showMessageDialog(CreateMemberScreen.this, "Invalid Socical Security Number, make sure your SSN follows the format YYYYMMDDXXXX");
					return;
				}
				
				if(!memberManager.createMember(name.getText(), addr.getText(), phone.getText(), ssn.getText())) {
					JOptionPane.showMessageDialog(CreateMemberScreen.this, "A member with the entered Social Security Number already exists.");
				}
				
				MainGUI.getInstance().setScreen(new MemberScreen(memberManager.getMember(ssn.getText())));
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(nextBtn, BorderLayout.EAST);
		
		
		this.setLayout(new BorderLayout());
		
		this.add(header, BorderLayout.NORTH);
		this.add(centerpanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}
}
