package gui.base;

import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;

import javax.swing.JFrame;

public abstract class Program extends JFrame {
	
	private Screen screen;
	private static IBicycleGarageManager BGM_MANAGER;
	
	public Program(IBicycleGarageManager manager) {
		Program.BGM_MANAGER = manager;
		
		this.setSize(854, 480);
		this.setResizable(false);
	}
	
	public void setScreen(Screen screen) {
		if(this.screen != null) {
			this.screen.hideScreen();
			this.getContentPane().remove(screen);
		}
		
		this.screen = screen;
		
		if(this.screen != null) {
			this.screen.showScreen();
			this.getContentPane().add(screen);
			this.getContentPane().repaint();
		}
	}
	
	public static IBicycleGarageManager getBGM() {
		return BGM_MANAGER;
	}
	
	public static IMemberManager getMemberManager() {
		return BGM_MANAGER.getMemberManager();
	}
}
