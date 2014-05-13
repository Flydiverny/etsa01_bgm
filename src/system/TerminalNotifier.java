package system;

import java.util.Timer;
import java.util.TimerTask;

import interfaces.hardware.PinCodeTerminal;

public class TerminalNotifier implements interfaces.TerminalNotifier {

	@Override
	public void NF1(PinCodeTerminal terminal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NF2(PinCodeTerminal terminal) {
		// TODO Auto-generated method stub
		
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
	        	
	        	terminal.lightLED(0, 1);
	        	counter++;
	        }
	      }, 0, 1500);
	}

	@Override
	public void NF4(PinCodeTerminal terminal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NF5(PinCodeTerminal terminal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NF6(PinCodeTerminal terminal) {
		// TODO Auto-generated method stub
		
	}

}
