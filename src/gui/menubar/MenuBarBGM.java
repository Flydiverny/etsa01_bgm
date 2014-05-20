package gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainGUI;
import gui.screen.main.CreateMemberScreen;
import gui.screen.main.MainScreen;
import gui.screen.main.OperatorParametersScreen;
import gui.screen.main.SystemParametersScreen;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
				//TODO implement
				//MainGUI.getInstance().setScreen(new SystemParametersScreen());
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
		createMember.setText("Create new Member");
		
		JMenuItem exititem = new JMenuItem();
		exititem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exititem.setText("Save and Exit");
		
		
		//add everything
		filemenu.add(listallmembersitem);
		filemenu.add(createMember);
		filemenu.add(sysparamitem);
		filemenu.add(opparamitem);
		filemenu.add(exititem);
		this.add(filemenu);
	}


}
