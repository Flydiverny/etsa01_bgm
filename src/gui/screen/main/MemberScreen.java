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
		
		createField(pane, "SSN", member.getSSN());		

		createField(pane, "Name", member.getName(), new EditCallback() {
			@Override
			public void Edit(String newValue) {
				member.setName(newValue);
			}
		});
		
		createField(pane, "Phone", member.getPhone(), new EditCallback() {
			@Override
			public void Edit(String newValue) {
				member.setPhone(newValue);
			}
		});
		
		createField(pane, "Addr", member.getAddress(), new EditCallback() {
			@Override
			public void Edit(String newValue) {
				member.setAddress(newValue);
			}
		});
		
		pane.add(new JLabel("Member Status"));
		final JTextField memberStatus = new JTextField();
		memberStatus.setText((member.isDisabled() ? "Disabled" : "Enabled"));
		memberStatus.setEditable(false);
		pane.add(memberStatus);
		
		final JButton memberStatusToggle = new JButton((member.isDisabled() ? "Enable" : "Disable"));
		
		memberStatusToggle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				member.enable(member.isDisabled());
				memberStatus.setText((member.isDisabled() ? "Disabled" : "Enabled"));
				memberStatusToggle.setText((member.isDisabled() ? "Enable" : "Disable"));
			}
		});
		
		pane.add(memberStatusToggle);
		
		createField(pane, "Amount of Bicycles", String.valueOf(member.amountOfBicycles()));
		createField(pane, "Monthly Fee", String.valueOf(bgm.getPaymentInfo(member)));
		
		return pane;
	}
	
	private void createField(JPanel p, String desc, String value) {
		createField(p, desc, value, null);
	}
	
	private void createField(JPanel p, String desc, String value, final EditCallback callback) {
		p.add(new JLabel(desc));
		
		final JTextField txtField = new JTextField();
		txtField.setText(value);
		txtField.setEditable(false);
		
		p.add(txtField);
		
		if(callback != null) {
			final JButton editBtn = new JButton("Edit");
			
			ActionListener edit = new ActionListener() {
				boolean editing = false;
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(editing) {
						editBtn.setText("Edit");
						txtField.setEditable(false);
						callback.Edit(txtField.getText());
						editing = false;
					} else {
						editBtn.setText("Save");
						txtField.setEditable(true);
						editing = true;
					}
				}
			};
			
			editBtn.addActionListener(edit);
			
			p.add(editBtn);
		} else {
			p.add(new JLabel()); // Fill space in grid.
		}
	}
	
	private JPanel bicyleList() {
		//TODO Implement
		
		return new JPanel();
	}
	
	private interface EditCallback {
		public void Edit(String newValue);
	}
}
