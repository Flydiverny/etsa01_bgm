package gui;

import interfaces.IBicycleGarageManager;
import gui.base.Program;
import gui.screen.installation.WelcomeScreen;

public class InstallationGUI extends Program {

	private static InstallationGUI instance;
	
	public InstallationGUI(IBicycleGarageManager manager) {
		super(manager);
		this.instance = this;
		this.setTitle("Installation Wizard");
		
		this.setScreen(new WelcomeScreen());
		
		this.setVisible(true);
	}
	
	public static Program getInstance() {
		return instance;
	}
}
