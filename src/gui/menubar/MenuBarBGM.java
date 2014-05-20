package gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainGUI;
import gui.screen.main.MainScreen;
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
		
		JMenuItem findallitem = new JMenuItem();
		findallitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO implement
				//MainGUI.getInstance().setScreen(new SystemParametersScreen());
			}
		});
		findallitem.setText("Find All Members");
		
		JMenuItem exititem = new JMenuItem();
		exititem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exititem.setText("Save and Exit");
		
		
		//add everything
		filemenu.add(findallitem);
		filemenu.add(sysparamitem);
		filemenu.add(exititem);
		this.add(filemenu);
	}


}
