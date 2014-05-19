package system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import test.drivers.*;
import interfaces.hardware.*;

public class SystemLauncher {
	private BicycleGarageManager manager;
	private boolean isInstalled = false;
	
	private final String SAVE_PATH = "bgm_database.out";
	
	public SystemLauncher() {
		if(systemInstalled()) {
			manager = loadSystem();
			isInstalled = true;
		} else {
			manager = new BicycleGarageManager();
		}
		
		bindHardware();
		setupReccuringSave();
		
		//Launching of GUI below.
		if(isInstalled) {
			//GUI g = new GUI();
		} else {//hej jag är snygg
			//GUI inst = new InstallGUI();
			//perhaps just send isInstalled as parameter to GUI and GUI has to take care of it.
		}
	}
	
	private boolean systemInstalled() {
		File f = new File(SAVE_PATH);
		return (f.exists() && !f.isDirectory());
	}
	
	private void bindHardware() {
        ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
        ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
        
        BarcodePrinter printer = new BarcodePrinterTestDriver();
        PinCodeTerminal entryTerminal = new PinCodeTerminalTestDriver(true);
        PinCodeTerminal exitTerminal = new PinCodeTerminalTestDriver(false);
        
        manager.registerHardwareDrivers(printer, entryLock, exitLock, entryTerminal, exitTerminal);
        entryTerminal.register(manager);
        exitTerminal.register(manager);
        
        BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
        BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
        
        readerEntry.register(manager);
        readerExit.register(manager);
	}
	 
	private void saveSystem() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(SAVE_PATH);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(manager);
	    	oos.flush();
	    	oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	private void setupReccuringSave() {
		final Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask()
	      {
	        public void run()
	        {
	        	saveSystem();
	        }
	      }, 0, 1000*60*15); // Every 15 minutes.
	}
	
	private BicycleGarageManager loadSystem() {
		BicycleGarageManager mgr = null;
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(SAVE_PATH);
			ObjectInputStream oin = new ObjectInputStream(fis);
			
			mgr = (BicycleGarageManager) oin.readObject();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failed to load system database, please restart the program. If the problem persists, please remove/rename/move the file named:\n" + SAVE_PATH);
		}
		
		return mgr;
	}
	 
    public static void main(String[] args) {
        new SystemLauncher();
    }
}
