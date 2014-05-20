package gui.menubar;

import gui.MainGUI;
import gui.screen.main.MainScreen;

import javax.swing.JMenuBar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuBarBGM extends JMenuBar {
	private static final long serialVersionUID = -4255644508622465982L;
	public MenuBarBGM(int menubartype){
		//TODO implement
		//MainGUI.getInstance().setScreen(new SystemParametersScreen());
		JMenu filemenu = new JMenu();
		JMenuItem sysparamitem = new JMenuItem();
		this.add(filemenu);
	}


}
