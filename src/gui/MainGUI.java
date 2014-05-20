package gui;

import gui.base.Program;
import gui.screen.main.LoginScreen;
import interfaces.IBicycleGarageManager;

public class MainGUI extends Program {
	private static MainGUI instance;

	public MainGUI(IBicycleGarageManager manager) {
		super(manager);
		
		this.instance = this;
		
		this.setTitle("Bicycle Garage Manager");
		setScreen(new LoginScreen());
				
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