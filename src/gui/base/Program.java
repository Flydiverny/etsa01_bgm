package gui.base;

import javax.swing.JFrame;

import interfaces.IBicycleGarageManager;
import interfaces.IMember;
import interfaces.IMemberManager;

public abstract class Program extends JFrame {
	
	private Screen screen;
	private static IBicycleGarageManager BGM_MANAGER;
	private static IMemberManager MEMBER_MANAGER;
	
	public Program(IBicycleGarageManager manager, IMemberManager mm) {
		Program.BGM_MANAGER = manager;
		Program.MEMBER_MANAGER = mm;
		
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
		return MEMBER_MANAGER;
	}
}
