package gui;

import javax.swing.JFrame;

import gui.base.Program;
import gui.menubar.MenuBarBGM;
import gui.screen.main.LoginScreen;
import gui.screen.main.MainScreen;
import gui.screen.main.SystemParametersScreen;
import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;

public class MainGUI extends Program {
	private static MainGUI instance;

	public MainGUI(IBicycleGarageManager manager, IMemberManager mm) {
		super(manager, mm);
		
		MainGUI.instance = this;
		
		this.setTitle("Bicycle Garage Manager");
		//TODO setScreen(new LoginScreen());
		//TODO remove below
		setScreen(new MainScreen());
		//TODO Create menu etc.
			
		//TODO log in operator
		//TODO create main window when logged in successfully
		//TODO win
		//TODO after login, plz activate menubar
		rootPane.setJMenuBar(new MenuBarBGM());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TODO (maybez not here) plz remove menubar when logging out
		
		this.setVisible(true);
	}

	public static Program getInstance() {
		return instance;
	}

	public void enableMenu() {
		//TODO Enable menu
	}
}
