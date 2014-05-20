package gui.base;

import interfaces.IBicycleGarageManager;
import interfaces.IMemberManager;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
	
	protected IBicycleGarageManager bgm;
	
	protected IMemberManager memberManager;
	
	private boolean created = false;
	
	public Screen() {
		this.bgm = Program.getBGM();
		this.memberManager = Program.getMemberManager();
	}
	
	public abstract void create();
	
	public void hideScreen() {
		this.setVisible(false);
	}
	
	public void showScreen() {
		if(!created) {
			this.create();
			created = true;
		}
		
		this.setVisible(true);
	}
}
