package gui.screen.main;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.base.Screen;

public class MemberListScreen extends Screen {

	private static final long serialVersionUID = 479327387994796388L;

	@Override
	public void create() {
		setLayout(new BorderLayout());	
		String[] columnNames = {
									"Social Security Number",
					                "Name",
					                "Registered Bicycles",
					                "Checked In Bicycles",
					                "Enabled"
            					};
		Object[][] data = new Object[memberManager.amountOfMembers()][5];
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		add(scrollPane, BorderLayout.CENTER);
		
	}

}
