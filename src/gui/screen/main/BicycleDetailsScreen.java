package gui.screen.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import interfaces.IBicycle;
import interfaces.ILog;
import interfaces.IMember;
import gui.MainGUI;
import gui.base.Program;
import gui.base.Screen;




public class BicycleDetailsScreen extends Screen {
	private static final long serialVersionUID = 7343484603784490726L;
	private IBicycle bicycle;
	public BicycleDetailsScreen(IBicycle b){
		super();
		bicycle = b;
	}

	@Override
	public void create() {
		this.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Bicycle Details for " + bicycle.getBarcode());
		title.setFont(title.getFont().deriveFont(20f));
		
		this.add(title, BorderLayout.NORTH);
		this.add(bicycleDetails(), BorderLayout.CENTER);
		this.add(logList(), BorderLayout.EAST);
		this.add(southPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel bicycleDetails() {	
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(0,3));
		
		createField(pane, "Owner SSN", bicycle.getOwner().getSSN());		

		createField(pane, "Owner Name", bicycle.getOwner().getName());
		
		createField(pane, "Barcode", bicycle.getBarcode());
		
		createField(pane, "Registration Date", bicycle.getRegistrationDate().toString());
		
		createField(pane, "Description", bicycle.getDescription(), new EditCallback() {
			@Override
			public void Edit(String newValue, JTextField editedField) {
				if(newValue.equals("")){
					newValue = bicycle.getDescription();
					editedField.setText(newValue);
					JOptionPane.showMessageDialog(null, "Empty fields are not allowed");
				}
				bicycle.setDescription(newValue);
			}
		});
		
		addBicycleStatus(pane);
				
		return pane;
	}
	
	private JPanel southPanel() {
	
		JPanel pane = new JPanel();
		
		pane.setLayout(new BorderLayout());
		
		JButton delete = new JButton("Delete Bicycle");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(bicycle.isCheckedIn()) {
					JOptionPane.showMessageDialog(null, "Cannot remove a checked in bicycle.");
					return;
				}
				
				if(JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected bicycle?", "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					IMember owner = bicycle.getOwner();
					owner.removeBicycle(bicycle.getBarcode());
					JOptionPane.showMessageDialog(null,  "Bike was successfully deleted");
					MainGUI.getInstance().setScreen(new MemberScreen(owner));
				}
			}
		});
		JButton print = new JButton("Print Barcode");
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Program.getBGM().printBarcode(bicycle);
			}
		});
		
		JButton back = new JButton("View Owner");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MemberScreen(bicycle.getOwner()));
			}
		});
		
		pane.add(delete, BorderLayout.EAST);
		
		pane.add(back, BorderLayout.CENTER);
		pane.add(print, BorderLayout.WEST);
		
		return pane;
	}
	
	private void addBicycleStatus(JPanel pane) {
		pane.add(new JLabel("Status"));
		final JTextField bikeCheckedInStatus = new JTextField();
		bikeCheckedInStatus.setText(bicycle.isCheckedIn() ? "Checked In" : "Checked Out");
		bikeCheckedInStatus.setToolTipText((bicycle.isCheckedIn() ? "Checked In" : "Checked Out"));
		bikeCheckedInStatus.setEditable(false);
		pane.add(bikeCheckedInStatus);
		
		final JButton bikeCheckedInStatusToggle = new JButton(bicycle.isCheckedIn() ? "Check Out" : "Check In");
		
		bikeCheckedInStatusToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bicycle.setCheckedIn(!bicycle.isCheckedIn());
				bikeCheckedInStatus.setText(bicycle.isCheckedIn() ? "Checked In" : "Checked Out");
				bikeCheckedInStatus.setToolTipText((bicycle.isCheckedIn() ? "Checked In" : "Checked Out"));
				bikeCheckedInStatusToggle.setText(bicycle.isCheckedIn() ? "Check Out" : "Check In");
				MainGUI.getInstance().setScreen(new BicycleDetailsScreen(bicycle));
			}
		});
		
		pane.add(bikeCheckedInStatusToggle);
		
	}
	
	
	private void createField(JPanel p, String desc, String value) {
		createField(p, desc, value, null);
	}
	
	private void createField(JPanel p, String desc, String value, final EditCallback callback) {
		p.add(new JLabel(desc));
		
		final JTextField txtField = new JTextField();
		txtField.setText(value);
		txtField.setToolTipText(value);
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
						txtField.setToolTipText(txtField.getText());
						callback.Edit(txtField.getText(), txtField);
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
	
	private JPanel logList() {
		JPanel pane = new JPanel();
		
		pane.setLayout(new BorderLayout());	
				
		final TableModelBGM model = new TableModelBGM();
		final JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		
		
		
		pane.add(scrollPane, BorderLayout.CENTER);
		
		return pane;
	}
	
	private class TableModelBGM extends AbstractTableModel{

		private static final long serialVersionUID = 2083509527767319524L;
		
		private String[] columnNames = {
			"Time",
            "Description"
            
		};
		
		private Object[][] data;
		
		private TableModelBGM(){
			updateTableData();
		}
		
		public void updateTableData() {

			//get all the datazzz
			List<ILog> logentries = bicycle.getLogEntries();
			data = new Object[logentries.size()][columnNames.length];
			int i = 0;
			for(final ILog l : logentries){
				data[i][0] = l.getDate().toString();
				data[i][1] = l.getDesc();
				i++;
			}
			
			fireTableDataChanged();
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

	    public Class<?> getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
	}
	
	private interface EditCallback {
		public void Edit(String newValue, JTextField editedField);
	}

}
