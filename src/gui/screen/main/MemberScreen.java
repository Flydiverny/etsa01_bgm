package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.base.Screen;
import interfaces.IMember;

public class MemberScreen extends Screen {
	
	private IMember member;
	
	public MemberScreen(IMember member) {
		super();
		this.member = member;
	}

	@Override
	public void create() {
		this.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Member Details for " + member.getSSN());
		title.setFont(title.getFont().deriveFont(20f));
		
		this.add(title, BorderLayout.NORTH);
		this.add(memberDetails(), BorderLayout.WEST);
		this.add(bicyleList(), BorderLayout.EAST);
	}
	
	private JPanel memberDetails() {
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(0,3));
		
		createField(pane, "Name", member.getName(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		createField(pane, "SSN", member.getSSN());
		
		createField(pane, "Phone", member.getPhone(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		createField(pane, "Addr", member.getAddress(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		return pane;
	}
	
	private void createField(JPanel p, String desc, String value) {
		createField(p, desc, value, null);
	}
	
	private void createField(JPanel p, String desc, String value, ActionListener callback) {
		p.add(new JLabel(desc));
		
		JTextField txtField = new JTextField();
		txtField.setText(value);
		txtField.setEditable(false);
		
		p.add(txtField);
		
		if(callback != null) {
			JButton editBtn = new JButton("Edit");
			editBtn.addActionListener(callback);
			p.add(editBtn);
		} else {
			p.add(new JLabel()); // Fill space in grid.
		}
	}
	
	private JPanel bicyleList() {
		//TODO Implement
		
		return new JPanel();
	}

}
