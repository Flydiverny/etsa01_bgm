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
		JMenu filemenu = new JMenu();
		filemenu.setText("File");
		
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
				String input = JOptionPane.showInputDialog(MenuBarBGM.this, "Insert a Social Security Number to find in the member database", "Find by SSN", JOptionPane.QUESTION_MESSAGE);
				if(input != null){
					if(!Program.getMemberManager().validateSSN(input)){
						JOptionPane.showMessageDialog(MenuBarBGM.this, "Invalid SSN, please provide a valid SSN");
						return;
					}else if(Program.getMemberManager().getMember(input) == null){
						JOptionPane.showMessageDialog(MenuBarBGM.this, "Could not find such a member in the database");
						return;
					}
					MainGUI.getInstance().setScreen(new MemberScreen(Program.getMemberManager().getMember(input)));

				}
			}
		});
		findMemberBySSN.setText("Find Member By Social Security Number");
		
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
		gohomeitem.setText("Go Home");
		
		
		//add everything
		filemenu.add(gohomeitem);
		filemenu.add(listallmembersitem);
		filemenu.add(findMemberBySSN);
		filemenu.add(createMember);
		filemenu.add(sysparamitem);
		filemenu.add(opparamitem);
		filemenu.add(exititem);
		this.add(filemenu);
	}


}
