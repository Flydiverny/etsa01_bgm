package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainGUI;
import gui.base.Screen;

public class SystemParametersScreen extends Screen {
	private static final long serialVersionUID = 8791422727411899812L;
	private Integer[] dooropentime = new  Integer[30-4];
	@Override
	public void create() {		
		for(int i = 0; i<dooropentime.length;i++){
			dooropentime[i] = i+5;
		}
		
		GridLayout gl = new GridLayout(5,2);
		JLabel jlnumofbikes = new JLabel("Number of bicycles allowed in garage");
		JLabel jldooropentime = new JLabel("Seconds for door to be unlocked");
		JLabel jlmonthlyfee = new JLabel("Monthly fee");
		JLabel jlbikefee = new JLabel("Bike fee");
		
		final JTextField jtfmonthlyfee = new JTextField(String.valueOf(bgm.getMonthlyFee()));
		final JTextField jtfbikefee = new JTextField(String.valueOf(bgm.getBikeFee()));
		final JTextField jtfnumofbikes = new JTextField(String.valueOf(bgm.getGarageSize()));
		JComboBox<Integer> jcbdooropentime = new JComboBox<Integer>(dooropentime);
		jcbdooropentime.setSelectedIndex(bgm.getUnlockDuration()-5);
		
		this.setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		JLabel header = new JLabel("System Parameters");
		header.setFont(header.getFont().deriveFont(20f));
		
		centerpanel.setLayout(gl);
		centerpanel.add(header);
		centerpanel.add(new JLabel());
		centerpanel.add(jlnumofbikes);
		centerpanel.add(jtfnumofbikes);
		centerpanel.add(jldooropentime);
		centerpanel.add(jcbdooropentime);
		centerpanel.add(jlmonthlyfee);
		centerpanel.add(jtfmonthlyfee);
		centerpanel.add(jlbikefee);
		centerpanel.add(jtfbikefee);
		this.add(centerpanel, BorderLayout.NORTH);
		
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
				int bicycles = 0;
				
				try {
					bicycles = Integer.parseInt(jtfnumofbikes.getText());
					if(bicycles<bgm.getAmountOfCheckedInBicycles()){
						JOptionPane.showMessageDialog(SystemParametersScreen.this, "Too few bicycles, garage already has more than the specified amount of bicycles checked in!");
						return;
					}	
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SystemParametersScreen.this, "Invalid amount of bicycles");
					return;
				}
				
				
				
				int monthly = 0;
				
				try {
					monthly = Integer.parseInt(jtfmonthlyfee.getText());
					if(monthly<0){
						JOptionPane.showMessageDialog(SystemParametersScreen.this, "Only positive payment values are valid");
						return;
					}	
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SystemParametersScreen.this, "Invalid monthly payment");
					return;
				}
				
				
				
				int bikefee = 0;
				
				try {
					bikefee = Integer.parseInt(jtfbikefee.getText());
					if(bikefee<0){
						JOptionPane.showMessageDialog(SystemParametersScreen.this, "Only positive payment values are valid");
						return;
					}	
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(SystemParametersScreen.this, "Invalid bike payment");
					return;
				}
				
				bgm.setGarageSize(bicycles);
				bgm.setBikeFee(bikefee);
				bgm.setMonthlyFee(monthly);
				
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(cancelBtn, BorderLayout.WEST);
		southPanel.add(nextBtn, BorderLayout.EAST);
		this.add(southPanel, BorderLayout.SOUTH);
	}

}
