package SlotMachine;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.util.Delay;

public class CoinTray {

	private NXTRegulatedMotor trayMotor;
	
	public CoinTray(NXTRegulatedMotor _trayMotor)
	{
		trayMotor = _trayMotor;
	}
	public void empty()
	{
		trayMotor.rotate(-80);
		Delay.msDelay(1000);
		trayMotor.rotate(80);
	}
}
