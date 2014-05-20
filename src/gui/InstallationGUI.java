package gui;

import javax.swing.JFrame;

import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;
import gui.base.Program;
import gui.screen.installation.WelcomeScreen;

public class InstallationGUI extends Program {

	private static InstallationGUI instance;
	
	public InstallationGUI(IBicycleGarageManager manager, IMemberManager mm) {
		super(manager, mm);
		InstallationGUI.instance = this;
		this.setTitle("Installation Wizard");
		
		this.setScreen(new WelcomeScreen());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static Program getInstance() {
		return instance;
	}
}
