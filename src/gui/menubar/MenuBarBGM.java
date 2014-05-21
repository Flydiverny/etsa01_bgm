package gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainGUI;
import gui.base.Program;
import gui.screen.main.CreateMemberScreen;
import gui.screen.main.MainScreen;
import gui.screen.main.MemberListScreen;
import gui.screen.main.MemberScreen;
import gui.screen.main.OperatorParametersScreen;
import gui.screen.main.SystemParametersScreen;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBarBGM extends JMenuBar {
	private static final long serialVersionUID = -4255644508622465982L;
	public MenuBarBGM(){
		//TODO implement
		//create menus
		JMenu systemmenu = new JMenu();
		systemmenu.setText("System");
		
		JMenu databasemenu = new JMenu();
		databasemenu.setText("Database");
		
		//create items
		JMenuItem sysparamitem = new JMenuItem();
		sysparamitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new SystemParametersScreen());
			}
		});
		sysparamitem.setText("Change System Parameters");
		
		JMenuItem opparamitem = new JMenuItem();
		opparamitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new OperatorParametersScreen());
			}
		});
		opparamitem.setText("Change Operator Parameters");
		
		JMenuItem listallmembersitem = new JMenuItem();
		listallmembersitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MemberListScreen());
			}
		});
		listallmembersitem.setText("List All Members");
		
		JMenuItem createMember = new JMenuItem();
		createMember.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new CreateMemberScreen());
			}
		});
		createMember.setText("Create New Member");
		
		JMenuItem findMemberBySSN = new JMenuItem();
		findMemberBySSN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog(null, "Insert a Social Security Number to find in the member database", "Find by SSN", JOptionPane.QUESTION_MESSAGE);
				if(input != null){
					if(!Program.getMemberManager().validateSSN(input)){
						JOptionPane.showMessageDialog(null, "Invalid SSN, please provide a valid SSN");
						return;
					}else if(Program.getMemberManager().getMember(input) == null){
						JOptionPane.showMessageDialog(null, "Could not find such a member in the database");
						return;
					}
					MainGUI.getInstance().setScreen(new MemberScreen(Program.getMemberManager().getMember(input)));

				}
			}
		});
		findMemberBySSN.setText("Find Member");
		
		JMenuItem exititem = new JMenuItem();
		exititem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exititem.setText("Save And Exit");
		
		JMenuItem gohomeitem = new JMenuItem();
		gohomeitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.getInstance().setScreen(new MainScreen());
			}
		});
		gohomeitem.setText("Status");
		
		JMenuItem checkinbike = new JMenuItem();
		checkinbike.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog(null, "Insert a barcode to find a bicycle in the database.", "Find by barcode", JOptionPane.QUESTION_MESSAGE);
				if(input != null){
					if(Program.getBGM().checkInBicycleByBarcode(input)){
						JOptionPane.showMessageDialog(null, "Bicycle was checked in");
						return;
					}else if(Program.getMemberManager().getBicycle(input) == null){
						JOptionPane.showMessageDialog(null, "Bicycle could not be found in database. Are you sure you provided a valid barcode?");
					}else if(Program.getMemberManager().getBicycle(input).isCheckedIn()){
						JOptionPane.showMessageDialog(null, "Bicycle is already checked in");
					}else{
						JOptionPane.showMessageDialog(null, "Could not check in bicycle. Unknown reason.");
					}
				}
			}
		});
		checkinbike.setText("Check In Bicycle");
		
		
		//add everything
		systemmenu.add(gohomeitem);
		systemmenu.add(sysparamitem);
		systemmenu.add(opparamitem);
		systemmenu.add(exititem);
		
		databasemenu.add(listallmembersitem);
		databasemenu.add(findMemberBySSN);
		databasemenu.add(createMember);
		databasemenu.add(checkinbike);
		
		this.add(systemmenu);
		this.add(databasemenu);
	}


}
