package gui;

import gui.base.Program;
import gui.screen.main.LoginScreen;
import gui.screen.main.SystemParametersScreen;
import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;

public class MainGUI extends Program {
	private static MainGUI instance;

	public MainGUI(IBicycleGarageManager manager, IMemberManager mm) {
		super(manager, mm);
		
		this.instance = this;
		
		this.setTitle("Bicycle Garage Manager");
		//TODO setScreen(new LoginScreen());
		//TODO remove below
		setScreen(new SystemParametersScreen());
		//TODO Create menu etc.
			
		//TODO log in operator
		//TODO create main window when logged in successfully
		//TODO win
		
		this.setVisible(true);
	}

	public static Program getInstance() {
		return instance;
	}

	public void enableMenu() {
		//TODO Enable menu
	}
}
