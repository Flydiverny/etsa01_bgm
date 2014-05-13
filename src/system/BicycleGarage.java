package system;

import test.drivers.*;
import interfaces.hardware.*;

public class BicycleGarage {
	 public BicycleGarage() {
        BicycleGarageManager manager = new BicycleGarageManager();
        
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
	 
    public static void main(String[] args) {
        new BicycleGarage();
    }
}
