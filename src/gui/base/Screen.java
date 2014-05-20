package gui.base;

import interfaces.IBicycleGarageManager;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
	
	protected IBicycleGarageManager bgm;
	
	private boolean created = false;
	
	public Screen() {
		this.bgm = Program.getManager();
	}
	
	public abstract void create();
	
	public void hide() {
		this.setVisible(false);
	}
	
	public void show() {
		if(!created) {
			this.create();
			created = true;
		}
		
		this.setVisible(true);
	}
}
