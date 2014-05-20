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
		setScreen(new LoginScreen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
	}

	public static MainGUI getInstance() {
		return instance;
	}

	public void enableMenu() {
		rootPane.setJMenuBar(new MenuBarBGM());
	}
}
