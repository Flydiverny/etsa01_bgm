package gui.screen.main;

import interfaces.IMember;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import gui.MainGUI;
import gui.base.Screen;

public class MemberListScreen extends Screen {

	private static final long serialVersionUID = 479327387994796388L;

	@Override
	public void create() {
		setLayout(new BorderLayout());	
		
		
		JButton selectButton = new JButton("Go to details page for selected member");
		
		
		final JTable table = new JTable(new TableModelBGM());
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MemberScreen(memberManager.getMember((String) table.getValueAt(table.getSelectedRow(), 0))));
				//System.out.println((String) table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		
		add(scrollPane, BorderLayout.CENTER);
		add(selectButton,BorderLayout.SOUTH);
		
	}
	private class TableModelBGM extends AbstractTableModel{

		private static final long serialVersionUID = 2083509527767319524L;
		String[] columnNames = {
				"Social Security Number",
	            "Name",
	            "Registered Bicycles",
	            "Enabled"
			};
		private Object[][] data = new Object[memberManager.amountOfMembers()][columnNames.length];
		
		private TableModelBGM(){
			//get all the datazzz
			List<IMember> memberlist = memberManager.listMembers();
			int i = 0;
			for(IMember m : memberlist){
				data[i][0] = m.getSSN();
				data[i][1] = m.getName();
				data[i][2] = m.amountOfBicycles();
				data[i][3] = !m.isDisabled();
				i++;
			}
		}

		public int getColumnCount() {
	        return columnNames.length;
	    }
	    public int getRowCount() {
	        return data.length;
	    }

	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	        return data[row][col];
	    }

	    public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
	}
}



