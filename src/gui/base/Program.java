package gui.base;

import javax.swing.JFrame;

import interfaces.IBicycleGarageManager;

public abstract class Program extends JFrame {
	
	private Screen screen;
	private static IBicycleGarageManager manager;
	
	public Program(IBicycleGarageManager manager) {
		this.manager = manager;
		
		this.setSize(854, 480);
		this.setResizable(false);
	}
	
	public void setScreen(Screen screen) {
		if(this.screen != null) {
			this.screen.hide();
			this.getContentPane().remove(screen);
		}
		
		this.screen = screen;
		
		if(this.screen != null) {
			this.screen.show();
			this.getContentPane().add(screen);
			this.getContentPane().repaint();
		}
	}
	
	public static IBicycleGarageManager getManager() {
		return manager;
	}
}
