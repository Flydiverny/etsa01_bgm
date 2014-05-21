package gui;

import gui.base.Program;
import gui.menubar.MenuBarBGM;
import gui.screen.main.LoginScreen;
import interfaces.IBicycleGarageManager;

import javax.swing.JFrame;

public class MainGUI extends Program {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3801631743920387386L;
	private static MainGUI instance;

	public MainGUI(IBicycleGarageManager manager) {
		super(manager);
		
		MainGUI.instance = this;
		
		this.setTitle("Bicycle Garage Manager");
		setScreen(new LoginScreen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static MainGUI getInstance() {
		return instance;
	}

	public void enableMenu() {
		rootPane.setJMenuBar(new MenuBarBGM());
	}
}
