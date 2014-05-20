package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.base.Screen;

public class SystemParametersScreen extends Screen {
	private static final long serialVersionUID = 8791422727411899812L;
	private Integer[] dooropentime = new  Integer[30-5];
	@Override
	public void create() {		
		for(int i = 0; i<dooropentime.length;i++){
			dooropentime[i] = i+5;
		}
		
		GridLayout gl = new GridLayout(4,2);
		JLabel jlnumofbikes = new JLabel("Number of bicycles allowed in garage");
		JLabel jldooropentime = new JLabel("Seconds for door to be unlocked");
		JLabel jlmonthlyfee = new JLabel("Monthly fee");
		JLabel jlbikefee = new JLabel("Bike fee");
		
		Dimension d = new Dimension(75,20); 
		JTextField jtfmonthlyfee = new JTextField();
		jtfmonthlyfee.setSize(d);
		JTextField jtfbikefee = new JTextField();
		jtfbikefee.setSize(d);
		JTextField jtfnumofbikes = new JTextField();
		jtfnumofbikes.setSize(d);
		JComboBox<Integer> jcbdooropentime = new JComboBox<Integer>(dooropentime);
		
		this.setLayout(new BorderLayout());
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(gl);
		centerpanel.add(jlnumofbikes);
		centerpanel.add(jtfnumofbikes);
		this.add(centerpanel);
	}

}
