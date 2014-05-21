package system;

import interfaces.ITerminalNotifier;
import interfaces.hardware.PinCodeTerminal;

import java.util.Timer;
import java.util.TimerTask;

public class TerminalNotifier implements ITerminalNotifier {
	private BicycleGarageManager bgm;
	
	public TerminalNotifier(BicycleGarageManager bgm) {
		this.bgm = bgm;
	}
	
	@Override
	public void NF1(final PinCodeTerminal terminal) {
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
	public void NF2(PinCodeTerminal terminal) {
		terminal.lightLED(PinCodeTerminal.GREEN_LED, bgm.getUnlockDuration());
	}

	@Override
	public void NF3(final PinCodeTerminal terminal) {
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
	public void NF4(final PinCodeTerminal terminal) {
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
	      }, 0, 2000);
	    
	    final Timer timer2 = new Timer();
	    timer2.scheduleAtFixedRate(new TimerTask()
	      {
	    	private int counter = 1;
	    	
	        public void run()
	        {
	        	if(counter == 3)
	        		timer2.cancel();
	        	
	        	terminal.lightLED(PinCodeTerminal.RED_LED, 1);
	        	counter++;
	        }
	      }, 1000, 2000);
	}

	@Override
	public void NF5(PinCodeTerminal terminal) {
		terminal.lightLED(PinCodeTerminal.GREEN_LED, 3);
	}

	@Override
	public void NF6(PinCodeTerminal terminal) {
		terminal.lightLED(PinCodeTerminal.RED_LED, 3);
	}

}
