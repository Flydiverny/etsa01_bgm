package system;

import java.util.Timer;
import java.util.TimerTask;

import interfaces.hardware.PinCodeTerminal;

public class TerminalNotifier implements interfaces.TerminalNotifier {
	private BicycleGarageManager bgm;
	
	private PinCodeTerminal terminal;
	
	public TerminalNotifier(BicycleGarageManager bgm, PinCodeTerminal terminal) {
		this.bgm = bgm;
		this.terminal = terminal;
	}
	
	@Override
	public void NF1() {
		final Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask()
	      {
	    	private int counter = 1;
	    	
	        public void run()
	        {
	        	if(counter == 3)
	        		timer.cancel();
	        	
	        	terminal.lightLED(PinCodeTerminal.GREEN_LED, 1);
	        	counter++;
	        }
	      }, 0, 1500);
	}

	@Override
	public void NF2() {
		terminal.lightLED(PinCodeTerminal.GREEN_LED, 5); //bgm.getUnlockedDuration());
	}

	@Override
	public void NF3() {
		final Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask()
	      {
	    	private int counter = 1;
	    	
	        public void run()
	        {
	        	if(counter == 3)
	        		timer.cancel();
	        	
	        	terminal.lightLED(PinCodeTerminal.RED_LED, 1);
	        	counter++;
	        }
	      }, 0, 1500);
	}

	@Override
	public void NF4() {
		// Should blink alternerande
		terminal.lightLED(PinCodeTerminal.GREEN_LED, 3);
		terminal.lightLED(PinCodeTerminal.RED_LED, 3);
	}

	@Override
	public void NF5() {
		terminal.lightLED(PinCodeTerminal.GREEN_LED, 3);
	}

	@Override
	public void NF6() {
		terminal.lightLED(PinCodeTerminal.RED_LED, 3);
	}

}
