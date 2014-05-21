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
import gui.MainGUI;
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
		this.add(memberDetails(), BorderLayout.WEST);
		this.add(bicyleList(), BorderLayout.EAST);
		this.add(southPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel memberDetails() {
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(0,3));
		
		createField(pane, "SSN", "");		

		createField(pane, "Name", "", new EditCallback() {
			@Override
			public void Edit(String newValue) {
				//member.setName(newValue);
			}
		});
		
		createField(pane, "Phone", "", new EditCallback() {
			@Override
			public void Edit(String newValue) {
				//member.setPhone(newValue);
			}
		});
		
		createField(pane, "Addr", "", new EditCallback() {
			@Override
			public void Edit(String newValue) {
				//member.setAddress(newValue);
			}
		});
		
		addMemberStatus(pane);
		
		createField(pane, "Amount of Bicycles", ""/*String.valueOf(member.amountOfBicycles())*/);
		createField(pane, "Monthly Fee", ""/*String.valueOf(bgm.getPaymentInfo(member))*/);
		
		addPIN(pane);
				
		return pane;
	}
	
	private JPanel southPanel() {
		JPanel pane = new JPanel();
		
		pane.setLayout(new BorderLayout());
		
		JButton delete = new JButton("Delete Member");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				return;/*
				if(JOptionPane.showConfirmDialog(null, "Do you really want to delte the selceted member?", "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(memberManager.removeMember(member.getSSN())) {
						JOptionPane.showMessageDialog(null,  "Member was successfully deleted");
						MainGUI.getInstance().setScreen(new MainScreen());
					} else {
						JOptionPane.showMessageDialog(null,  "Failed to delete member, make sure all prerequisites are fulfilled.");
					}
				}*/
			}
		});
		
		pane.add(delete, BorderLayout.EAST);
		
		return pane;
	}
	
	private void addMemberStatus(JPanel pane) {
		pane.add(new JLabel("Member Status"));
		final JTextField memberStatus = new JTextField();
		memberStatus.setText("");
		memberStatus.setEditable(false);
		pane.add(memberStatus);
		
		final JButton memberStatusToggle = new JButton("");
		
		memberStatusToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//member.enable(member.isDisabled());
				//memberStatus.setText((member.isDisabled() ? "Disabled" : "Enabled"));
				//memberStatusToggle.setText((member.isDisabled() ? "Enable" : "Disable"));
			}
		});
		
		pane.add(memberStatusToggle);
	}
	
	private void addPIN(JPanel pane) {
		pane.add(new JLabel("PIN-code"));
		final JTextField memberPIN = new JTextField();
		memberPIN.setText("");
		memberPIN.setEditable(false);
		pane.add(memberPIN);
		
		JButton memberPINGenerate = new JButton("Generate New PIN");
		
		memberPINGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to generate a new PIN-code for this member?", "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					//memberPIN.setText(memberManager.createNewPIN(member));
				}
			}
		});
		
		pane.add(memberPINGenerate);
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
		JPanel pane = new JPanel();
		
		pane.setLayout(new BorderLayout());	
				
		final TableModelBGM model = new TableModelBGM();
		
		final JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		JButton selectButton = new JButton("Open Selected");
		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				return;
				/*if(table.getSelectedRow()<0){
					JOptionPane.showMessageDialog(BicycleDetailsScreen.this, "No bicycle selected");
					return;
				}
				String target = (String) table.getValueAt(table.getSelectedRow(), 0);
*/
			}
		});
		
		JButton barcodeBtn = new JButton("Print Selected");
		barcodeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String target = (String) table.getValueAt(table.getSelectedRow(), 0);
				/*
				for(IBicycle b : member.getBicycles()) {
					if(b.getBarcode().equals(target)) {
						bgm.printBarcode(b);
						return;
					}
					
				}*/
			}
		});
		
		JButton addButton = new JButton("Add New Bicycle");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				String desc = JOptionPane.showInputDialog("Enter Bicycle description");
				member.registerBicycle(desc);
				
				model.updateTableData();
				*/
			}
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BorderLayout());
		
		buttonPane.add(addButton, BorderLayout.WEST);
		buttonPane.add(selectButton, BorderLayout.CENTER);
		buttonPane.add(barcodeBtn, BorderLayout.EAST);
		
		pane.add(scrollPane, BorderLayout.CENTER);
		pane.add(buttonPane, BorderLayout.SOUTH);
		
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

	    public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
	}
	
	private interface EditCallback {
		public void Edit(String newValue);
	}

}
