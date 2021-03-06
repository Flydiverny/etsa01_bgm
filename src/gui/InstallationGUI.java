package gui;

import gui.base.Program;
import gui.screen.installation.WelcomeScreen;
import interfaces.IBicycleGarageManager;

public class InstallationGUI extends Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874471469172383477L;

	private static InstallationGUI instance;
	
	private Runnable callback;
	
	public InstallationGUI(IBicycleGarageManager manager, Runnable callback) {
		super(manager);
		InstallationGUI.instance = this;
		this.setTitle("Installation Wizard");
		
		this.setScreen(new WelcomeScreen());

		this.callback = callback;
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	public static Program getInstance() {
		return instance;
	}
	
	public void dispose() {
		super.dispose();
		
		callback.run();
	}
}
